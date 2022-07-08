package br.com.solutis.votacao.service;

import br.com.solutis.votacao.exceptionHandler.sessao.SessaoNaoEncontradaException;
import br.com.solutis.votacao.orm.Pauta;
import br.com.solutis.votacao.orm.Sessao;
import br.com.solutis.votacao.repository.SessaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SessaoServiceTest {

    @InjectMocks
    SessaoService sessaoService;

    @Spy
    SessaoRepository sessaoRepository;

    @MockBean
    PautaService pautaService;

    @Test
    void setTempoDeSessaoTest(){
        Sessao sessao = new Sessao();
        int tempoSessao = 2;

        Sessao sessaoResponse = sessaoService.setTempoDeSessao(sessao, tempoSessao);

        Assert.isTrue(sessaoResponse.getTempoSessao() == 2, "");
    }

    @Test
    void findSessaoByIdTest() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        given(sessaoRepository.querySelectOneSessaoWhereId(anyLong()))
                .willReturn(Optional.of(Sessao.builder().aberturaDeSessao(Calendar.getInstance()).pauta(pauta).id(1L).build()));

        Sessao sessao = sessaoService.findSessaoById(1L);

        Assert.isTrue(sessao.getId().equals(1L), "");
    }

    @Test
    @ExceptionHandler(SessaoNaoEncontradaException.class)
    void findSessaoByIdExceptionTest() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        given(sessaoRepository.querySelectOneSessaoWhereId(anyLong()))
                .willReturn(Optional.of(Sessao.builder().aberturaDeSessao(Calendar.getInstance()).pauta(pauta).id(1L).build()));

        Sessao sessao = sessaoService.findSessaoById(2L);
    }

    @Test
    void verificaSessaoAbertaTest() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        Sessao sessao = new Sessao();
        sessao.setId(1L);
        sessao.setAberturaDeSessao(Calendar.getInstance());
        sessao.setTempoSessao(2);
        sessao.setPauta(pauta);
        Collection<Sessao> sessaoCollection = new ArrayList<>();
        sessaoCollection.add(sessao);

        given(sessaoRepository.querySelectSessaoByPauta(anyLong())).willReturn(sessaoCollection);

        try {
            sessaoService.verificaSessaoAberta(1L);
        } catch (Exception e){
            Assert.isTrue(e.getMessage().equals("Esta sessão já foi aberta."), "");
        }
    }

    @Test
    void verificaSeSessaoAindaEstaAbertaTest() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        Calendar abertura = Calendar.getInstance();
        abertura.add(Calendar.MINUTE, -5);

        Sessao sessao = new Sessao();
        sessao.setId(1L);
        sessao.setAberturaDeSessao(abertura);
        sessao.setTempoSessao(1);
        sessao.setPauta(pauta);

        given(sessaoRepository.querySelectOneSessaoWhereId(anyLong())).
                willReturn(Optional.of(sessao));

        try {
            sessaoService.verificaSeSessaoAindaEstaAberta(1L);
        } catch (Exception e){
            Assert.isTrue(e.getMessage().equals("Voto não computado. Sessão encerrada."), "");
        }
    }
}
