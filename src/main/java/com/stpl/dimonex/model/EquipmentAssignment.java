package com.stpl.dimonex.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "equipment_assignment")
public class EquipmentAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "polisher_id", nullable = false)
    private Polisher polisher;

    @Column(name = "assigned_date", nullable = false)
    private LocalDate assignedDate;

    public EquipmentAssignment() {
    }

    public EquipmentAssignment(Equipment equipment, Polisher polisher, LocalDate assignedDate) {
        this.equipment = equipment;
        this.polisher = polisher;
        this.assignedDate = assignedDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Polisher getPolisher() {
        return polisher;
    }

    public void setPolisher(Polisher polisher) {
        this.polisher = polisher;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }
}
