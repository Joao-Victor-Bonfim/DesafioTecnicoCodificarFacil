package desafio.model.daoimpl;

import desafio.exception.BusinessException;
import desafio.model.dao.DaoVendedor;
import static desafio.model.daoimpl.DaoImpl.PU;
import desafio.model.domain.Vendedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor Bonfim
 */
public class DaoVendedorImpl implements DaoImpl, DaoVendedor{

    private static final DaoVendedorImpl IMPLEMENTACAO = new DaoVendedorImpl();
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(PU);
    
    
    private DaoVendedorImpl(){}
    
    public static DaoVendedorImpl getInstance(){
        return IMPLEMENTACAO;
    }

    @Override
    public List<Vendedor> listarTodos() throws PersistenceException {
        return EMF.createEntityManager()
                .createNamedQuery("Vendedor.findAll")
                .getResultList();
    }

    @Override
    public List<Vendedor> listarPorNome(String nome) throws PersistenceException, BusinessException {
        if(!Vendedor.isValidNome(nome))
            throw new BusinessException("Erro interno ao pesquisar vendedor por nome." + System.lineSeparator()
                + "O nome dado quebra regra de negócio.");
        
        return EMF.createEntityManager()
                .createNamedQuery("Vendedor.findByNome")
                .setParameter("nome", nome)
                .getResultList();
    }

    @Override
    public List<Vendedor> listarPorNroRegistro(Integer nroRegistro) throws PersistenceException, BusinessException {
        if(!Vendedor.isValidNroRegistro(nroRegistro))
            throw new BusinessException("Erro interno ao pesquisar vendedor por nroRegistro." + System.lineSeparator()
                + "O nroRegistro dado quebra regra de negócio.");
        
        List<Vendedor> retorno = EMF.createEntityManager()
                .createNamedQuery("Vendedor.findByNroRegistro")
                .setParameter("nroRegistro", nroRegistro)
                .getResultList();
        
        int tam = retorno.size();
        
        if(tam != 0 && tam != 1)
            throw new PersistenceException("Erro interno ao pesquisar por nroRegistro." + System.lineSeparator()
                + "A quantidade de vendedores obtida quebra regra de negócio.");
        
        return retorno;
    }
    
    @Override
    public void inserir(Vendedor novo) throws PersistenceException, BusinessException {
        if(!Vendedor.isValidVendedor(novo))
            throw new BusinessException("Erro interno ao inserir vendedor." + System.lineSeparator()
                + "O vendedor dado quebra regra de negócio.");
        
        EntityManager em = EMF.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(novo);
        em.getTransaction().commit();
        
        em.close();
    }

    @Override
    public void deletar(Vendedor remover) throws PersistenceException, BusinessException {
        if(!Vendedor.isValidVendedor(remover))
            throw new BusinessException("Erro interno ao remover vendedor." + System.lineSeparator()
                + "O vendedor dado quebra regra de negócio.");
        
        EntityManager em = EMF.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(remover);
        em.getTransaction().commit();
        
        em.close();
    }

    @Override
    public void atualizar(Vendedor atualizado) throws PersistenceException, BusinessException {
        if(!Vendedor.isValidVendedor(atualizado))
            throw new BusinessException("Erro interno ao atualizar vendedor." + System.lineSeparator()
                + "O vendedor dado quebra regra de negócio.");
        
        EntityManager em = EMF.createEntityManager();
        
        em.getTransaction().begin();
        em.refresh(atualizado);
        em.getTransaction().commit();
        
        em.close();
    }
}
