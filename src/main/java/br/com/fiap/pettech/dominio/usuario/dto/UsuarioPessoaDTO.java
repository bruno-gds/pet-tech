package br.com.fiap.pettech.dominio.usuario.dto;

import br.com.fiap.pettech.dominio.pessoa.dto.PessoaDTO;
import br.com.fiap.pettech.dominio.usuario.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 23/08/2023
 * Project Name: pet-tech
 */

public record UsuarioPessoaDTO(
        Long id,
        @NotBlank(message = "O nome de usuário não pode estar em branco")
        String username,
        @NotBlank(message = "O password não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter pelo menos {min} caracteres")
        String password,
        PessoaDTO pessoa
) {
    public static UsuarioPessoaDTO fromEntity(Usuario entity) {
        return new UsuarioPessoaDTO(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getPessoa() != null ? new PessoaDTO(entity.getPessoa()) : null
        );
    }
}
