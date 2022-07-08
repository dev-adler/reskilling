package br.com.solutis.votacao.api;

import br.com.solutis.votacao.dto.VotoDTO;
import br.com.solutis.votacao.repository.VotoRepository;
import br.com.solutis.votacao.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * API do voto.
 */
@RestController
@RequestMapping(value = "/voto")
@RequiredArgsConstructor
public class VotoController {

    /**
     * Instancia o objeto VotoService.
     */
    @Autowired
    private VotoService votoService;

    /**
     * Instancia o objeto VotoRepository.
     */
    @Autowired
    private VotoRepository votoRepository;

    /**
     * Endepoint para registro de um novo voto.
     * @param votoDTO Payload de entrada contendo ID do associado, Id da sessão e o voto (Sim/Não).
     * @return Payload de retorno contendo o Id do voto, o voto (Sim/Não) e a data do voto.
     */
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveVoto(@RequestBody @Valid VotoDTO votoDTO) {

        return new ResponseEntity(votoService.saveVotoService(votoDTO), HttpStatus.OK);
    }

    /**
     * Endpoint para consulta de vencedor de uma determinada sessão.
     * @param id Id da sessão a se contabilizada.
     * @return Payload de retorno contendo o voto vencedor (Sim/Não) e a seu totalizador.
     */
    @GetMapping(value = "/vencedor/{id}")
    public ResponseEntity getVotoVencedor(@PathVariable Long id){

        return new ResponseEntity(votoService.vencedorMaioriaSimples(id), HttpStatus.OK);
    }

    /**
     * Endpoint para consulta dos total dos votos computados.
     * @param id ID da sessão a ser contabilizada.
     * @return Payload de retorno contendo os votos (Sim/Não) e seus totalizadores.
     */
    @GetMapping(value="/computados/{id}")
    public  ResponseEntity getVotosComputados(@PathVariable Long id){

        return new ResponseEntity(votoService.votosComputados(id), HttpStatus.OK);
    }

    /**
     * Endpoint para consulta do percentual dos votos computados.
     * @param id ID da sessão a ser contabilizada.
     * @return Payload de retorno contendo os votos (Sim/Não) e seus percentuais.
     */
    @GetMapping(value="/percentual/{id}")
    public  ResponseEntity getPercentualVotosComputados(@PathVariable Long id){

        return new ResponseEntity(votoService.percentualVotosComputados(id), HttpStatus.OK);
    }

    /**
     * Endpoint para consulta consolidada dos dados da votação.
     * @param id ID da sessão a ser contabilizada.
     * @return Payload de retorno contendo a definição do vencedor com o total de votos
     * e os votos (Sim/Não) com seus totalizadores e seus percentuais.
     */
    @GetMapping(value = "/consolidado/{id}")
    public ResponseEntity getRelatorioConsolidadoDosVotosDaPauta(@PathVariable Long id){

        return new ResponseEntity(votoService.relatorioConsolidadoDosVotos(id), HttpStatus.OK);
    }

}
