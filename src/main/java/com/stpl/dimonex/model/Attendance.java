package com.stpl.dimonex.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    private String status;
    private Long userId;

    public Attendance() {}

    @Override
	public String toString() {
		return "Attendance [id=" + id + ", date=" + date + ", status=" + status + ", userId=" + userId + "]";
	}

	public Attendance(int id, String date, String status, Long polisherId) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.userId = polisherId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
