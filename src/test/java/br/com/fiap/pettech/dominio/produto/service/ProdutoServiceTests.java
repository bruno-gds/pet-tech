package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTests {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private IProdutoRepository repository;

    @Test
    public void findByIdDeveRetornarUmProdutoDTOAoBuscarPorId() {
        UUID id = UUID.fromString("b8e00075-4d0f-4bc8-a9e6-63dafebc50c4");

        Produto produto = new Produto();
        produto.setNome("Produto");
        produto.setDescricao("Descricao");
        produto.setUrlImagem("urlImagem");
        produto.setPreco(10.0);
        produto.setId(null);

        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(produto));

        ProdutoDTO produtoDTO = service.findById(id);

        Assertions.assertNotNull(produtoDTO);
    }
}
