/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.Section;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SectionJpaController implements Serializable {

    private EntityManager em;

    public SectionJpaController(EntityManager em) {
        this.em = em;
    }

    public void create(Section section) {
        em.getTransaction().begin();
        try {
            em.persist(section);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSection(section.getId()) != null) {
                throw new RuntimeException("Section with id " + section.getId() + " already exists.", ex);
            }
            throw ex;
        }
    }

    public void edit(Section section) {
        em.getTransaction().begin();
        try {
            section = em.merge(section);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = section.getId();
                if (findSection(id) == null) {
                    throw new RuntimeException("The Section with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void destroy(int id) {
        em.getTransaction().begin();
        try {
            Section section;
            try {
                section = em.getReference(Section.class, id);
                section.getId();
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("The Section with id " + id + " no longer exists.", enfe);
            }
            em.remove(section);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Section> findSectionEntities() {
        return findSectionEntities(true, -1, -1);
    }

    public List<Section> findSectionEntities(int maxResults, int firstResult) {
        return findSectionEntities(false, maxResults, firstResult);
    }

    private List<Section> findSectionEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Section.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public Section findSection(int id) {
        return em.find(Section.class, id);
    }

    public int getSectionCount() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Section> rt = cq.from(Section.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
/*public class SectionJpaController implements Serializable {

    public SectionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public void create(Section section) {
        EntityManager em = null;
        try {
            em=getEntityManager();
            em.getTransaction().begin();
            em.persist(section);
            em.getTransaction().commit();
        } finally {
            if (em !=null) {
                em.close();
            }
        }
    }

    public void edit(Section section) {
        EntityManager em = null;
        try {
            section = em.merge(section);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = section.getId();
                if (findSection(id) == null) {
                    throw new RuntimeException("The Section with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void destroy(int id) {
        EntityManager em = null;
        try {
            Section section;
            try {
                section = em.getReference(Section.class, id);
                section.getId();
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("The Section with id " + id + " no longer exists.", enfe);
            }
            em.remove(section);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Section> findSectionEntities() {
        return findSectionEntities(true, -1, -1);
    }

    public List<Section> findSectionEntities(int maxResults, int firstResult) {
        return findSectionEntities(false, maxResults, firstResult);
    }

    private List<Section> findSectionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Section.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public Section findSection(int id) {
        EntityManager em = getEntityManager();
        return em.find(Section.class, id);
    }

    public int getSectionCount() {
        EntityManager em = getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Section> rt = cq.from(Section.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}*/