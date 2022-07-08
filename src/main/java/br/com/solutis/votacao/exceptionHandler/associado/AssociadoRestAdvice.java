package br.com.solutis.votacao.exceptionHandler.associado;

import br.com.solutis.votacao.exceptionHandler.MessageExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * Classe de definição dos métodos de exceção a serem lançadas.
 */
@RestControllerAdvice(basePackages = "br.com.solutis.votacao.api")
public class AssociadoRestAdvice {

    /**
     * Em caso de não encontrar o registro do associado no banco gera uma exceção do tipo 404 (NOT_FOUND).
     * @param associadoNaoEncontadoException Class referente à exceção.
     * @return Payload de retorno contendo a data da exceção, o tipo (404) e a mensagem tratada.
     */
    @ResponseBody
    @ExceptionHandler(AssociadoNaoEncontadoException.class)
    public ResponseEntity<MessageExceptionHandler> associadoNaoEncontrado(AssociadoNaoEncontadoException associadoNaoEncontadoException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date().toString(), HttpStatus.NOT_FOUND.value(), "Associado não cadastrado.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
