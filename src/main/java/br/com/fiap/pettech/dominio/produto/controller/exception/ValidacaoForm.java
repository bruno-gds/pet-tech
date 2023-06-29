package br.com.fiap.pettech.dominio.produto.controller.exception;

import br.com.fiap.pettech.dominio.produto.service.exception.DefaultError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 28/06/2023
 * Project Name: pet-tech
 */

public class ValidacaoForm extends DefaultError {

    private List<ValidacaoCampo> mensagens = new ArrayList<ValidacaoCampo>();

    public List<ValidacaoCampo> getMensagens() {
        return mensagens;
    }

    public void addMensagens(String campo, String mensagem) {
        mensagens.add(new ValidacaoCampo(campo, mensagem));
    }
}
