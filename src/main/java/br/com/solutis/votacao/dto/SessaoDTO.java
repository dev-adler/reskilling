package br.com.solutis.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Payload de entrada para abertura de uma sessão.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessaoDTO {
    private Long pautaId;
    private int tempoSessao;
}
