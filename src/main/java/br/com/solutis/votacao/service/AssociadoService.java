package br.com.solutis.votacao.service;

import br.com.solutis.votacao.exceptionHandler.associado.AssociadoNaoEncontadoException;
import br.com.solutis.votacao.orm.Associado;
import br.com.solutis.votacao.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe de regra de negócio referente ao associado.
 */
@Service
@RequiredArgsConstructor
public class AssociadoService {

    /**
     * Instancia o objeto AssociadoRepository.
     */
    @Autowired
    public AssociadoRepository associadoRepository;

    /**
     * Método para consulta se o associado está cadastrado no banco.
     * @param id ID do associado.
     * @return Retona o associado ou uma exceção caso o associado não seja localizado.
     */
    public Associado findAssociadoById(Long id){
        Optional<Associado> associado = associadoRepository.querySelectAssociadoById(id);
        return associado.orElseThrow(AssociadoNaoEncontadoException::new);
    }
}
