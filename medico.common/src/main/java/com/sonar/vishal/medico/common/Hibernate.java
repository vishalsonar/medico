package com.sonar.vishal.medico.common;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.sonar.vishal.medico.common.util.HibernateUtil;
import com.sonar.vishal.medico.common.util.Logger;

public class Hibernate {

	private boolean handleException(Session session) {
		if (session != null) {
			session.getTransaction().rollback();
		}
		return false;
	}

	private void closeSession(Session session) {
		if (session != null) {
			session.close();
		}
	}

	public boolean delete(Class<?> clazz, int id) {
		boolean state = true;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(session.get(clazz, id));
			session.getTransaction().commit();
		} catch (Exception e) {
			Logger.error(getClass().getName(), e.getMessage());
			state = handleException(session);
		} finally {
			closeSession(session);
		}
		return state;
	}

	public boolean saveOrUpdate(Object object) {
		boolean state = true;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			Logger.error(getClass().getName(), e.getMessage());
			state = handleException(session);
		} finally {
			closeSession(session);
		}
		return state;
	}

	public Session getSession() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			Logger.error(getClass().getName(), e.getMessage());
			handleException(session);
		}
		return session;
	}

	public List<?> executeCriteria(Session session, Criteria criteria) {
		List<?> list = null;
		try {
			session.beginTransaction();
			list = criteria.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			Logger.error(getClass().getName(), e.getMessage());
			handleException(session);
		} finally {
			closeSession(session);
		}
		return list;
	}

	public Object selectObjectById(Class<?> clazz, int id) {
		Object object = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			object = session.get(clazz, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			Logger.error(getClass().getName(), e.getMessage());
			handleException(session);
		} finally {
			closeSession(session);
		}
		return object;
	}

	@SuppressWarnings("deprecation")
	public Object selectObjectById(Class<?> clazz, int id, String mappingCriteria) {
		Object object = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			criteria.createCriteria(mappingCriteria);
			criteria.add(Restrictions.idEq(id));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			object = criteria.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			Logger.error(getClass().getName(), e.getMessage());
			handleException(session);
		} finally {
			closeSession(session);
		}
		return object;
	}
}
