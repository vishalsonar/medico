package com.sonar.vishal.medico.common;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.sonar.vishal.medico.common.data.TestData;
import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Bill;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.util.Logger;
import com.sonar.vishal.medico.common.util.LoggerMessage;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HibernateTest extends TestCase {

	private Hibernate hibernate;
	private int size = 5;
	private int storeId;
	private int addressId;
	private int patientId;
	
	static {
		try {
			Logger.setComponent(LoggerMessage.CORE);
			Logger.setIp(Inet4Address.getLocalHost().getHostAddress());
			Logger.info("HibernateTest", LoggerMessage.SERVER_INITIALIZE);
		} catch (UnknownHostException e) {
			Logger.setIp(LoggerMessage.EMPTY);
			Logger.error("HibernateTest", LoggerMessage.UNKOWN_HOST_EXCEPTION);
		}
	}

	public HibernateTest() {
		hibernate = new Hibernate();
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testCriteria() {
		Session session = hibernate.getSession();
		Criteria criteria = session.createCriteria(Store.class);
		criteria.createCriteria("address");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<?> list = hibernate.executeCriteria(session, criteria);
		assertNotNull(list);
		Store store = (Store) list.get(0);
		storeId = store.getId();
		addressId = store.getAddress().getId();
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testCustomerCriteria() {
		Session session = hibernate.getSession();
		Criteria criteria = session.createCriteria(Patient.class);
		criteria.createCriteria("address");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<?> list = hibernate.executeCriteria(session, criteria);
		assertNotNull(list);
		Patient patient = (Patient) list.get(0);
		patientId = patient.getId();
	}

	@Test
	public void testASaveOrUpdate() {
		int i = 0;
		List<Object> list = TestData.getObjectList();
		Store store = (Store) list.get(0);
		Address address = (Address) list.get(1);
		Patient patient = (Patient) list.get(2);
		store.setId(storeId);
		address.setId(addressId);
		patient.setId(patientId);
		for (Object object : list) {
			if (i <= size) {
				assertEquals(true, hibernate.saveOrUpdate(object));
				i++;
				continue;
			}
			assertEquals(false, hibernate.saveOrUpdate(object));
		}
	}

	@Test
	public void testZdelete() {
		Store object = (Store) hibernate.selectObjectById(Store.class, storeId);
		if (object != null) {
			assertEquals(true, hibernate.delete(Store.class, object.getId()));
		}
		assertEquals(false, hibernate.delete(Store.class, 5585));
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Test
	public void testTbillSave() {
		Bill bill = new Bill();
		Session session = hibernate.getSession();

		Criteria criteria = session.createCriteria(Patient.class);
		criteria.createCriteria("address");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<?> list = hibernate.executeCriteria(session, criteria);
		assertNotNull(list);
		Patient patient = (Patient) list.get(0);

		session = hibernate.getSession();
		criteria = session.createCriteria(Store.class);
		criteria.createCriteria("address");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		list = (List<Store>) hibernate.executeCriteria(session, criteria);
		assertNotNull(list);
		bill.setStore((Store) list.get(0));

		session = hibernate.getSession();
		criteria = session.createCriteria(Product.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		list = (List<Product>) hibernate.executeCriteria(session, criteria);
		assertNotNull(list);
		bill.setPatient(patient);
		bill.setProducts((List<Product>) list.subList(0, 0));
		assertEquals(true, hibernate.saveOrUpdate(bill));
	}
}
