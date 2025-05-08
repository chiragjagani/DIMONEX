package com.stpl.dimonex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.dimonex.model.Equipment;
import com.stpl.dimonex.model.EquipmentAssignment;
import com.stpl.dimonex.service.EquipmentService;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;
    
    

    // 1️⃣ Add Equipment
    @PostMapping("/add")
    public String addEquipment(@RequestBody Equipment equipment) {
        equipmentService.addEquipment(equipment);
        return "Equipment added successfully!";
    }

    // 2️⃣ Get All Equipment
    @GetMapping("/all")
    public List<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    // 3️⃣ Get Available Equipment
    @GetMapping("/available")
    public List<Equipment> getAvailableEquipment() {
        return equipmentService.getAvailableEquipment();
    }

    // 4️⃣ Assign Equipment to Polisher
    @PostMapping("/assign")
    public ResponseEntity<String> assignEquipment(@RequestBody Map<String, Long> requestData) {
        Long equipmentId = requestData.get("equipmentId");
        Long polisherId = requestData.get("polisherId");

        if (equipmentId == null || polisherId == null) {
            return ResponseEntity.badRequest().body("Invalid input data");
        }

        boolean assigned = equipmentService.assignEquipmentToPolisher(equipmentId, polisherId);
        return assigned ? ResponseEntity.ok("Equipment assigned successfully!") 
                        : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Assignment failed!");
    }


    // 5️⃣ Get All Assignment Logs (For Manager Dashboard)
    @GetMapping("/assignments")
    public List<EquipmentAssignment> getAllAssignments() {
        return equipmentService.getAllAssignments();
    }
    
}
