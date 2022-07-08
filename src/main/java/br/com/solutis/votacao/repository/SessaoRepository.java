package br.com.solutis.votacao.repository;

import br.com.solutis.votacao.orm.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * Repositório da sessão.
 */
@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    /**
     * Query para consulta de uma sessão pelo ID.
     * @param id ID da sessão.
     * @return Optional da sessão.
     */
    @Query("Select s from Sessao s where s.id = :codigo")
    Optional<Sessao> querySelectOneSessaoWhereId(@Param("codigo") Long id);

    /**
     * Query para consulta de uma sessão pelo ID da pauta.
     * @param id ID da pauta.
     * @return Collection da sessão.
     */
    @Query("Select s from Sessao s where s.pauta.id = :codigo")
    Collection<Sessao> querySelectSessaoByPauta(@Param("codigo")Long id);
}
