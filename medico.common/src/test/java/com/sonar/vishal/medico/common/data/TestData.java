package com.sonar.vishal.medico.common.data;

import java.util.ArrayList;
import java.util.List;

import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Bill;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.pojo.User;

public class TestData {

	public static List<Object> getObjectList() {
		ArrayList<Object> testObjectList = new ArrayList<Object>();
		testObjectList.add(TestData.getStore());
		testObjectList.add(TestData.getAddress());
		testObjectList.add(TestData.getPatient());
		testObjectList.add(TestData.getProduct());
		testObjectList.add(TestData.getRole());
		testObjectList.add(TestData.getUser());
		testObjectList.add(null);
		testObjectList.add(Store.class);
		testObjectList.add(new Store());
		return testObjectList;
	}

	public static Role getRole() {
		Role role = new Role();
		role.setName("admin");
		role.setModule("Login,Option,Bill,Product,Patient,Store,User,Role");
		return role;
	}

	public static User getUser() {
		User user = new User();
		Role role = getRole();
		role.setId(1);
		user.setUserName("admin");
		user.setPassword("admin");
		user.setRole(role);
		return user;
	}

	public static Address getAddress() {
		Address address = new Address();
		address.setLine1("line1 address");
		address.setLine2("line2 address");
		address.setCity("Mumbai");
		address.setPinCode("400069");
		address.setState("Maharashtra");
		return address;
	}

	public static Store getStore() {
		Store store = new Store();
		store.setCinNumber("CIN123456");
		store.setDlExpiry("12-12-12");
		store.setDlNumber("12345678");
		store.setFassaiNumber("FASSAI123");
		store.setGstin("GSTIN9876543AS3443");
		store.setName("vishal");
		store.setPanNumber("PAN765432");
		store.setPhoneNumber("022-23456789");
		store.setSxDlExpiry("12-12-12");
		store.setSxDlNumber("8765432345");
		store.setAddress(TestData.getAddress());
		return store;
	}

	public static Patient getPatient() {
		Patient patient = new Patient();
		patient.setAddress(getAddress());
		patient.setPhoneNumber("987643234");
		patient.setDoctorName("doctor");
		patient.setPatientName("patient name");
		return patient;
	}

	public static Product getProduct() {
		Product product = new Product();
		product.setAmount("123");
		product.setBatchNumber("Batch12345");
		product.setDescription("NAME");
		product.setExpiryDate("12-12-1212");
		product.setGst("12");
		product.setHsnCode("hsn1234");
		product.setMrp("23");
		product.setPack("pack123");
		product.setQuantity("12");
		product.setRate("30");
		product.setLsq("LSQ1234");
		return product;
	}

	public static Bill getBill() {
		Bill bill = new Bill();
		List<Product> list = new ArrayList<Product>();
		list.add(getProduct());
		bill.setPatient(getPatient());
		bill.setProducts(list);
		bill.setBillNumber("BILL23242");
		return bill;
	}
}
