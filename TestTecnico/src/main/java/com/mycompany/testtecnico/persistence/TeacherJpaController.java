/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.Teacher;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TeacherJpaController implements Serializable {

    private EntityManager em;

    public TeacherJpaController(EntityManager em) {
        this.em = em;
    }

    public void create(Teacher teacher) {
        em.getTransaction().begin();
        try {
            em.persist(teacher);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTeacher(teacher.getId()) != null) {
                throw new RuntimeException("Teacher with id " + teacher.getId() + " already exists.", ex);
            }
            throw ex;
        }
    }

    public void edit(Teacher teacher) {
        em.getTransaction().begin();
        try {
            teacher = em.merge(teacher);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = teacher.getId();
                if (findTeacher(id) == null) {
                    throw new RuntimeException("The Teacher with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void destroy(int id) {
        em.getTransaction().begin();
        try {
            Teacher teacher;
            try {
                teacher = em.getReference(Teacher.class, id);
                teacher.getId();
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("The Teacher with id " + id + " no longer exists.", enfe);
            }
            em.remove(teacher);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Teacher> findTeacherEntities() {
        return findTeacherEntities(true, -1, -1);
    }

    public List<Teacher> findTeacherEntities(int maxResults, int firstResult) {
        return findTeacherEntities(false, maxResults, firstResult);
    }

    private List<Teacher> findTeacherEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Teacher.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public Teacher findTeacher(int id) {
        return em.find(Teacher.class, id);
    }

    public int getTeacherCount() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Teacher> rt = cq.from(Teacher.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}