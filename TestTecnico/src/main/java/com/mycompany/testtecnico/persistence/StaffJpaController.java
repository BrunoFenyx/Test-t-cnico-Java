/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.Staff;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class StaffJpaController implements Serializable {

    private EntityManager em;

    public StaffJpaController(EntityManager em) {
        this.em = em;
    }

    public void create(Staff staff) {
        em.getTransaction().begin();
        try {
            em.persist(staff);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStaff(staff.getId()) != null) {
                throw new RuntimeException("Staff with id " + staff.getId() + " already exists.", ex);
            }
            throw ex;
        }
    }

    public void edit(Staff staff) {
        em.getTransaction().begin();
        try {
            staff = em.merge(staff);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = staff.getId();
                if (findStaff(id) == null) {
                    throw new RuntimeException("The Staff with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void destroy(int id) {
        em.getTransaction().begin();
        try {
            Staff staff;
            try {
                staff = em.getReference(Staff.class, id);
                staff.getId();
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("The Staff with id " + id + " no longer exists.", enfe);
            }
            em.remove(staff);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Staff> findStaffEntities() {
        return findStaffEntities(true, -1, -1);
    }

    public List<Staff> findStaffEntities(int maxResults, int firstResult) {
        return findStaffEntities(false, maxResults, firstResult);
    }

    private List<Staff> findStaffEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Staff.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public Staff findStaff(int id) {
        return em.find(Staff.class, id);
    }

    public int getStaffCount() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Staff> rt = cq.from(Staff.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
