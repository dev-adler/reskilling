package br.com.solutis.votacao.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Pattern;

/**
 * Payload de entrada para cadastro de uma pauta.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {
    @NonNull
    private String tema;
}
