package br.com.solutis.votacao.service;

import br.com.solutis.votacao.exceptionHandler.associado.AssociadoNaoEncontadoException;
import br.com.solutis.votacao.orm.Associado;
import br.com.solutis.votacao.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AssociadoService.class)
public class AssociadoServiceTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AssociadoService associadoService;

    @MockBean
    AssociadoRepository associadoRepository;

    @Test
    void findAssociadoByIdTest() throws Exception {

        Optional<Associado> associadoOptionalResult;
        associadoOptionalResult = Optional.of(new Associado(1L, "NOME", "123"));

        given(associadoRepository.querySelectAssociadoById(anyLong())).willReturn(associadoOptionalResult);

        Associado associado = associadoService.findAssociadoById(1L);

        Assert.isTrue(associado.getId().equals(1L), "");
        Assert.isTrue(associado.getNome().equals("NOME"), "");
        Assert.isTrue(associado.getCpf().equals("123"), "");
    }

    @Test
    @ExceptionHandler(AssociadoNaoEncontadoException.class)
    void findAssociadoByIdExceptionTest() throws Exception {

        Optional<Associado> associadoOptionalResult;
        associadoOptionalResult = Optional.of(new Associado(1L, "NOME", "123"));

        given(associadoRepository.querySelectAssociadoById(anyLong())).willReturn(associadoOptionalResult);

        Associado associado = associadoService.findAssociadoById(2L);
    }
}
