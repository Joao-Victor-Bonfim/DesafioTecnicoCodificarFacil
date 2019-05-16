package desafio.model.dao;

import desafio.exception.BusinessException;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor Bonfim
 * @param <Domain>
 */
public interface Dao <Domain>{
    
    public List<Domain> listarTodos() throws PersistenceException;
    
    public void inserir(Domain novo) throws PersistenceException, BusinessException;
    
    public void deletar(Domain remover) throws PersistenceException, BusinessException;
    
    public void atualizar(Domain atualizado) throws PersistenceException, BusinessException;
}
