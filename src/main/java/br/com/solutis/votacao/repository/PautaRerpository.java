package br.com.solutis.votacao.repository;

import br.com.solutis.votacao.orm.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório da pauta.
 */
@Repository
public interface PautaRerpository extends JpaRepository<Pauta, Long> {

    /**
     * Query para consulta de uma pauta pelo ID.
     * @param id ID da pauta.
     * @return Optional de pauta.
     */
    @Query("Select p from Pauta p where p.id = :codigo")
    Optional<Pauta> querySelectPautaById(@Param("codigo")Long id);
}
