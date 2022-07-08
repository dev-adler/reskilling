package br.com.solutis.votacao.exceptionHandler.sessao;

import lombok.Getter;

/**
 * Class para lançamento de exceção caso a sessão já tenha sido aberta.
 */
@Getter
public class SessaoJaAbertaException extends RuntimeException{
    private String mensagem;

    public SessaoJaAbertaException(String mensagem){
        super(mensagem);
        this.mensagem = mensagem;
    }
}
