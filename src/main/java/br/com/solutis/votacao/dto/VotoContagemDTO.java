package br.com.solutis.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Estrutura para manipular os dados retornado da consulta de votos.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotoContagemDTO {

    private String voto;
    private BigInteger quantidade;

    /**
     * Builder do objeto a ser manipulado após retorno da consulta.
     * @param objects Objeto a ser construído.
     * @return Lista com o Objeto pronto para manipulação.
     */
    public static List<VotoContagemDTO> buildObject(Collection<Object[]> objects) {
        List<VotoContagemDTO> dtos = new ArrayList<>();
        objects.forEach(objects1 -> dtos.add(new VotoContagemDTO((String) objects1[0], (BigInteger) objects1[1])));
        return dtos;
    }
}
