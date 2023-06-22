package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProdutoRepositoryTests {

    @Autowired
    private IProdutoRepository produtoRepository;

    @Test
    public void findByIdDeveRetornarObjetoCasoIdExista() {
        UUID id = UUID.fromString("b8e00075-4d0f-4bc8-a9e6-63dafebc50c4");
        Optional<Produto> result = produtoRepository.findById(id);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdDeveRetornarControllerNotFoundExceptionCasoIdNaoExista() {
        UUID id = UUID.fromString("b8e00075-4d0f-4bc8-a9e6-63dafebc50");

        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            produtoRepository.findById(id).orElseThrow( () -> new ControllerNotFoundException("Product not found") );
        });
    }

    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull() {
        Produto produto = new Produto();
        produto.setNome("Produto");
        produto.setDescricao("Descricao");
        produto.setUrlImagem("urlImagem");
        produto.setPreco(10.0);
        produto.setId(null);

        var produtoSalvo = produtoRepository.save(produto);
        Assertions.assertNotNull(produtoSalvo.getId());
    }
}
