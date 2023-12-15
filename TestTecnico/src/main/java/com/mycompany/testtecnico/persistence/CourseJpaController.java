/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.Course;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

public class CourseJpaController {
    
    private EntityManager em;

    public CourseJpaController(EntityManager em) {
        this.em = em;
    }

    public void create(Course course) {
        em.getTransaction().begin();
        try {
            em.persist(course);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        }
    }

    public void edit(Course course) throws Exception {
        em.getTransaction().begin();
        try {
            course = em.merge(course);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        }
    }

    public void destroy(int id) throws EntityNotFoundException {
        em.getTransaction().begin();
        try {
            Course course = em.find(Course.class, id);
            if (course == null) {
                throw new EntityNotFoundException("The course with id " + id + " no longer exists.");
            }
            em.remove(course);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    public List<Course> findCourseEntities() {
        return em.createQuery("SELECT c FROM Course c").getResultList();
    }

    public Course findCourseById(int id) {
        return em.find(Course.class, id);
    }

    public List<Course> findCourseByName(String name) {
        Query query = em.createQuery("SELECT c FROM Course c WHERE c.name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    // Otros métodos de búsqueda y operaciones según tus necesidades
    
    // ...

}