package br.com.solutis.votacao.service;

import br.com.solutis.votacao.dto.VotoContagemDTO;
import br.com.solutis.votacao.dto.VotoDTO;
import br.com.solutis.votacao.dto.VotoRelatorioDTO;
import br.com.solutis.votacao.exceptionHandler.sessao.SessaoNaoEncontradaException;
import br.com.solutis.votacao.exceptionHandler.voto.EmpateException;
import br.com.solutis.votacao.exceptionHandler.voto.VotoJaComputadoException;
import br.com.solutis.votacao.exceptionHandler.voto.VotoNaoPermitidoException;
import br.com.solutis.votacao.orm.Associado;
import br.com.solutis.votacao.orm.Sessao;
import br.com.solutis.votacao.orm.Voto;
import br.com.solutis.votacao.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

/**
 * Classe de regra de negócio referente ao voto.
 */
@Service
@RequiredArgsConstructor
public class VotoService {

    /**
     * Instancia o objeto VotoRepository.
     */
    @Autowired
    public VotoRepository votoRepository;

    /**
     * Instancia o objeto SessaoService.
     */
    @Autowired
    public SessaoService sessaoService;

    /**
     * Instancia o objeto AssociadoService.
     */
    @Autowired
    public AssociadoService associadoService;

    /**
     * Registra um voto e persiste no banco de dados validando se a sessão existe, se a sessão
     * ainda está aberta, se o associado existe e se o voto do associado já foi computado para
     * a pauta.
     * @param votoDTO Payload de entrada para registro de um voto.
     * @return Objeto de voto.
     * @throws SessaoNaoEncontradaException Exceção caso não seja encontrada.
     * @throws VotoJaComputadoException Exceção caso o voto já tenha sido computado para este
     * associado.
     */
    public Voto saveVotoService(VotoDTO votoDTO) throws SessaoNaoEncontradaException, VotoJaComputadoException {
        Sessao sessao = new Sessao();
        sessao.setId(votoDTO.getSessaoId());

        Associado associado = new Associado();
        associado.setId(votoDTO.getAssociadoId());

        sessaoService.findSessaoById(votoDTO.getSessaoId());
        sessaoService.verificaSeSessaoAindaEstaAberta(votoDTO.getSessaoId());
        associadoService.findAssociadoById(votoDTO.getAssociadoId());
        verificaVotoJaComputado(votoDTO.getAssociadoId(), votoDTO.getSessaoId());
        validaVoto(votoDTO.getVoto());

        Voto voto = new Voto();
        voto.setSessao(sessao);
        voto.setAssociado(associado);
        voto.setVoto(votoDTO.getVoto());
        voto.setDataVoto(Calendar.getInstance());

        return votoRepository.save(voto);
    }

    /**
     * Método para verificar se o voto já foi computado para o associado em questão.
     * @param associadoId ID do associado.
     * @param sessaoId ID da sessão.
     * @throws VotoJaComputadoException Exceção gerada caso o associado já tenha votado na pauta.
     */
    public void verificaVotoJaComputado(Long associadoId, Long sessaoId) throws VotoJaComputadoException {
        Collection<Voto> voto = votoRepository.querySelectOneVotoWhereId(associadoId, sessaoId);
        if (!voto.isEmpty()) {
            throw new VotoJaComputadoException();
        }
    }

    /**
     * Método para calcular o vencedor de uma pauta.
     * @param id ID da sessão.
     * @return Mapa de String BigInteger contendo o voto com maior quatidade de votos.
     */
    public Map<String, BigInteger> vencedorMaioriaSimples(Long id) {
        sessaoService.findSessaoById(id);
        Map<String, BigInteger> resultado = new HashMap<>();
        Collection<Object[]> objects = votoRepository.queryVencedorMaioriaSimples(id);

        List<VotoContagemDTO> votos = VotoContagemDTO.buildObject(objects);
        if(votos.size() > 1) {
            if (votos.get(0).getQuantidade().compareTo(votos.get(1).getQuantidade()) == 0) {
                throw new EmpateException();
            } else if (votos.get(0).getQuantidade().compareTo(votos.get(1).getQuantidade()) == 1) {
                resultado.put(votos.get(0).getVoto(), votos.get(0).getQuantidade());
                return resultado;
            } else {
                resultado.put(votos.get(1).getVoto(), votos.get(1).getQuantidade());
                return resultado;
            }
        }
        resultado.put(votos.get(0).getVoto(), votos.get(0).getQuantidade());
        return resultado;
    }

    /**
     * Método para contabilizar os votos de uma pauta.
     * @param id ID da sessão.
     * @return Mapa de String BigInteger contendo o voto (Sim/Não) com quatidade de votos.
     */
    public Map<String, BigInteger> votosComputados(Long id) {
        sessaoService.findSessaoById(id);
        Map<String, BigInteger> resultado = new HashMap<>();
        Collection<Object[]> objects = votoRepository.queryVencedorMaioriaSimples(id);

        List<VotoContagemDTO> votos = VotoContagemDTO.buildObject(objects);

        votos.forEach(v -> resultado.put(v.getVoto(), v.getQuantidade()));

        return resultado;
    }

    /**
     * Método para contabilizar o percentual de votos de uma pauta.
     * @param id ID da sessão.
     * @return Mapa de String BigInteger contendo o voto (Sim/Não) com percentual de votos.
     */
    public Map<String, String> percentualVotosComputados(Long id) {
        sessaoService.findSessaoById(id);
        Map<String, String> resultado = new HashMap<>();
        Collection<Object[]> objects = votoRepository.queryVencedorMaioriaSimples(id);

        BigInteger totalVotos;
        List<VotoContagemDTO> votos = VotoContagemDTO.buildObject(objects);
        if(votos.size() > 1){
            totalVotos = votos.get(0).getQuantidade().add(votos.get(1).getQuantidade());
        } else  {
            totalVotos = votos.get(0).getQuantidade();
        }
        final BigInteger finalTotalVotos = totalVotos;
        votos.forEach(v -> resultado.put(v.getVoto(),
                String.format("%.0f%%",((v.getQuantidade().doubleValue()/(finalTotalVotos.doubleValue()))*100))));
        return resultado;
    }

    /**
     * Método que retorna o relatório consolidado dos votos exibindo vencedor por maioria simples,
     * contagem e percentual dos votos (Sim/Não) de uma pauta.
     * @param id ID da sessão.
     * @return Lista de VotoRelatorioDTO contendo vencedor por maioria simples, contagem e percentual
     * dos votos (Sim/Não) de uma pauta.
     */
    public List<VotoRelatorioDTO> relatorioConsolidadoDosVotos(Long id) {
        sessaoService.findSessaoById(id);
        List<VotoRelatorioDTO> resultado = new ArrayList<>();

        VotoRelatorioDTO votoRelatorioDTO = new VotoRelatorioDTO();

        votoRelatorioDTO.setVencedor(vencedorMaioriaSimples(id));
        votoRelatorioDTO.setTotalizador(votosComputados(id));
        votoRelatorioDTO.setPercentual(percentualVotosComputados(id));

        resultado.add(votoRelatorioDTO);

        return resultado;
    }

    /**
     * Valida se o voto digitado é Sim ou Não.
     * @param voto Voto do associado.
     */
    public void validaVoto(String voto){
        if(!(voto.equals("Sim") || voto.equals("Não"))) {
            throw new VotoNaoPermitidoException();
        }
    }
}
