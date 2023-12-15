package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.Department;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DepartmentJpaController implements Serializable {

    private EntityManager em;

    public DepartmentJpaController(EntityManager em) {
        this.em = em;
    }

    public void create(Department department) {
        em.getTransaction().begin();
        try {
            em.persist(department);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDepartment(department.getId()) != null) {
                throw new RuntimeException("Department with id " + department.getId() + " already exists.", ex);
            }
            throw ex;
        }
    }

    public void edit(Department department) {
        em.getTransaction().begin();
        try {
            department = em.merge(department);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = department.getId();
                if (findDepartment(id) == null) {
                    throw new RuntimeException("The Department with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void destroy(int id) {
        em.getTransaction().begin();
        try {
            Department department;
            try {
                department = em.getReference(Department.class, id);
                department.getId();
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("The Department with id " + id + " no longer exists.", enfe);
            }
            em.remove(department);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Department> findDepartmentEntities() {
        return findDepartmentEntities(true, -1, -1);
    }

    public List<Department> findDepartmentEntities(int maxResults, int firstResult) {
        return findDepartmentEntities(false, maxResults, firstResult);
    }

    private List<Department> findDepartmentEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Department.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public Department findDepartment(int id) {
        return em.find(Department.class, id);
    }

    public int getDepartmentCount() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Department> rt = cq.from(Department.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}