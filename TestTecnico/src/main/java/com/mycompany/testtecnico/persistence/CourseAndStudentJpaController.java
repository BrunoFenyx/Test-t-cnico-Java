/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.CourseAndStudent;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseAndStudentJpaController {

    private EntityManager entityManager;

    public CourseAndStudentJpaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(CourseAndStudent courseAndStudent) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(courseAndStudent);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public void destroy(int id) throws EntityNotFoundException {
        entityManager.getTransaction().begin();
        try {
            CourseAndStudent courseAndStudent = entityManager.find(CourseAndStudent.class, id);
            if (courseAndStudent == null) {
                throw new EntityNotFoundException("CourseAndStudent with id " + id + " not found.");
            }
            entityManager.remove(courseAndStudent);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public CourseAndStudent find(int id) {
        return entityManager.find(CourseAndStudent.class, id);
    }

    // Otros métodos de búsqueda

    public List<CourseAndStudent> findByStudentId(int studentId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CourseAndStudent> query = cb.createQuery(CourseAndStudent.class);
        Root<CourseAndStudent> root = query.from(CourseAndStudent.class);
        query.select(root).where(cb.equal(root.get("student").get("id"), studentId));
        return entityManager.createQuery(query).getResultList();
    }

    public List<CourseAndStudent> findByCourseId(int courseId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CourseAndStudent> query = cb.createQuery(CourseAndStudent.class);
        Root<CourseAndStudent> root = query.from(CourseAndStudent.class);
        query.select(root).where(cb.equal(root.get("course").get("id"), courseId));
        return entityManager.createQuery(query).getResultList();
    }

    // Otros métodos de búsqueda según tus necesidades

}
