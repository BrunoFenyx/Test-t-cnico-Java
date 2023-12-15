/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.CivilStatus;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CivilStatusJpaController implements Serializable {

    private EntityManager em;

    public CivilStatusJpaController(EntityManager em) {
        this.em = em;
    }

    public void create(CivilStatus civilStatus) {
        em.getTransaction().begin();
        try {
            em.persist(civilStatus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCivilStatus(civilStatus.getId()) != null) {
                throw new RuntimeException("CivilStatus with id " + civilStatus.getId() + " already exists.", ex);
            }
            throw ex;
        }
    }

    public void edit(CivilStatus civilStatus) {
        em.getTransaction().begin();
        try {
            civilStatus = em.merge(civilStatus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = civilStatus.getId();
                if (findCivilStatus(id) == null) {
                    throw new RuntimeException("The CivilStatus with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void destroy(int id) {
        em.getTransaction().begin();
        try {
            CivilStatus civilStatus;
            try {
                civilStatus = em.getReference(CivilStatus.class, id);
                civilStatus.getId();
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("The CivilStatus with id " + id + " no longer exists.", enfe);
            }
            em.remove(civilStatus);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<CivilStatus> findCivilStatusEntities() {
        return findCivilStatusEntities(true, -1, -1);
    }

    public List<CivilStatus> findCivilStatusEntities(int maxResults, int firstResult) {
        return findCivilStatusEntities(false, maxResults, firstResult);
    }

    private List<CivilStatus> findCivilStatusEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(CivilStatus.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public CivilStatus findCivilStatus(int id) {
        return em.find(CivilStatus.class, id);
    }

    public int getCivilStatusCount() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<CivilStatus> rt = cq.from(CivilStatus.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}