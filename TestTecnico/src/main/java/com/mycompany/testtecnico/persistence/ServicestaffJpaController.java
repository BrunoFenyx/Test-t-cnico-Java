/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.Servicestaff;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ServicestaffJpaController implements Serializable {

    private EntityManager em;

    public ServicestaffJpaController(EntityManager em) {
        this.em = em;
    }

    public void create(Servicestaff servicestaff) {
        em.getTransaction().begin();
        try {
            em.persist(servicestaff);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicestaff(servicestaff.getId()) != null) {
                throw new RuntimeException("Servicestaff with id " + servicestaff.getId() + " already exists.", ex);
            }
            throw ex;
        }
    }

    public void edit(Servicestaff servicestaff) {
        em.getTransaction().begin();
        try {
            servicestaff = em.merge(servicestaff);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicestaff.getId();
                if (findServicestaff(id) == null) {
                    throw new RuntimeException("The Servicestaff with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void destroy(int id) {
        em.getTransaction().begin();
        try {
            Servicestaff servicestaff;
            try {
                servicestaff = em.getReference(Servicestaff.class, id);
                servicestaff.getId();
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("The Servicestaff with id " + id + " no longer exists.", enfe);
            }
            em.remove(servicestaff);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Servicestaff> findServicestaffEntities() {
        return findServicestaffEntities(true, -1, -1);
    }

    public List<Servicestaff> findServicestaffEntities(int maxResults, int firstResult) {
        return findServicestaffEntities(false, maxResults, firstResult);
    }

    private List<Servicestaff> findServicestaffEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Servicestaff.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public Servicestaff findServicestaff(int id) {
        return em.find(Servicestaff.class, id);
    }

    public int getServicestaffCount() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Servicestaff> rt = cq.from(Servicestaff.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
