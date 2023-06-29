package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTests {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private IProdutoRepository repository;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private PageImpl<Produto> page;
    private ProdutoDTO produtoDTO;
    private Produto produto;
    private String nomeAtualizado;

    @BeforeEach
    void setUp() {
        idExistente = UUID.fromString("cd1d5aaa-4141-4318-a3e4-9008b841a8b2");
        idNaoExistente = UUID.fromString("b8e00075-4d0f-4bc8-a9e6-63dafebc50");
        pageRequest = PageRequest.of(0, 10);
        produto = Factory.createProduto();
        produtoDTO = Factory.createProdutoDTO();
        page = new PageImpl<>(List.of(produto));
        nomeAtualizado = "Produto Atualizado";

        Mockito.when(repository.findById((UUID) ArgumentMatchers.any())).thenReturn(Optional.of(produto));
        Mockito.when(repository.findAll((PageRequest) ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
    }

    @Test
    public void findAllDeveRetornarUmaListaDeProdutosDTO() {
        Page ProdutoDTO = service.findAll(this.pageRequest);
        Assertions.assertNotNull(ProdutoDTO);
    }

    @Test
    public void findByIdDeveRetornarUmProdutoDTOAoBuscarPorId() {
        ProdutoDTO produtoDTO = service.findById(this.idExistente);
        Assertions.assertNotNull(produtoDTO);
    }

    @Test
    public void findByIdDeveRetornarUmaExcessaoAoBuscarPorIdQueNaoExiste() {
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            service.findById(this.idNaoExistente);
        });
    }
}
