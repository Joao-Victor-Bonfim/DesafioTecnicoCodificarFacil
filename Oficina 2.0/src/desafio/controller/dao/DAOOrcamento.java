package desafio.controller.dao;

import desafio.controller.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import desafio.model.domain.Cliente;
import desafio.model.domain.Orcamento;
import desafio.model.domain.Vendedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author João Victor Bonfim
 */
public class DAOOrcamento implements Serializable {

    public DAOOrcamento(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orcamento orcamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = orcamento.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                orcamento.setCliente(cliente);
            }
            Vendedor vendedor = orcamento.getVendedor();
            if (vendedor != null) {
                vendedor = em.getReference(vendedor.getClass(), vendedor.getNroRegistro());
                orcamento.setVendedor(vendedor);
            }
            em.persist(orcamento);
            if (cliente != null) {
                cliente.getOrcamentoList().add(orcamento);
                cliente = em.merge(cliente);
            }
            if (vendedor != null) {
                vendedor.getOrcamentoList().add(orcamento);
                vendedor = em.merge(vendedor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orcamento orcamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orcamento persistentOrcamento = em.find(Orcamento.class, orcamento.getId());
            Cliente clienteOld = persistentOrcamento.getCliente();
            Cliente clienteNew = orcamento.getCliente();
            Vendedor vendedorOld = persistentOrcamento.getVendedor();
            Vendedor vendedorNew = orcamento.getVendedor();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                orcamento.setCliente(clienteNew);
            }
            if (vendedorNew != null) {
                vendedorNew = em.getReference(vendedorNew.getClass(), vendedorNew.getNroRegistro());
                orcamento.setVendedor(vendedorNew);
            }
            orcamento = em.merge(orcamento);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getOrcamentoList().remove(orcamento);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getOrcamentoList().add(orcamento);
                clienteNew = em.merge(clienteNew);
            }
            if (vendedorOld != null && !vendedorOld.equals(vendedorNew)) {
                vendedorOld.getOrcamentoList().remove(orcamento);
                vendedorOld = em.merge(vendedorOld);
            }
            if (vendedorNew != null && !vendedorNew.equals(vendedorOld)) {
                vendedorNew.getOrcamentoList().add(orcamento);
                vendedorNew = em.merge(vendedorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orcamento.getId();
                if (findOrcamento(id) == null) {
                    throw new NonexistentEntityException("The orcamento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orcamento orcamento;
            try {
                orcamento = em.getReference(Orcamento.class, id);
                orcamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orcamento with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = orcamento.getCliente();
            if (cliente != null) {
                cliente.getOrcamentoList().remove(orcamento);
                cliente = em.merge(cliente);
            }
            Vendedor vendedor = orcamento.getVendedor();
            if (vendedor != null) {
                vendedor.getOrcamentoList().remove(orcamento);
                vendedor = em.merge(vendedor);
            }
            em.remove(orcamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orcamento> findOrcamentoEntities() {
        return findOrcamentoEntities(true, -1, -1);
    }

    public List<Orcamento> findOrcamentoEntities(int maxResults, int firstResult) {
        return findOrcamentoEntities(false, maxResults, firstResult);
    }

    private List<Orcamento> findOrcamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orcamento.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Orcamento findOrcamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orcamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrcamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orcamento> rt = cq.from(Orcamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
