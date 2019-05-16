package desafio.model.daoimpl;

import desafio.exception.BusinessException;
import desafio.model.dao.DaoOrcamento;
import desafio.model.domain.Orcamento;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor Bonfim
 */
public class DaoOrcamentoImpl extends DaoImpl implements DaoOrcamento {
    
    private static final DaoOrcamentoImpl IMPLEMENTACAO = new DaoOrcamentoImpl();
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(PU);
    
    
    private DaoOrcamentoImpl(){}
    
    public static DaoOrcamentoImpl getInstance(){
        return IMPLEMENTACAO;
    }

    @Override
    public List<Orcamento> listarTodos() throws PersistenceException {
        return EMF.createEntityManager()
                .createNamedQuery("Orcamento.findAll")
                .getResultList();
    }
    
    @Override
    public void inserir(Orcamento novo) throws PersistenceException, BusinessException {
        if(!Orcamento.isValidOrcamento(novo))
            throw new BusinessException("Erro interno ao inserir orçamento." + System.lineSeparator()
                + "O orçamento dado quebra regra de negócio.");
        
        EntityManager em = EMF.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(novo);
        em.getTransaction().commit();
        
        em.close();
    }

    @Override
    public void deletar(Orcamento remover) throws PersistenceException, BusinessException {
        if(!Orcamento.isValidOrcamento(remover))
            throw new BusinessException("Erro interno ao remover orçamento." + System.lineSeparator()
                + "O orçamento dado quebra regra de negócio.");
        
        EntityManager em = EMF.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(remover);
        em.getTransaction().commit();
        
        em.close();
    }

    @Override
    public void atualizar(Orcamento atualizado) throws PersistenceException, BusinessException {
        if(!Orcamento.isValidOrcamento(atualizado))
            throw new BusinessException("Erro interno ao atualizar orçamento." + System.lineSeparator()
                + "O orçamento dado quebra regra de negócio.");
        
        EntityManager em = EMF.createEntityManager();
        
        em.getTransaction().begin();
        em.refresh(atualizado);
        em.getTransaction().commit();
        
        em.close();
    }

    @Override
    public List<Orcamento> listarPorDataEHora(Date dataEHora) throws PersistenceException, BusinessException {
        if(!Orcamento.isValidDataEHora(dataEHora))
            throw new BusinessException("Erro interno ao pesquisar orçamento por data e hora." + System.lineSeparator()
                + "O intante dado quebra regra de negócio.");
        
        return EMF.createEntityManager()
                .createNamedQuery("Orcamento.findByDataEHora")
                .setParameter("dataEHora", dataEHora)
                .getResultList();
    }

    @Override
    public List<Orcamento> listarPorIdOrcamento(Integer idOrcamento) throws PersistenceException, BusinessException {
        if(!Orcamento.isValidIdOrcamento(idOrcamento))
            throw new BusinessException("Erro interno ao pesquisar orçamento por idOrcamento." + System.lineSeparator()
                + "O idOrcamento dado quebra regra de negócio.");
        
        List<Orcamento> retorno = EMF.createEntityManager()
                .createNamedQuery("Orcamento.findByIdOrcamento")
                .setParameter("idOrcamento", idOrcamento)
                .getResultList();
        
        int tam = retorno.size();
        
        if(tam != 0 && tam != 1)
            throw new PersistenceException("Erro interno ao pesquisar por idOrcamento." + System.lineSeparator()
                + "A quantidade de orçamentos obtida quebra regra de negócio.");
        
        return retorno;
    }

    @Override
    public List<Orcamento> listarPorValor(Double valor) throws PersistenceException, BusinessException {
        if(!Orcamento.isValidValor(valor))
            throw new BusinessException("Erro interno ao pesquisar orçamento por valor." + System.lineSeparator()
                + "O valor dado quebra regra de negócio.");
        
        return EMF.createEntityManager()
                .createNamedQuery("Orcamento.findByValor")
                .setParameter("valor", valor)
                .getResultList();
    }
}
