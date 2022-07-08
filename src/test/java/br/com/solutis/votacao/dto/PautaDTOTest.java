package br.com.solutis.votacao.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.util.Assert;

@WebMvcTest(PautaDTO.class)
public class PautaDTOTest {

    /**
     * Teste do construtor da classe PautaDTO.
     */
    @Test
    void pautaDTOConstrutorTest() {
        PautaDTO pautaDTO = new PautaDTO("Testando pauta");

        Assert.isTrue(pautaDTO.getTema().equals("Testando pauta"), "");
    }

    @Test
    void pautaDTOGetTest() {
        PautaDTO pautaDTO = new PautaDTO("Testando get pauta");

        String pautaDTOResponse = pautaDTO.getTema();

        Assert.isTrue(pautaDTOResponse.equals("Testando get pauta"), "");
    }

    @Test
    void pautaDTOSetTest() {
        PautaDTO pautaDTO = new PautaDTO("Testando pauta");

        pautaDTO.setTema("Testando set pauta");
        String pautaDTOResponse = pautaDTO.getTema();

        Assert.isTrue(pautaDTOResponse.equals("Testando set pauta"), "");
    }

}
