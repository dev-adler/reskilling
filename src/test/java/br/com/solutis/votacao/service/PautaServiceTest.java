package br.com.solutis.votacao.service;

import br.com.solutis.votacao.dto.PautaDTO;
import br.com.solutis.votacao.exceptionHandler.pauta.PautaNaoEncontradaException;
import br.com.solutis.votacao.orm.Pauta;
import br.com.solutis.votacao.repository.PautaRerpository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(PautaService.class)
public class PautaServiceTest {

    @Autowired
    PautaService pautaService;

    @MockBean
    PautaRerpository pautaRerpository;

    @Test
    void findPautaByIdTest() throws Exception {

        Optional<Pauta> pautaOptionalResult;
        pautaOptionalResult = Optional.of(new Pauta(1L, "Teste de pauta"));

        given(pautaRerpository.querySelectPautaById(anyLong())).willReturn(pautaOptionalResult);

        Pauta pauta = pautaService.findPautaById(1L);

        Assert.isTrue(pauta.getId().equals(1L), "");
        Assert.isTrue(pauta.getTema().equals("Teste de pauta"), "");
    }

    @Test
    @ExceptionHandler(PautaNaoEncontradaException.class)
    void findPautaByIdExceptionTest() throws Exception {

        Optional<Pauta> pautaOptionalResult;
        pautaOptionalResult = Optional.of(new Pauta(1L, "Teste de pauta"));

        given(pautaRerpository.querySelectPautaById(anyLong())).willReturn(pautaOptionalResult);

        Pauta pauta = pautaService.findPautaById(2L);
    }

    @Test
    void savePautaServiceTest() throws Exception {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setTema("Teste cadastro de pauta");

        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setTema("Teste cadastro de pauta");


        given(pautaRerpository.save(any())).willReturn(pauta);

        Pauta pautaResult = pautaService.savePautaService(pautaDTO);

        Assert.isTrue(pautaResult.getId().equals(1L), "");
        Assert.isTrue(pautaResult.getTema().equals("Teste cadastro de pauta"), "");

    }
}
