package br.com.solutis.votacao.api;

import br.com.solutis.votacao.dto.SessaoDTO;
import br.com.solutis.votacao.orm.Sessao;
import br.com.solutis.votacao.service.SessaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.Calendar;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(SessaoController.class)
public class SessaoControllerTest {

    @MockBean
    SessaoService sessaoService;

    @Autowired
    private SessaoController sessaoController;

    @Test
    void saveSessaoTest() throws Exception {

        SessaoDTO sessaoDTO = new SessaoDTO();

        sessaoDTO.setPautaId(1L);
        sessaoDTO.setTempoSessao(2);

        Sessao sessao = new Sessao();
        sessao.setId(1L);
        sessao.setAberturaDeSessao(Calendar.getInstance());
        sessao.setTempoSessao(2);

        when(sessaoService.saveSessaoService(any())).thenReturn(sessao);

        ResponseEntity sessaoControllerResponse = sessaoController.saveSessao(sessaoDTO);

        Assert.isTrue(sessaoControllerResponse.getStatusCode().equals(HttpStatus.OK), "");
    }
}
