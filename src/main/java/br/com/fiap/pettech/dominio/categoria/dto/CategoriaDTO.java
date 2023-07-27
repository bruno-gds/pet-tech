package br.com.fiap.pettech.dominio.categoria.dto;

import br.com.fiap.pettech.dominio.categoria.entity.Categoria;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 26/07/2023
 * Project Name: pet-tech
 */

public class CategoriaDTO {

    private Long id;
    private String nome;


    public CategoriaDTO() {}

    public CategoriaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
