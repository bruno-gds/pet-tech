package br.com.fiap.pettech.dominio.usuario.service;

import br.com.fiap.pettech.dominio.usuario.dto.UsuarioDTO;
import br.com.fiap.pettech.dominio.usuario.dto.UsuarioPessoaDTO;
import br.com.fiap.pettech.dominio.usuario.entity.Usuario;
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
public class UsuarioService {
    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Page<UsuarioPessoaDTO> findAll(PageRequest pageRequest) {
        var usuarios = usuarioRepository.findAll(pageRequest);

        return usuarios.map(UsuarioPessoaDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public UsuarioPessoaDTO findById(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new ControllerNotFoundException("Usuário não encontrado")
        );

        return UsuarioPessoaDTO.fromEntity(usuario);
    }

    @Transactional
    public UsuarioDTO save(UsuarioDTO dto) {
        var entity = UsuarioDTO.toEntity(dto);
        var usuarioSaved = usuarioRepository.save(entity);

        return UsuarioDTO.fromEntity(usuarioSaved);
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        try {
            Usuario entity = usuarioRepository.getReferenceById(id);
            UsuarioDTO.mapperDtoToEntity(dto, entity);
            entity = usuarioRepository.save(entity);

            return UsuarioDTO.fromEntity(entity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuário não encontrado, id: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }
}
