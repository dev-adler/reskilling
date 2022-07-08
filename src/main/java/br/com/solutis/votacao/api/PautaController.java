package br.com.solutis.votacao.api;

import br.com.solutis.votacao.dto.PautaDTO;
import br.com.solutis.votacao.service.PautaService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * API da pauta.
 */
@RestController
@RequestMapping(value = "/pauta")
@RequiredArgsConstructor
@Slf4j
@Api(value = "Teste")
public class PautaController {

    /**
     * Instancia o objeto PautaService.
     */
    @Autowired
    private PautaService pautaService;

    /**
     * Endpoint para salvar um pauta no banco de dados.
     *
     * @param pautaDTO Payload de entrada contendo o tema para o cadastro da pauta.
     * @return Paylad de resposta confirmando que a pauta foi salva com o ID e o tema.
     */
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity savePauta(@RequestBody PautaDTO pautaDTO) {

        return new ResponseEntity(pautaService.savePautaService(pautaDTO), HttpStatus.OK);
    }

}
