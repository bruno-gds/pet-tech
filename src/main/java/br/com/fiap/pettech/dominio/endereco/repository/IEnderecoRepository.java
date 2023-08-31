package br.com.fiap.pettech.dominio.endereco.repository;

import br.com.fiap.pettech.dominio.endereco.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 17/08/2023
 * Project Name: pet-tech
 */

@Repository
public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {
}
