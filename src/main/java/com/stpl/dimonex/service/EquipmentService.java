package com.stpl.dimonex.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.dimonex.dao.EquipmentAssignmentDAO;
import com.stpl.dimonex.dao.EquipmentDAO;
import com.stpl.dimonex.dao.PolisherDao;
import com.stpl.dimonex.model.Equipment;
import com.stpl.dimonex.model.EquipmentAssignment;
import com.stpl.dimonex.model.Polisher;
import com.stpl.dimonext.constants.AvailabilityStatus;

@Service
@Transactional
public class EquipmentService {

    @Autowired
    private EquipmentDAO equipmentDAO;

    @Autowired
    private PolisherDao polisherDAO;

    @Autowired
    private EquipmentAssignmentDAO equipmentAssignmentDAO;

    // Add new equipment
    public void addEquipment(Equipment equipment) {
        equipment.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        equipmentDAO.addEquipment(equipment);
    }

    // Update existing equipment
    public void updateEquipment(Equipment equipment) {
        equipmentDAO.updateEquipment(equipment);
    }

    // Get equipment by ID
    public Equipment getEquipmentById(Long id) {
        return equipmentDAO.getEquipmentById(id);
    }

    // Delete equipment
    public void deleteEquipment(Long id) {
        equipmentDAO.deleteEquipment(id);
    }

    // Get all equipment
    public List<Equipment> getAllEquipment() {
        return equipmentDAO.getAllEquipment();
    }

    // Get available equipment
    public List<Equipment> getAvailableEquipment() {
        return equipmentDAO.getAvailableEquipment();
    }

    // Assign equipment to polisher
    public boolean assignEquipmentToPolisher(Long equipmentId, Long polisherId) {
        Equipment equipment = equipmentDAO.getEquipmentById(equipmentId);
        Polisher polisher = polisherDAO.getPolisherById(polisherId);

        if (equipment != null && polisher != null && equipment.getAvailabilityStatus() == AvailabilityStatus.AVAILABLE) {
            equipment.setAvailabilityStatus(AvailabilityStatus.ASSIGNED);
            equipmentDAO.updateEquipment(equipment);

            EquipmentAssignment assignment = new EquipmentAssignment();
            assignment.setEquipment(equipment);
            assignment.setPolisher(polisher);
            assignment.setAssignedDate(LocalDate.now());

            equipmentAssignmentDAO.saveAssignment(assignment);
            return true;
        }
        return false;
    }

    // Get assignment logs for manager dashboard
    public List<EquipmentAssignment> getAllAssignments() {
        return equipmentAssignmentDAO.getAllAssignments();
    }
    
 // Get equipment assigned to a specific polisher
    public List<Equipment> getEquipmentByPolisher(Long polisherId) {
        return equipmentAssignmentDAO.getEquipmentByPolisherId(polisherId);
    }

    
}
