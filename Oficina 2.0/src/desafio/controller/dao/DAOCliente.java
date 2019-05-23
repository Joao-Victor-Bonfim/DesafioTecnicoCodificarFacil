package desafio.controller.dao;

import desafio.controller.dao.exceptions.IllegalOrphanException;
import desafio.controller.dao.exceptions.NonexistentEntityException;
import desafio.model.domain.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import desafio.model.domain.Orcamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author João Victor Bonfim
 */
public class DAOCliente implements Serializable {

    public DAOCliente(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getOrcamentoList() == null) {
            cliente.setOrcamentoList(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Orcamento> attachedOrcamentoList = new ArrayList<>();
            for (Orcamento orcamentoListOrcamentoToAttach : cliente.getOrcamentoList()) {
                orcamentoListOrcamentoToAttach = em.getReference(orcamentoListOrcamentoToAttach.getClass(), orcamentoListOrcamentoToAttach.getId());
                attachedOrcamentoList.add(orcamentoListOrcamentoToAttach);
            }
            cliente.setOrcamentoList(attachedOrcamentoList);
            em.persist(cliente);
            for (Orcamento orcamentoListOrcamento : cliente.getOrcamentoList()) {
                Cliente oldClienteOfOrcamentoListOrcamento = orcamentoListOrcamento.getCliente();
                orcamentoListOrcamento.setCliente(cliente);
                orcamentoListOrcamento = em.merge(orcamentoListOrcamento);
                if (oldClienteOfOrcamentoListOrcamento != null) {
                    oldClienteOfOrcamentoListOrcamento.getOrcamentoList().remove(orcamentoListOrcamento);
                    oldClienteOfOrcamentoListOrcamento = em.merge(oldClienteOfOrcamentoListOrcamento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId());
            List<Orcamento> orcamentoListOld = persistentCliente.getOrcamentoList();
            List<Orcamento> orcamentoListNew = cliente.getOrcamentoList();
            List<String> illegalOrphanMessages = null;
            for (Orcamento oldOrcamento : orcamentoListOld) {
                if (!orcamentoListNew.contains(oldOrcamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("O Orçamento " + oldOrcamento + " deve ser mantido, já que o campo em Cliente é não nulo.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Orcamento> attachedOrcamentoListNew = new ArrayList<>();
            for (Orcamento orcamentoListNewOrcamentoToAttach : orcamentoListNew) {
                orcamentoListNewOrcamentoToAttach = em.getReference(orcamentoListNewOrcamentoToAttach.getClass(), orcamentoListNewOrcamentoToAttach.getId());
                attachedOrcamentoListNew.add(orcamentoListNewOrcamentoToAttach);
            }
            orcamentoListNew = attachedOrcamentoListNew;
            cliente.setOrcamentoList(orcamentoListNew);
            cliente = em.merge(cliente);
            for (Orcamento orcamentoListNewOrcamento : orcamentoListNew) {
                if (!orcamentoListOld.contains(orcamentoListNewOrcamento)) {
                    Cliente oldClienteOfOrcamentoListNewOrcamento = orcamentoListNewOrcamento.getCliente();
                    orcamentoListNewOrcamento.setCliente(cliente);
                    orcamentoListNewOrcamento = em.merge(orcamentoListNewOrcamento);
                    if (oldClienteOfOrcamentoListNewOrcamento != null && !oldClienteOfOrcamentoListNewOrcamento.equals(cliente)) {
                        oldClienteOfOrcamentoListNewOrcamento.getOrcamentoList().remove(orcamentoListNewOrcamento);
                        oldClienteOfOrcamentoListNewOrcamento = em.merge(oldClienteOfOrcamentoListNewOrcamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (IllegalOrphanException ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getId();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("O Cliente com id " + id + " não existe mais.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("O Cliente com id " + id + " não existe mais.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Orcamento> orcamentoListOrphanCheck = cliente.getOrcamentoList();
            for (Orcamento orcamentoListOrphanCheckOrcamento : orcamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("O Cliente (" + cliente + ") não pode ser destruído, já que o Orçamento " + orcamentoListOrphanCheckOrcamento + " na lista de Orçamentos tem um campo Cliente não nulo.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
