package com.stpl.dimonex.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.stpl.dimonex.model.Equipment;
import com.stpl.dimonext.constants.AvailabilityStatus;

@Repository
@Transactional
public class EquipmentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // Add new equipment
    public void addEquipment(Equipment equipment) {
        entityManager.persist(equipment);
    }

    // Update existing equipment
    public void updateEquipment(Equipment equipment) {
        entityManager.merge(equipment);
    }

    // Get equipment by ID
    public Equipment getEquipmentById(Long id) {
        return entityManager.find(Equipment.class, id);
    }

    // Delete equipment by ID
    public void deleteEquipment(Long id) {
        Equipment equipment = getEquipmentById(id);
        if (equipment != null) {
            entityManager.remove(equipment);
        }
    }

    // Get all equipment
    public List<Equipment> getAllEquipment() {
        return entityManager.createQuery("SELECT e FROM Equipment e", Equipment.class)
                            .getResultList();
    }

    // Get available equipment
    public List<Equipment> getAvailableEquipment() {
        return entityManager.createQuery(
                "SELECT e FROM Equipment e WHERE e.availabilityStatus = :status", Equipment.class)
                .setParameter("status", AvailabilityStatus.AVAILABLE)
                .getResultList();
    }
}
