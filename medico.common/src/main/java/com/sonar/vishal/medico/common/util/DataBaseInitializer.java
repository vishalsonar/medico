package com.sonar.vishal.medico.common.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.sonar.vishal.medico.common.Hibernate;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.User;

public class DataBaseInitializer {

	private static final Hibernate HIBERNATE = Hibernate.getInstance();
	
	private DataBaseInitializer() {
		throw new IllegalStateException("Utility class");
	}

	private static User getUser() {
		User user = new User();
		Role role = getRole();
		role.setId(1);
		user.setUserName(Constant.ADMIN);
		user.setPassword(Constant.ADMIN);
		user.setRole(role);
		return user;
	}

	private static Role getRole() {
		Role role = new Role();
		role.setName(Constant.ADMIN);
		role.setModule("Login,Option,Bill,Product,Patient,Store,User,Role");
		return role;
	}

	@SuppressWarnings("deprecation")
	public static long getRoleCount() {
		long count = 0;
		Session session = HIBERNATE.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Role.class);
			count = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		}
		return count;
	}

	@SuppressWarnings("deprecation")
	public static long getUserCount() {
		long count = 0;
		Session session = HIBERNATE.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(User.class);
			count = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		}
		return count;
	}

	private static void addRole() {
		long count = getRoleCount();
		if (count == 0) {
			HIBERNATE.saveOrUpdate(getRole());
		}
	}

	private static void addUser() {
		long count = getUserCount();
		if (count == 0) {
			HIBERNATE.saveOrUpdate(getUser());
		}
	}

	public static void insertSuperUser() {
		addRole();
		addUser();
	}

}
