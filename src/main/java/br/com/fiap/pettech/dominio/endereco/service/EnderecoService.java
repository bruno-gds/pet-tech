package br.com.fiap.pettech.dominio.endereco.service;

import br.com.fiap.pettech.dominio.endereco.dto.EnderecoPessoaDTO;
import br.com.fiap.pettech.dominio.endereco.entity.Endereco;
import br.com.fiap.pettech.dominio.endereco.repository.IEnderecoRepository;
import br.com.fiap.pettech.dominio.pessoa.repository.IPessoaRepository;
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
public class EnderecoService {

    private final IEnderecoRepository enderecoRepository;
    private final IPessoaRepository pessoaRepository;

    @Autowired
    public EnderecoService(
            IEnderecoRepository enderecoRepository,
            IPessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true)
    public Page<EnderecoPessoaDTO> findAll(PageRequest pageRequest) {
        var enderecos = enderecoRepository.findAll(pageRequest);

        return enderecos.map(EnderecoPessoaDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public EnderecoPessoaDTO findById(Long id) {
        var endereco = enderecoRepository.findById(id).orElseThrow(
                () -> new ControllerNotFoundException("Endereço não encontrado")
        );

        return EnderecoPessoaDTO.fromEntity(endereco);
    }

    @Transactional
    public EnderecoPessoaDTO save(EnderecoPessoaDTO dto) {
        try {
            var pessoa = pessoaRepository.getReferenceById(dto.pessoa().id());
            var entity = EnderecoPessoaDTO.toEntity(dto, pessoa);
            var enderecoSaved = enderecoRepository.save(entity);

            return EnderecoPessoaDTO.fromEntity(enderecoSaved);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Pessoa não encontrada");
        }
    }

    @Transactional
    public EnderecoPessoaDTO update(Long id, EnderecoPessoaDTO dto) {
        try {
            Endereco entity = enderecoRepository.getReferenceById(id);
            var pessoa = pessoaRepository.getReferenceById(dto.pessoa().id());
            EnderecoPessoaDTO.mapperDtoToEntity(dto, entity, pessoa);
            entity = enderecoRepository.save(entity);

            return EnderecoPessoaDTO.fromEntity(entity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Pessoa/Endereço não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            enderecoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }
}
