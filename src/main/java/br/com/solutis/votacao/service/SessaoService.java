package br.com.solutis.votacao.service;

import br.com.solutis.votacao.dto.SessaoDTO;
import br.com.solutis.votacao.exceptionHandler.sessao.SessaoEncerradaException;
import br.com.solutis.votacao.exceptionHandler.sessao.SessaoJaAbertaException;
import br.com.solutis.votacao.exceptionHandler.sessao.SessaoNaoEncontradaException;
import br.com.solutis.votacao.orm.Pauta;
import br.com.solutis.votacao.orm.Sessao;
import br.com.solutis.votacao.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;

/**
 * Classe de regra de negócio referente à sessão.
 */
@Service
public class SessaoService {

    /**
     * Instancia o objeto SessaoRepository.
     */
    @Autowired
    private final SessaoRepository sessaoRepository;

    /**
     * Instancia o objeto PautaService.
     */
    @Autowired
    public PautaService pautaService;

    /**
     * Construtor da classe SessaoService.
     * @param sessaoRepository Objeto SessaoRepository.
     */
    public SessaoService(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    /**
     * Abre uma sessão validando se a pauta existe e se a sessão já foi aberta.
     * @param sessaoDTO Payload de entrada para abertura de sessão contendo ID
     *                  da sessão e tempo que deverá ficar aberta.
     * @return Objeto de sessão aberta.
     */
    public Sessao saveSessaoService(SessaoDTO sessaoDTO) {

        Pauta pauta = new Pauta();
        pauta.setId(sessaoDTO.getPautaId());

        pautaService.findPautaById(sessaoDTO.getPautaId());
        verificaSessaoAberta(sessaoDTO.getPautaId());
        Sessao sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setAberturaDeSessao(Calendar.getInstance());

        sessao = setTempoDeSessao(sessao, sessaoDTO.getTempoSessao());

        return sessaoRepository.save(sessao);
    }

    /**
     * Método para modificar/definir o tempo que uma sessão deverá ficar aberta.
     * @param sessao Objeto sessão.
     * @param tempoSessao tempo informado pelo usuário.
     * @return Objeto de sessão com o tempo de sessão definido.
     */
    public Sessao setTempoDeSessao(Sessao sessao, int tempoSessao) {

        if (tempoSessao > 0) {
            sessao.setTempoSessao(tempoSessao);
            return sessao;
        } else {
            sessao.setTempoSessao(1);
            return sessao;
        }
    }

    /**
     * Método para verificar se uma sessão existe.
     * @param id ID da sessão.
     * @return Objeto sessão localizado.
     * @throws SessaoNaoEncontradaException Gera excessão caso não localize a sessão.
     */
    public Sessao findSessaoById(Long id) throws SessaoNaoEncontradaException{
        Optional<Sessao> sessao =  sessaoRepository.querySelectOneSessaoWhereId(id);
        return sessao.orElseThrow(SessaoNaoEncontradaException::new);
    }

    /**
     * Método para verificar se uma sessão já foi aberta. Gera exceção caso positivo.
     * @param pautaId ID da pauta.
     */
    public void verificaSessaoAberta(Long pautaId){
        Collection<Sessao> sessao = sessaoRepository.querySelectSessaoByPauta(pautaId);
        if(!sessao.isEmpty()){
            throw new SessaoJaAbertaException("Esta sessão já foi aberta.");
        }
    }

    /**
     * Método para verificar se uma sessão ainda está aberta. Gera exceção caso o associado
     * tente registrar um voto na sessão encerrada.
     * @param sessaoId ID da sessão.
     */
    public void verificaSeSessaoAindaEstaAberta(Long sessaoId){
        Optional<Sessao> sessao = sessaoRepository.querySelectOneSessaoWhereId(sessaoId);
        Calendar agora = Calendar.getInstance();
        Calendar fimSessao = sessao.get().getAberturaDeSessao();
        fimSessao.add(Calendar.MINUTE, sessao.get().getTempoSessao());

        if(fimSessao.before(agora)){
            throw new SessaoEncerradaException("Voto não computado. Sessão encerrada.");
        }
    }

}