package desafio.model.daoimpl;

import desafio.exception.BusinessException;
import desafio.model.dao.DaoCliente;
import desafio.model.domain.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor Bonfim
 */
public class DaoClienteImpl implements DaoImpl, DaoCliente {

    private static final DaoClienteImpl IMPLEMENTACAO = new DaoClienteImpl();
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(PU);

    private DaoClienteImpl() {
    }

    public static DaoClienteImpl getInstance() {
        return IMPLEMENTACAO;
    }

    @Override
    public List<Cliente> listarTodos() throws PersistenceException {
        return EMF.createEntityManager()
                .createNamedQuery("Cliente.findAll")
                .getResultList();
    }

    @Override
    public List<Cliente> listarPorNome(String nome) throws PersistenceException, BusinessException {
        if (!Cliente.isValidNome(nome)) {
            throw new BusinessException("Erro interno ao pesquisar cliente por nome." + System.lineSeparator()
                    + "O nome dado quebra regra de negócio.");
        }

        return EMF.createEntityManager()
                .createNamedQuery("Cliente.findByNome")
                .setParameter("nome", nome)
                .getResultList();
    }

    @Override
    public List<Cliente> listarPorId(Integer id) throws PersistenceException, BusinessException {
        if (!Cliente.isValidId(id)) {
            throw new BusinessException("Erro interno ao pesquisar cliente por id." + System.lineSeparator()
                    + "O id dado quebra regra de negócio.");
        }

        List<Cliente> retorno = EMF.createEntityManager()
                .createNamedQuery("Cliente.findById")
                .setParameter("id", id)
                .getResultList();

        int tam = retorno.size();

        if (tam != 0 && tam != 1) {
            throw new PersistenceException("Erro interno ao pesquisar por id." + System.lineSeparator()
                    + "A quantidade de clientes obtida quebra regra de negócio.");
        }

        return retorno;
    }

    @Override
    public void inserir(Cliente novo) throws PersistenceException, BusinessException {
        if (!Cliente.isValidCliente(novo)) {
            throw new BusinessException("Erro interno ao inserir cliente." + System.lineSeparator()
                    + "O cliente dado quebra regra de negócio.");
        }

        EntityManager em = EMF.createEntityManager();

        em.getTransaction().begin();
        em.persist(novo);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void deletar(Cliente remover) throws PersistenceException, BusinessException {
        if (!Cliente.isValidCliente(remover)) {
            throw new BusinessException("Erro interno ao remover cliente." + System.lineSeparator()
                    + "O cliente dado quebra regra de negócio.");
        }

        EntityManager em = EMF.createEntityManager();

        em.getTransaction().begin();
        em.remove(remover);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void atualizar(Cliente atualizado) throws PersistenceException, BusinessException {
        if (!Cliente.isValidCliente(atualizado)) {
            throw new BusinessException("Erro interno ao atualizar cliente." + System.lineSeparator()
                    + "O cliente dado quebra regra de negócio.");
        }

        EntityManager em = EMF.createEntityManager();

        em.getTransaction().begin();
        em.refresh(atualizado);
        em.getTransaction().commit();

        em.close();
    }

}
