package br.com.solutis.votacao.orm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import java.util.Calendar;

/**
 * Define a estrutura da sessão.
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_sessao")
public class Sessao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aberturaDeSessao")
    private Calendar aberturaDeSessao;

    @Column(name = "tempoSessao")
    private int tempoSessao;

    @OneToOne (fetch = FetchType.LAZY)
    @JsonIgnore
    private Pauta pauta;

}
