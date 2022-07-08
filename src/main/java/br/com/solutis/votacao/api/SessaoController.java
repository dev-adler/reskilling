package br.com.solutis.votacao.api;

import br.com.solutis.votacao.dto.SessaoDTO;
import br.com.solutis.votacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API da sessão.
 */
@RestController
@RequestMapping(value = "/sessao")
@RequiredArgsConstructor
public class SessaoController {

    /**
     * Instancia o objeto SessaoService..
     */
    @Autowired
    private SessaoService sessaoService;

    /**
     * Endepoint de abertura de sessao para a pauta.
     * @param sessaoDTO Payload de entrada contendo ID da pauta e tempo de sessão.
     * @return Payload de retorno contendo ID da sessão, data da abertura e tempo da sessão.
     */
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveSessao(@RequestBody SessaoDTO sessaoDTO) {

        return new ResponseEntity(sessaoService.saveSessaoService(sessaoDTO), HttpStatus.OK);
    }
}
