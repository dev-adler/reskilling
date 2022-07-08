package br.com.solutis.votacao.api;

import br.com.solutis.votacao.dto.PautaDTO;
import br.com.solutis.votacao.orm.Pauta;
import br.com.solutis.votacao.service.PautaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(PautaController.class)
public class PautaControllerTest {

    @MockBean
    PautaService pautaService;

    @Autowired
    private PautaController pautaController;

    @Test
    void savePautaTest() throws Exception {
        PautaDTO pautaDTO = new PautaDTO();

        pautaDTO.setTema("Teste unitário de pauta");

        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setTema("Teste unitário de pauta");

        when(pautaService.savePautaService(pautaDTO)).thenReturn(pauta);

        ResponseEntity pautaControllerResponse =  pautaController.savePauta(pautaDTO);

        Assert.isTrue(pautaControllerResponse.getStatusCode().equals(HttpStatus.OK), "");
    }
}
