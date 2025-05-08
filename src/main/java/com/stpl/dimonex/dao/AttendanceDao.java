package com.stpl.dimonex.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.model.Attendance;

@Repository
public class AttendanceDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public void addAttendance(Attendance attendance) {
		hibernateTemplate.saveOrUpdate(attendance);
	}

	public List<Attendance> getAllAttendance() {
		return (List<Attendance>) hibernateTemplate.find("from Attendance");
	}

	public Attendance getAttendanceById(int id) {
		return hibernateTemplate.get(Attendance.class, id);
	}

	@Transactional
	public void deleteAttendance(int id) {
		Attendance attendance = getAttendanceById(id);
		if (attendance != null) {
			hibernateTemplate.delete(attendance);
		}
	}

	@Transactional
	public Attendance findAttendanceByUserAndDate(Long userId, String date) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "FROM Attendance a WHERE a.userId = :userId AND a.date = :date";
		Query<Attendance> query = session.createQuery(hql, Attendance.class);
		query.setParameter("userId", userId);
		query.setParameter("date", date);
		List<Attendance> result = query.getResultList();
		return result.isEmpty() ? null : result.get(0); // Return the first result or null if no record found
	}

	@Transactional
	public List<Attendance> findAttendanceByDate(String date) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		// Create HQL (Hibernate Query Language) to fetch attendance records by date
		String hql = "from Attendance a where a.date = :date";

		// Create the query
		Query<Attendance> query = session.createQuery(hql, Attendance.class);
		query.setParameter("date", date); // Set the date parameter

		// Execute the query and get the result list
		return query.getResultList();
	}

	@Transactional
	public List<Attendance> findAttendanceByUserAndMonth(Long userId, String month) {
		// Query for user attendance by month
		String hql = "from Attendance a where a.userId = :userId and "
				+ "function('month', a.date) = :month and function('year', a.date) = function('year', current_date)";

		// Get the current session
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		// Create the query and set parameters
		Query<Attendance> query = session.createQuery(hql, Attendance.class);
		query.setParameter("userId", userId);
		query.setParameter("month", Integer.parseInt(month)); // month should be a string like "01", "02", etc.

		// Execute the query and return the list of results
		return query.getResultList();
	}

	@Transactional
	public List<Attendance> findByUserId(Long userId) {
		// Get the current session from HibernateTemplate
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		// Create a Hibernate Query using HQL
		String hql = "FROM Attendance a WHERE a.userId = :userId";

		// Create the query object and set the parameter
		Query<Attendance> query = session.createQuery(hql, Attendance.class);
		query.setParameter("userId", userId);

		// Execute the query and return the result list
		return query.getResultList();
	}
}
