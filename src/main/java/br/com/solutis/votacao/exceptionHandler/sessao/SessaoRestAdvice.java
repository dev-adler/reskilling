package br.com.solutis.votacao.exceptionHandler.sessao;

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
public class SessaoRestAdvice {

    /**
     * Caso a excessão já tenha sido aberta gera uma excessão do tipo 412 (PRECONDITION_FAILED).
     * @param sessaoJaAberta Class referente à exceção.
     * @return Payload de retorno contendo a data da exceção, o tipo (412) e a mensagem tratada.
     */
    @ResponseBody
    @ExceptionHandler(SessaoJaAbertaException.class)
    public ResponseEntity<MessageExceptionHandler> sessaoJaAberta(SessaoJaAbertaException sessaoJaAberta){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date().toString(), HttpStatus.PRECONDITION_FAILED.value(), sessaoJaAberta.getMensagem());
        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }

    /***
     * Em caso de não encontrar o registro da sessão no banco gera uma exceção do tipo 404 (NOT_FOUND).
     * @param sessaoNaoEncontradaException Class referente à exceção.
     * @return Payload de retorno contendo a data da exceção, o tipo (404) e a mensagem tratada.
     */
    @ResponseBody
    @ExceptionHandler(SessaoNaoEncontradaException.class)
    public ResponseEntity<MessageExceptionHandler> sessaoNaoEncontrada(SessaoNaoEncontradaException sessaoNaoEncontradaException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date().toString(), HttpStatus.NOT_FOUND.value(), "Sessão não encontrada.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Caso a sessão já esteja encerrada gera uma excessão do tipo 412 (PRECONDITION_FAILED).
     * @param sessaoEncerradaException Class referente à exceção.
     * @return Payload de retorno contendo a data da exceção, o tipo (412) e a mensagem tratada.
     */
    @ResponseBody
    @ExceptionHandler(SessaoEncerradaException.class)
    public ResponseEntity<MessageExceptionHandler> sessaoEncerrada(SessaoEncerradaException sessaoEncerradaException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date().toString(), HttpStatus.PRECONDITION_FAILED.value(), sessaoEncerradaException.getMensagem());
        return  new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }
}
