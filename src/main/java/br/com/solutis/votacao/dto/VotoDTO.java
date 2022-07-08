package br.com.solutis.votacao.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Payload de entrada para registro de um voto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO {
    @NonNull
    private Long sessaoId;

    @NonNull
    private Long associadoId;

    @NonNull
    private String voto;
}
