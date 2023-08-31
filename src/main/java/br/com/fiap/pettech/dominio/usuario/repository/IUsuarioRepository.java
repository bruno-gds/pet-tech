package br.com.fiap.pettech.dominio.usuario.repository;

import br.com.fiap.pettech.dominio.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 23/08/2023
 * Project Name: pet-tech
 */

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
