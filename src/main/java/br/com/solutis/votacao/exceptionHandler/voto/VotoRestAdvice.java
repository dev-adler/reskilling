package br.com.solutis.votacao.exceptionHandler.voto;

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
public class VotoRestAdvice {

    /**
     * Caso o associado já tenha votado na pauta gera uma exceção do tipo 412 (PRECONDITION_FAILED).
     * @param votoJaComputadoException Class referente à exceção.
     * @return Payload de retorno contendo a data da exceção, o tipo (412) e a mensagem tratada.
     */
    @ResponseBody
    @ExceptionHandler(VotoJaComputadoException.class)
    public ResponseEntity<MessageExceptionHandler> votoJaCompudado(VotoJaComputadoException votoJaComputadoException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date().toString(), HttpStatus.PRECONDITION_FAILED.value(), "Voto não computado. Associado já efetuou o voto.");
        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }

    /**
     * Caso a votação dê empate o payload será retorndo como excessão, porém de sucesso (200).
     * @param empateException Class referente à exceção.
     * @return Payload de retorno contendo a data da exceção, o tipo (200) e a mensagem tratada.
     */
    @ResponseBody
    @ExceptionHandler(EmpateException.class)
    public ResponseEntity<MessageExceptionHandler> empate(EmpateException empateException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date().toString(), HttpStatus.OK.value(), "A votação deu empate para esta pauta.");
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    /**
     * Caso o usuário tente efetuar um voto diferente de Sim ou Não gera uma exceção 400 (BAD_REQUEST).
     * @param votoNaoPermitidoException Classe referente à exceção.
     * @return Payload de retorno contendo a data da exceção, o tipo (400) e a mensagem tratada.
     */
    @ResponseBody
    @ExceptionHandler(VotoNaoPermitidoException.class)
    public ResponseEntity<MessageExceptionHandler> votoNaoPermitido(VotoNaoPermitidoException votoNaoPermitidoException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date().toString(), HttpStatus.BAD_REQUEST.value(), "Só são permitidos votos Sim ou Não.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
