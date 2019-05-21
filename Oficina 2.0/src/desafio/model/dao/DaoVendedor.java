package desafio.model.dao;

import desafio.exception.BusinessException;
import desafio.model.domain.Vendedor;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor Bonfim
 */
public interface DaoVendedor extends Dao<Vendedor> {

    public List<Vendedor> listarPorNome(String nome) throws PersistenceException, BusinessException;

    public List<Vendedor> listarPorNroRegistro(Integer nroRegistro) throws PersistenceException, BusinessException;
}
