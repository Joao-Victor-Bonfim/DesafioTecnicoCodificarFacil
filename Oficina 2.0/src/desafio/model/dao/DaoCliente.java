package desafio.model.dao;

import desafio.exception.BusinessException;
import desafio.model.domain.Cliente;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor Bonfim
 */
public interface DaoCliente extends Dao<Cliente> {

    public List<Cliente> listarPorNome(String nome) throws PersistenceException, BusinessException;

    public List<Cliente> listarPorId(Integer id) throws PersistenceException, BusinessException;

}
