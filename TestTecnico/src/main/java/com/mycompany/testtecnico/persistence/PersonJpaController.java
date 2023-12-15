/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.Person;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PersonJpaController implements Serializable {

    private EntityManager em;

    public PersonJpaController(EntityManager em) {
        this.em = em;
    }

    public void create(Person person) {
        em.getTransaction().begin();
        try {
            em.persist(person);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPerson(person.getId()) != null) {
                throw new RuntimeException("Person with id " + person.getId() + " already exists.", ex);
            }
            throw ex;
        }
    }

    public void edit(Person person) {
        em.getTransaction().begin();
        try {
            person = em.merge(person);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = person.getId();
                if (findPerson(id) == null) {
                    throw new RuntimeException("The Person with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void destroy(int id) {
        em.getTransaction().begin();
        try {
            Person person;
            try {
                person = em.getReference(Person.class, id);
                person.getId();
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("The Person with id " + id + " no longer exists.", enfe);
            }
            em.remove(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Person> findPersonEntities() {
        return findPersonEntities(true, -1, -1);
    }

    public List<Person> findPersonEntities(int maxResults, int firstResult) {
        return findPersonEntities(false, maxResults, firstResult);
    }

    private List<Person> findPersonEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Person.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public Person findPerson(int id) {
        return em.find(Person.class, id);
    }

    public int getPersonCount() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Person> rt = cq.from(Person.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}