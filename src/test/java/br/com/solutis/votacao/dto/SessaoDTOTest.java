package br.com.solutis.votacao.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.util.Assert;

@WebMvcTest(SessaoDTO.class)
public class SessaoDTOTest {

    @Test
    void sessaoDTOConstrutorTetst() {
        SessaoDTO sessaoDTO = new SessaoDTO(1L, 5);

        Assert.isTrue(sessaoDTO.getPautaId().equals(1L), "");
        Assert.isTrue(sessaoDTO.getTempoSessao() == 5, "");
    }

    @Test
    void sessaoDTOGetTetst() {
        SessaoDTO sessaoDTO = new SessaoDTO(1L, 5);

        Long pautaId = sessaoDTO.getPautaId();
        int temporSessao = sessaoDTO.getTempoSessao();

        Assert.isTrue(pautaId.equals(1L), "");
        Assert.isTrue(temporSessao == 5, "");
    }

    @Test
    void sessaoDTOSetTetst() {
        SessaoDTO sessaoDTO = new SessaoDTO(1L, 5);

        sessaoDTO.setTempoSessao(10);
        sessaoDTO.setPautaId(2L);
        Long pautaId = sessaoDTO.getPautaId();
        int temporSessao = sessaoDTO.getTempoSessao();

        Assert.isTrue(pautaId.equals(2L), "");
        Assert.isTrue(temporSessao == 10, "");
    }

}
