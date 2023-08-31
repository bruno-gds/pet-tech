package br.com.fiap.pettech.dominio.pessoa.dto;

import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import br.com.fiap.pettech.dominio.usuario.dto.UsuarioDTO;
import br.com.fiap.pettech.dominio.usuario.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 17/08/2023
 * Project Name: pet-tech
 */

public record PessoaUsuarioDTO(
        Long id,
        @NotBlank(message = "O CPF não pode estar em branco")
        @CPF(message = "CPF inválido")
        String cpf,
        String nome,
        LocalDate nascimento,
        @Email(message = "O email deve estar em um formato válido")
        String email,
        UsuarioDTO usuario
) {
    public static Pessoa toEntity(PessoaUsuarioDTO dto, Usuario usuario) {
        return new Pessoa(dto, usuario);
    }

    public static PessoaUsuarioDTO fromEntity(Pessoa pessoa) {
        return new PessoaUsuarioDTO(
                pessoa.getId(),
                pessoa.getCpf(),
                pessoa.getNome(),
                pessoa.getNascimento(),
                pessoa.getEmail(),
                pessoa.getUsuario() != null ? new UsuarioDTO(pessoa.getUsuario()) : null
        );
    }

    public static Pessoa mapperDtoToEntity(PessoaUsuarioDTO dto, Pessoa entity, Usuario usuario) {
        entity.setCpf(dto.cpf());
        entity.setNome(dto.nome());
        entity.setNascimento(dto.nascimento());
        entity.setEmail(dto.email());
        entity.setUsuario(usuario);
        return entity;
    }
}
