package br.com.solutis.votacao.orm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Define a estrutura do voto.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_voto")
public class Voto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Sessao sessao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Associado associado;

    @Column(name = "voto")
    private String voto;

    @Column(name = "dataVoto")
    private Calendar dataVoto;
}
