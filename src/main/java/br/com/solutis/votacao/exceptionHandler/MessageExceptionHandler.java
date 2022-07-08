package br.com.solutis.votacao.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe para definição da estrutura do payload de retorno das exceções.
 */
@AllArgsConstructor
@Getter
@Setter
public class MessageExceptionHandler {

    private String timestamp;
    private Integer status;
    private String message;

}
