package br.com.fiap.pettech.dominio.pessoa.service;

import br.com.fiap.pettech.dominio.pessoa.dto.PessoaEnderecoUsuarioDTO;
import br.com.fiap.pettech.dominio.pessoa.dto.PessoaUsuarioDTO;
import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import br.com.fiap.pettech.dominio.pessoa.repository.IPessoaRepository;
import br.com.fiap.pettech.dominio.usuario.repository.IUsuarioRepository;
import br.com.fiap.pettech.exception.service.ControllerNotFoundException;
import br.com.fiap.pettech.exception.service.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 17/08/2023
 * Project Name: pet-tech
 */

@Service
public class PessoaService {
    private final IPessoaRepository pessoaRepository;

    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public PessoaService(IPessoaRepository pessoaRepository, IUsuarioRepository usuarioRepository) {
        this.pessoaRepository = pessoaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Page<PessoaEnderecoUsuarioDTO> findAll(PageRequest pageRequest) {
        var pessoas = pessoaRepository.findAll(pageRequest);

        return pessoas.map(PessoaEnderecoUsuarioDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public PessoaEnderecoUsuarioDTO findById(Long id) {
        var pessoa = pessoaRepository.findById(id).orElseThrow(
                () -> new ControllerNotFoundException("Pessoa não encontrada")
        );

        return PessoaEnderecoUsuarioDTO.fromEntity(pessoa);
    }

    @Transactional
    public PessoaUsuarioDTO save(PessoaUsuarioDTO dto) {
        try {
            var usuario = usuarioRepository.getReferenceById(dto.usuario().id());
            var entity = PessoaUsuarioDTO.toEntity(dto, usuario);
            var pessoaSaved = pessoaRepository.save(entity);

            return PessoaUsuarioDTO.fromEntity(pessoaSaved);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Usuário não encontrado");
        }
    }

    @Transactional
    public PessoaUsuarioDTO update(Long id, PessoaUsuarioDTO dto) {
        try {
            var usuario = usuarioRepository.getReferenceById(dto.usuario().id());
            Pessoa entity = pessoaRepository.getReferenceById(id);
            PessoaUsuarioDTO.mapperDtoToEntity(dto, entity, usuario);
            entity = pessoaRepository.save(entity);

            return PessoaUsuarioDTO.fromEntity(entity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Endereço não encontrado, id: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            pessoaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }
}
