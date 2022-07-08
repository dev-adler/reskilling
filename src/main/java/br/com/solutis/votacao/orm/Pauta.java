package br.com.solutis.votacao.orm;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * Define a estrutura da pauta.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "tb_pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tema")
    private String tema;

    @Builder
    public Pauta(Long id, String tema) {
        this.id = id;
        this.tema = tema;
    }
}

