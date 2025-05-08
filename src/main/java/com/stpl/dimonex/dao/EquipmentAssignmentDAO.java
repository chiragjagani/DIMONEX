package com.stpl.dimonex.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stpl.dimonex.model.Equipment;
import com.stpl.dimonex.model.EquipmentAssignment;

@Repository
@Transactional
public class EquipmentAssignmentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveAssignment(EquipmentAssignment assignment) {
        entityManager.persist(assignment);
    }

    public List<EquipmentAssignment> getAllAssignments() {
        return entityManager.createQuery("SELECT e FROM EquipmentAssignment e", EquipmentAssignment.class).getResultList();
    }
    
    @Autowired
    private SessionFactory sessionFactory;

    public List<Equipment> getEquipmentByPolisherId(Long polisherId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT ea.equipment FROM EquipmentAssignment ea WHERE ea.polisher.id = :polisherId";
        Query<Equipment> query = session.createQuery(hql, Equipment.class);
        query.setParameter("polisherId", polisherId);
        return query.getResultList();
    }
}
