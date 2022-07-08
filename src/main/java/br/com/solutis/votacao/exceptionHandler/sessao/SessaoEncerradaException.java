package br.com.solutis.votacao.exceptionHandler.sessao;

import lombok.Getter;

/**
 * Class para lançamento de exceção em caso de uma sessão estar encerrada.
 */
@Getter
public class SessaoEncerradaException extends RuntimeException{
    private String mensagem;

    public SessaoEncerradaException(String mensagem){
        super(mensagem);
        this.mensagem = mensagem;
    }
}
