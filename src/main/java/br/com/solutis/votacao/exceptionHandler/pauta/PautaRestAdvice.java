package br.com.solutis.votacao.exceptionHandler.pauta;

import br.com.solutis.votacao.exceptionHandler.MessageExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de definição dos métodos de exceção a serem lançadas.
 */
@RestControllerAdvice(basePackages = "br.com.solutis.votacao.api")
public class PautaRestAdvice {

    /**
     * Em caso de não encontrar o registro da pauta no banco gera uma exceção do tipo 404 (NOT_FOUND).
     * @param pautaNaoEncontradaException Class referente à exceção.
     * @return Payload de retorno contendo a data da exceção, o tipo (404) e a mensagem tratada.
     */
    @ResponseBody
    @ExceptionHandler(PautaNaoEncontradaException.class)
    public ResponseEntity<MessageExceptionHandler> pautaNaoEncontrada(PautaNaoEncontradaException pautaNaoEncontradaException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date().toString(), HttpStatus.NOT_FOUND.value(), "Pauta não cadastrada.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Em caso de tentativa de cadastro de pauta sem tema gera uma exceção do tipo 400 (BAD_REQUEST).
     * @param pautaSemTemaException Class referente à exceção.
     * @return Payload de retorno contendo a data da exceção, o tipo (400) e a mensagem tratada.
     */
    @ResponseBody
    @ExceptionHandler(PautaSemTemaException.class)
    public ResponseEntity<MessageExceptionHandler> pautaSemTema(PautaSemTemaException pautaSemTemaException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date().toString(), HttpStatus.BAD_REQUEST.value(), "Tema para a pauta não informado.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
