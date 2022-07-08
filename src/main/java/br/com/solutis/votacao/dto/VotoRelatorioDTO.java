package br.com.solutis.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Map;

/**
 * Estrutura para manipular os dados retornado da consulta consolidada de votos.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotoRelatorioDTO {
    Map<String, BigInteger> vencedor;
    Map<String, BigInteger> totalizador;
    Map<String, String> percentual;
}
