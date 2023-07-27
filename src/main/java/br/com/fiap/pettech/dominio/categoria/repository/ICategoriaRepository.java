package br.com.fiap.pettech.dominio.categoria.repository;

import br.com.fiap.pettech.dominio.categoria.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 26/07/2023
 * Project Name: pet-tech
 */
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
