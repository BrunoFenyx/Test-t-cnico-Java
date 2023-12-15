/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.Student;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class StudentJpaController implements Serializable {

    private EntityManager em;

    public StudentJpaController(EntityManager em) {
        this.em = em;
    }

    public void create(Student student) {
        em.getTransaction().begin();
        try {
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStudent(student.getId()) != null) {
                throw new RuntimeException("Student with id " + student.getId() + " already exists.", ex);
            }
            throw ex;
        }
    }

    public void edit(Student student) {
        em.getTransaction().begin();
        try {
            student = em.merge(student);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = student.getId();
                if (findStudent(id) == null) {
                    throw new RuntimeException("The Student with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void destroy(int id) {
        em.getTransaction().begin();
        try {
            Student student;
            try {
                student = em.getReference(Student.class, id);
                student.getId();
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("The Student with id " + id + " no longer exists.", enfe);
            }
            em.remove(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Student> findStudentEntities() {
        return findStudentEntities(true, -1, -1);
    }

    public List<Student> findStudentEntities(int maxResults, int firstResult) {
        return findStudentEntities(false, maxResults, firstResult);
    }

    private List<Student> findStudentEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Student.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public Student findStudent(int id) {
        return em.find(Student.class, id);
    }

    public int getStudentCount() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Student> rt = cq.from(Student.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}