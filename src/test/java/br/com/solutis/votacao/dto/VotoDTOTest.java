package br.com.solutis.votacao.dto;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.util.Assert;

@WebMvcTest(VotoDTO.class)
public class VotoDTOTest {

    @Test
    void votoDTOConstrutorTetst() {
        VotoDTO votoDTO = new VotoDTO(1L, 1L, "Sim");

        Assert.isTrue(votoDTO.getVoto().equals("Sim"), "");
    }

    @Test
    void votoDTOGetTetst() {
        VotoDTO votoDTO = new VotoDTO(1L, 1L, "Sim");

        Long sessaoId = votoDTO.getSessaoId();
        Long associadoId = votoDTO.getAssociadoId();
        String voto = votoDTO.getVoto();

        Assert.isTrue(voto.equals("Sim"), "");
        Assert.isTrue(sessaoId.equals(1L), "");
        Assert.isTrue(associadoId.equals(1L), "");
    }
}
