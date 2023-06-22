package br.com.fiap.pettech.dominio.produto.controller;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProdutoService produtoService;

    @Test
    public void findByIdDeveRetornarUmProdutoDTOCasoIdExista() throws Exception {
        UUID id = UUID.fromString("b8e00075-4d0f-4bc8-a9e6-63dafebc50c4");

        ProdutoDTO produto = new ProdutoDTO();
        produto.setNome("Produto");
        produto.setDescricao("Descricao");
        produto.setUrlImagem("urlImagem");
        produto.setPreco(10.0);
        produto.setId(id);

        Mockito.when(produtoService.findById(id)).thenReturn(produto);

        ResultActions result = mockMvc.perform(get("/produto/{id}", id).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }
}
