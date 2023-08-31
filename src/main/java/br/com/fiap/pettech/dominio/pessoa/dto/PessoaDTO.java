package br.com.fiap.pettech.dominio.pessoa.dto;

import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 17/08/2023
 * Project Name: pet-tech
 */

public record PessoaDTO(
        Long id,
        @NotBlank(message = "O CPF não pode estar em branco")
        @CPF(message = "CPF inválido")
        String cpf,
        String nome,
        LocalDate nascimento,
        @Email(message = "O email deve estar em um formato válido")
        String email
) {
    public PessoaDTO(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getCpf(), pessoa.getNome(), pessoa.getNascimento(), pessoa.getEmail());
    }

    public static Pessoa toEntity(PessoaDTO dto) {
        return new Pessoa(dto);
    }

    public static PessoaDTO fromEntity(Pessoa pessoa) {
        return new PessoaDTO(
                pessoa.getId(),
                pessoa.getCpf(),
                pessoa.getNome(),
                pessoa.getNascimento(),
                pessoa.getEmail()
        );
    }

    public static Pessoa mapperDtoToEntity(PessoaDTO dto, Pessoa entity) {
        entity.setCpf(dto.cpf());
        entity.setNome(dto.nome());
        entity.setNascimento(dto.nascimento());
        entity.setEmail(dto.email());
        return entity;
    }
}
