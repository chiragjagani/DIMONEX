package com.stpl.dimonex.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.stpl.dimonext.constants.AvailabilityStatus;


@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String type;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    @ManyToOne
    @JoinColumn(name = "polisher_id", nullable = true) // Nullable if unassigned
    private Polisher assignedPolisher;

    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedDate; // Store when equipment was assigned

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AvailabilityStatus getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public Polisher getAssignedPolisher() {
		return assignedPolisher;
	}

	public void setAssignedPolisher(Polisher assignedPolisher) {
		this.assignedPolisher = assignedPolisher;
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

}
