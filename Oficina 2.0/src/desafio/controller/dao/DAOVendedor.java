package desafio.controller.dao;

import desafio.controller.dao.exceptions.IllegalOrphanException;
import desafio.controller.dao.exceptions.NonexistentEntityException;
import desafio.controller.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import desafio.model.domain.Orcamento;
import desafio.model.domain.Vendedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author João Victor Bonfim
 */
public class DAOVendedor implements Serializable {

    public DAOVendedor(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vendedor vendedor) throws PreexistingEntityException, Exception {
        if (vendedor.getOrcamentoList() == null) {
            vendedor.setOrcamentoList(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Orcamento> attachedOrcamentoList = new ArrayList<>();
            for (Orcamento orcamentoListOrcamentoToAttach : vendedor.getOrcamentoList()) {
                orcamentoListOrcamentoToAttach = em.getReference(orcamentoListOrcamentoToAttach.getClass(), orcamentoListOrcamentoToAttach.getId());
                attachedOrcamentoList.add(orcamentoListOrcamentoToAttach);
            }
            vendedor.setOrcamentoList(attachedOrcamentoList);
            em.persist(vendedor);
            for (Orcamento orcamentoListOrcamento : vendedor.getOrcamentoList()) {
                Vendedor oldVendedorOfOrcamentoListOrcamento = orcamentoListOrcamento.getVendedor();
                orcamentoListOrcamento.setVendedor(vendedor);
                orcamentoListOrcamento = em.merge(orcamentoListOrcamento);
                if (oldVendedorOfOrcamentoListOrcamento != null) {
                    oldVendedorOfOrcamentoListOrcamento.getOrcamentoList().remove(orcamentoListOrcamento);
                    oldVendedorOfOrcamentoListOrcamento = em.merge(oldVendedorOfOrcamentoListOrcamento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVendedor(vendedor.getNroRegistro()) != null) {
                throw new PreexistingEntityException("O Vendedor " + vendedor + " já existe.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vendedor vendedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vendedor persistentVendedor = em.find(Vendedor.class, vendedor.getNroRegistro());
            List<Orcamento> orcamentoListOld = persistentVendedor.getOrcamentoList();
            List<Orcamento> orcamentoListNew = vendedor.getOrcamentoList();
            List<String> illegalOrphanMessages = null;
            for (Orcamento oldOrcamento : orcamentoListOld) {
                if (!orcamentoListNew.contains(oldOrcamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("O Orçamento " + oldOrcamento + " deve ser mantido, já que o campo Vendedor não pode ser nulo.");
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
            vendedor.setOrcamentoList(orcamentoListNew);
            vendedor = em.merge(vendedor);
            for (Orcamento orcamentoListNewOrcamento : orcamentoListNew) {
                if (!orcamentoListOld.contains(orcamentoListNewOrcamento)) {
                    Vendedor oldVendedorOfOrcamentoListNewOrcamento = orcamentoListNewOrcamento.getVendedor();
                    orcamentoListNewOrcamento.setVendedor(vendedor);
                    orcamentoListNewOrcamento = em.merge(orcamentoListNewOrcamento);
                    if (oldVendedorOfOrcamentoListNewOrcamento != null && !oldVendedorOfOrcamentoListNewOrcamento.equals(vendedor)) {
                        oldVendedorOfOrcamentoListNewOrcamento.getOrcamentoList().remove(orcamentoListNewOrcamento);
                        oldVendedorOfOrcamentoListNewOrcamento = em.merge(oldVendedorOfOrcamentoListNewOrcamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (IllegalOrphanException ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vendedor.getNroRegistro();
                if (findVendedor(id) == null) {
                    throw new NonexistentEntityException("O Vendedor de id " + id + " não existe mais.");
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
            Vendedor vendedor;
            try {
                vendedor = em.getReference(Vendedor.class, id);
                vendedor.getNroRegistro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("O Vendedor de id " + id + " não existe mais.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Orcamento> orcamentoListOrphanCheck = vendedor.getOrcamentoList();
            for (Orcamento orcamentoListOrphanCheckOrcamento : orcamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("O Vendedor (" + vendedor + ") não pode ser destruído, pois o Orcamento " + orcamentoListOrphanCheckOrcamento + " na sua lista de Orcamentos tem um campo Vendedor não nulo.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(vendedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vendedor> findVendedorEntities() {
        return findVendedorEntities(true, -1, -1);
    }

    public List<Vendedor> findVendedorEntities(int maxResults, int firstResult) {
        return findVendedorEntities(false, maxResults, firstResult);
    }

    private List<Vendedor> findVendedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vendedor.class));
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

    public Vendedor findVendedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vendedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vendedor> rt = cq.from(Vendedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
