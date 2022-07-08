package br.com.solutis.votacao.service;

import br.com.solutis.votacao.dto.PautaDTO;
import br.com.solutis.votacao.exceptionHandler.pauta.PautaNaoEncontradaException;
import br.com.solutis.votacao.exceptionHandler.pauta.PautaSemTemaException;
import br.com.solutis.votacao.orm.Pauta;
import br.com.solutis.votacao.repository.PautaRerpository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe de regra de negócio referente à pauta.
 */
@Service
@AllArgsConstructor
@Slf4j
public class PautaService {

    /**
     * Instancia o objeto PautaRerpository.
     */
    @Autowired
    private PautaRerpository pautaRerpository;

    public List<Pauta> findAll() {
        return pautaRerpository.findAll();
    }

    /**
     * Salva uma nova pauta no banco de dados validando o tema para evitar pautas
     * cadastradas sem temas.
     * @param pautaDTO Payload de entrada da API para gravação da pauta contendo o tema.
     * @return Retorna o objeto Pauta.
     */
    public Pauta savePautaService(PautaDTO pautaDTO) throws PautaSemTemaException{
        validaSeTemaDaPautaEstaPreenchido(pautaDTO.getTema());
        Pauta pauta = new Pauta();
        pauta.setTema(pautaDTO.getTema());

        return pautaRerpository.save(pauta);
    }

    /**
     * Verifica se uma pauta já existe no banco de dados.
     * @param id Recebe como parâmetro o ID da pauta.
     * @return Retorna o objeto Pauta.
     */
    public Pauta findPautaById(Long id){
        Optional<Pauta> pauta = pautaRerpository.querySelectPautaById(id);
        return pauta.orElseThrow(PautaNaoEncontradaException::new);
    }

    /**
     * Método responsável pela validação do tema da pauta. Verifica se o mesmo não é nulo
     * e lança uma exceção caso seja.
     * @param tema Recebe como parâmentro o tema digitado pelo usuário.
     */
    public void validaSeTemaDaPautaEstaPreenchido(String tema){
        if(tema.isEmpty()){
            throw new PautaSemTemaException();
        }
    }
}
