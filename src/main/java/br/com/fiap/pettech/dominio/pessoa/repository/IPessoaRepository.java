package br.com.fiap.pettech.dominio.pessoa.repository;

import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 19/07/2023
 * Project Name: pet-tech
 */

public interface IPessoaRepository extends JpaRepository<Pessoa, Long> {
//    List<Pessoa> findAll(int page, int pageSize);
//    Pessoa findById(Long id);
//    Pessoa save(Pessoa pessoa);
//    Pessoa update(Long id, Pessoa pessoa);
//    void deleteById(Long id);
}
