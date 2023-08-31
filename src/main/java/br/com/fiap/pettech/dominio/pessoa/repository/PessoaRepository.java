package br.com.fiap.pettech.dominio.pessoa.repository;

import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import br.com.fiap.pettech.exception.service.ControllerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 19/07/2023
 * Project Name: pet-tech
 */

//@Repository
//public class PessoaRepository implements IPessoaRepository {
public class PessoaRepository {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PessoaRepository(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    @Override
//    public List<Pessoa> findAll(int page, int pageSize) {
//        int offset = (page - 1) * pageSize;
//        String sql = "SELECT * FROM pessoas LIMIT ? OFFSET ?";
//        return jdbcTemplate
//                .query(
//                        sql,
//                        new Object[]{pageSize, offset},
//                        new PessoaRowMapper()
//                );
//    }
//
//    @Override
//    public Pessoa findById(Long id) {
//        String sql = "SELECT * FROM pessoas WHERE id = ?";
//        List<Pessoa> pessoas = jdbcTemplate
//                .query(
//                        sql,
//                        new Object[]{id},
//                        new PessoaRowMapper()
//                );
//        return pessoas.isEmpty() ? null : pessoas.get(0);
//    }
//
//    @Override
//    public Pessoa save(Pessoa pessoa) {
//        try {
//            String sql = "INSERT INTO pessoas (cpf, nome, email, nascimento) VALUES (?, ?, ?, ?)";
//            this.jdbcTemplate
//                    .update(
//                        sql,
//                        pessoa.getCpf(),
//                        pessoa.getNome(),
//                        pessoa.getEmail(),
//                        Date.valueOf(pessoa.getNascimento())
//                    );
//            return pessoa;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public Pessoa update(Long id, Pessoa pessoa) {
//        String sql = "UPDATE pessoas SET cpf = ?, nome = ?, email = ?, nascimento = ? WHERE id = ?";
//        int rowsAffected = jdbcTemplate
//                .update(
//                        sql,
//                        pessoa.getCpf(),
//                        pessoa.getNome(),
//                        pessoa.getEmail(),
//                        pessoa.getNascimento(),
//                        id
//                );
//        if (rowsAffected == 0) {
//            throw new ControllerNotFoundException("Pessoa com ID " + id + " não encontrado");
//        } else {
//            return pessoa;
//        }
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        String sql = "DELETE FROM pessoas WHERE id = ?";
//        int rowsAffected = jdbcTemplate
//                .update(
//                        sql,
//                        id
//                );
//        if (rowsAffected == 0) {
//            throw new ControllerNotFoundException("Pessoa com ID" + id + " não encontrado");
//        }
//    }
//
//    private static class PessoaRowMapper implements RowMapper<Pessoa> {
//        @Override
//        public Pessoa mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Pessoa pessoa = new Pessoa();
//            pessoa.setId(rs.getLong("id"));
//            pessoa.setCpf(rs.getString("cpf"));
//            pessoa.setNome(rs.getString("nome"));
//            pessoa.setEmail(rs.getString("email"));
//            pessoa.setNascimento(rs.getDate("nascimento").toLocalDate());
//            return pessoa;
//        }
//    }
}
