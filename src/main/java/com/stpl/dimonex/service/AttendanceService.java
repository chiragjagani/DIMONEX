package com.stpl.dimonex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.dao.AttendanceDao;
import com.stpl.dimonex.model.Attendance;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    public void addAttendance(Attendance attendance) {
        attendanceDao.addAttendance(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceDao.getAllAttendance();
    }

    public Attendance getAttendanceById(int id) {
        return attendanceDao.getAttendanceById(id);
    }

    public void deleteAttendance(int id) {
        attendanceDao.deleteAttendance(id);
    }
    
    @Transactional
    public List<Attendance> getAttendanceByDate(String date) {
        return attendanceDao.findAttendanceByDate(date);
    }
    
    
    @Transactional
    public List<Attendance> getAttendanceHistoryByUserAndMonth(Long userId, String month) {
        return attendanceDao.findAttendanceByUserAndMonth(userId, month);
    }
    
    
    public List<Attendance> getAttendanceForPolisher(Long polisherId) {
        // Fetch all attendance records for the polisher by their ID
        List<Attendance> allAttendance = attendanceDao.findByUserId(polisherId);

        // Return the list of attendance records for the given polisher
        return allAttendance;
    }
    
    public void addOrUpdateAttendance(Attendance attendance) {
        Attendance existingAttendance = attendanceDao.findAttendanceByUserAndDate(attendance.getUserId(), attendance.getDate());
        
        if (existingAttendance != null) {
            // If attendance exists, update the status
            existingAttendance.setStatus(attendance.getStatus());
            attendanceDao.addAttendance(existingAttendance);  // Save the updated attendance
        } else {
            // If attendance does not exist, create a new entry
            attendanceDao.addAttendance(attendance);
        }
    }
    
}
