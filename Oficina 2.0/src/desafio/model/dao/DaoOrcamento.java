package desafio.model.dao;

import desafio.exception.BusinessException;
import desafio.model.domain.Orcamento;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor Bonfim
 */
public interface DaoOrcamento extends Dao<Orcamento>{
    
    public List<Orcamento> listarPorDataEHora(Date dataEHora) throws PersistenceException, BusinessException;
    
    public List<Orcamento> listarPorIdOrcamento(Integer idOrcamento) throws PersistenceException, BusinessException;
    
    public List<Orcamento> listarPorValor(Double valor) throws PersistenceException, BusinessException;
    
}