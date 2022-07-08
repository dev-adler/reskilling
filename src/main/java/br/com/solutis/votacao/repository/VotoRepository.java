package br.com.solutis.votacao.repository;

import br.com.solutis.votacao.orm.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Repositório do voto.
 */
@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    /**
     * Query para consulta do voto pelo ID da sessão e pelo ID do associado.
     * @param id Id da sessão.
     * @param Id Id do associado.
     * @return Collection de voto.
     */
    @Query("Select v from Voto v where v.associado.id = :codigoAssociado and v.sessao.id = :codigoSessao")
    Collection<Voto> querySelectOneVotoWhereId(@Param("codigoAssociado") Long id, @Param("codigoSessao") Long Id);

    /**
     * Query nativa para consulta contabilizada dos votos pelo ID da sessão.
     * @param id ID da sessão.
     * @return Coolection de Object[].
     */
    @Query(value = "SELECT v.voto, count(v.voto) FROM tb_voto v where v.sessao_id =  :codigoSessao group by v.voto order by 2", nativeQuery = true)
    Collection<Object[]> queryVencedorMaioriaSimples(@Param("codigoSessao")Long id);
}
