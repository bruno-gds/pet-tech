package br.com.fiap.pettech.dominio.usuario.dto;

import br.com.fiap.pettech.dominio.usuario.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 23/08/2023
 * Project Name: pet-tech
 */

public record UsuarioDTO(
        Long id,
        @NotBlank(message = "O nome de usuário não pode estar em branco")
        String username,
        @NotBlank(message = "O password não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter pelo menos {min} caracteres")
        String password
) {

    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername(), usuario.getPassword());
    }

    public static UsuarioDTO fromEntity(Usuario entity) {
        return new UsuarioDTO(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword()
        );
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        return new Usuario(
                dto.username,
                dto.password
        );
    }

    public static Usuario mapperDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setUsername(dto.username);
        entity.setPassword(dto.password);
        return entity;
    }
}
