package br.com.solutis.votacao.repository;

import br.com.solutis.votacao.orm.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório do associado.
 */
@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    /**
     * Query para consulta do associado pelo ID.
     * @param id ID do associado.
     * @return Optional de associado.
     */
    @Query("Select a from Associado a where a.id = :codigo")
    Optional<Associado> querySelectAssociadoById(@Param("codigo") Long id);
}
