package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.message.common.Now;
import com.sonar.vishal.medico.common.pojo.Bill;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.structure.BillData;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.Header;
import com.sonar.vishal.medico.common.structure.IdData;
import com.sonar.vishal.medico.common.structure.LogData;
import com.sonar.vishal.medico.common.structure.LoginData;
import com.sonar.vishal.medico.common.structure.PageData;
import com.sonar.vishal.medico.common.structure.PatientData;
import com.sonar.vishal.medico.common.structure.ProductData;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.medico.common.structure.StoreData;
import com.sonar.vishal.medico.common.structure.UserData;

public class UnitTestData {

	private Message message;
	private Header header;
	private String date;
	private PageData pageData;

	public UnitTestData() throws InvalidKeyException, NoSuchAlgorithmException {
		pageData = new PageData();
		message = new Message();
		header = new Header();
		date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
		header.setDateTime(date);
		header.setMessage(Constant.SUCCESS);
		header.setResult(Constant.SUCCESS);
		header.setType(Constant.REQUEST);
		header.setUuid(UUID.randomUUID().toString());
		message.setHeader(header);
		pageData.setStartIndex(0);
		pageData.setEndIndex(10);
	}

	public Message getAllStoreRequest() {
		message.setData(new Data());
		message.getHeader().setFunction(Constant.GET_STORE_LIST);
		return message;
	}

	public Message getPageStoreRequest() {
		message.setData(pageData);
		message.getHeader().setFunction(Constant.GET_STORE_PAGE);
		return message;
	}

	public Message getAddStoreRequest() {
		StoreData data = new StoreData();
		data.setStore(TestData.getStore());
		message.setData(data);
		message.getHeader().setFunction(Constant.ADD_STORE);
		return message;
	}

	public Message getUpdateStoreRequest(Store store) {
		StoreData data = new StoreData();
		data.setStore(store);
		message.setData(data);
		message.getHeader().setFunction(Constant.UPDATE_STORE);
		return message;
	}

	public Message getDeleteStoreRequest(Store store) {
		StoreData data = new StoreData();
		data.setStore(store);
		message.setData(data);
		message.getHeader().setFunction(Constant.DELETE_STORE);
		return message;
	}

	public Message getStoreRequest(int id) {
		IdData data = new IdData();
		data.setId(String.valueOf(id));
		message.setData(data);
		message.getHeader().setFunction(Constant.GET_STORE);
		return message;
	}

	public Message getAllUserRequest() {
		message.setData(new Data());
		message.getHeader().setFunction(Constant.GET_USER_LIST);
		return message;
	}

	public Message getPageUserRequest() {
		message.setData(pageData);
		message.getHeader().setFunction(Constant.GET_USER_PAGE);
		return message;
	}

	public Message getAddUserRequest(Role role) {
		UserData data = new UserData();
		data.setUser(TestData.getUser());
		data.getUser().setRole(role);
		message.setData(data);
		message.getHeader().setFunction(Constant.ADD_USER);
		return message;
	}

	public Message getUserRequest(int id) {
		IdData data = new IdData();
		data.setId(String.valueOf(id));
		message.setData(data);
		message.getHeader().setFunction(Constant.GET_USER);
		return message;
	}

	public Message getUpdateUserRequest(User user) {
		UserData data = new UserData();
		data.setUser(user);
		message.setData(data);
		message.getHeader().setFunction(Constant.UPDATE_USER);
		return message;
	}

	public Message getDeleteUserRequest(User user) {
		UserData data = new UserData();
		data.setUser(user);
		message.setData(data);
		message.getHeader().setFunction(Constant.DELETE_USER);
		return message;
	}

	public Message getAllRoleRequest() {
		message.setData(new Data());
		message.getHeader().setFunction(Constant.GET_ROLE_LIST);
		return message;
	}

	public Message getPageRoleRequest() {
		message.setData(pageData);
		message.getHeader().setFunction(Constant.GET_ROLE_PAGE);
		return message;
	}

	public Message getAddRoleRequest() {
		RoleData data = new RoleData();
		data.setRole(TestData.getRole());
		message.setData(data);
		message.getHeader().setFunction(Constant.ADD_ROLE);
		return message;
	}

	public Message getUpdateRoleRequest(Role role) {
		RoleData data = new RoleData();
		data.setRole(role);
		message.setData(data);
		message.getHeader().setFunction(Constant.UPDATE_ROLE);
		return message;
	}

	public Message getDeleteRoleRequest(Role role) {
		RoleData data = new RoleData();
		data.setRole(role);
		message.setData(data);
		message.getHeader().setFunction(Constant.DELETE_ROLE);
		return message;
	}

	public Message getRoleRequest(int id) {
		IdData data = new IdData();
		data.setId(String.valueOf(id));
		message.setData(data);
		message.getHeader().setFunction(Constant.GET_ROLE);
		return message;
	}

	public Message getAllProductRequest() {
		message.setData(new Data());
		message.getHeader().setFunction(Constant.GET_PRODUCT_LIST);
		return message;
	}

	public Message getPageProductRequest() {
		message.setData(pageData);
		message.getHeader().setFunction(Constant.GET_PRODUCT_PAGE);
		return message;
	}

	public Message getAddProductRequest() {
		ProductData data = new ProductData();
		data.setProduct(TestData.getProduct());
		message.setData(data);
		message.getHeader().setFunction(Constant.ADD_PRODUCT);
		return message;
	}

	public Message getUpdateProductRequest(Product product) {
		ProductData data = new ProductData();
		data.setProduct(product);
		message.setData(data);
		message.getHeader().setFunction(Constant.UPDATE_PRODUCT);
		return message;
	}

	public Message getDeleteProductRequest(Product product) {
		ProductData data = new ProductData();
		data.setProduct(product);
		message.setData(data);
		message.getHeader().setFunction(Constant.DELETE_PRODUCT);
		return message;
	}

	public Message getProductRequest(int id) {
		IdData data = new IdData();
		data.setId(String.valueOf(id));
		message.setData(data);
		message.getHeader().setFunction(Constant.GET_PRODUCT);
		return message;
	}

	public Message getAllPatientRequest() {
		message.setData(new Data());
		message.getHeader().setFunction(Constant.GET_PATIENT_LIST);
		return message;
	}

	public Message getPagePatientRequest() {
		message.setData(pageData);
		message.getHeader().setFunction(Constant.GET_PATIENT_PAGE);
		return message;
	}

	public Message getAddPatientRequest() {
		PatientData data = new PatientData();
		data.setPatient(TestData.getPatient());
		message.setData(data);
		message.getHeader().setFunction(Constant.ADD_PATIENT);
		return message;
	}

	public Message getPatientRequest(int id) {
		IdData data = new IdData();
		data.setId(String.valueOf(id));
		message.setData(data);
		message.getHeader().setFunction(Constant.GET_PATIENT);
		return message;
	}

	public Message getUpdatePatientRequest(Patient patient) {
		PatientData data = new PatientData();
		data.setPatient(patient);
		message.setData(data);
		message.getHeader().setFunction(Constant.UPDATE_PATIENT);
		return message;
	}

	public Message getDeletePatientRequest(Patient patient) {
		PatientData data = new PatientData();
		data.setPatient(patient);
		message.setData(data);
		message.getHeader().setFunction(Constant.DELETE_PATIENT);
		return message;
	}

	public Message getAllBillRequest() {
		message.setData(new Data());
		message.getHeader().setFunction(Constant.GET_BILL_LIST);
		return message;
	}

	public Message getPageBillRequest() {
		message.setData(pageData);
		message.getHeader().setFunction(Constant.GET_BILL_PAGE);
		return message;
	}

	public Message getAddBillRequest(Patient patient, Store store) {
		BillData data = new BillData();
		data.setBill(TestData.getBill());
		data.getBill().setPatient(patient);
		data.getBill().setStore(store);
		message.setData(data);
		message.getHeader().setFunction(Constant.ADD_BILL);
		return message;
	}

	public Message getUpdateBillRequest(Bill bill) {
		BillData data = new BillData();
		data.setBill(bill);
		message.setData(data);
		message.getHeader().setFunction(Constant.UPDATE_BILL);
		return message;
	}

	public Message getDeleteBillRequest(Bill bill) {
		BillData data = new BillData();
		data.setBill(bill);
		message.setData(data);
		message.getHeader().setFunction(Constant.DELETE_BILL);
		return message;
	}

	public Message getBillRequest(int id) {
		IdData data = new IdData();
		data.setId(String.valueOf(id));
		message.setData(data);
		message.getHeader().setFunction(Constant.GET_BILL);
		return message;
	}

	public Message getLoginData(User user) {
		LoginData data = new LoginData();
		data.setUserName(user.getUserName());
		data.setPassword(user.getPassword());
		message.setData(data);
		message.getHeader().setFunction(Constant.LOGIN);
		return message;
	}

	public Message getAddLogRequest() {
		LogData data = new LogData();
		data.setLog(TestData.getLog());
		message.setData(data);
		message.getHeader().setFunction(Constant.ADD_LOG);
		return message;
	}

	public Message getAllLogRequest() {
		message.setData(new Data());
		message.getHeader().setFunction(Constant.GET_LOG_LIST);
		return message;
	}

	public Message getPageLogRequest() {
		message.setData(pageData);
		message.getHeader().setFunction(Constant.GET_LOG_PAGE);
		return message;
	}

	public Message getLogRequest() {
		LogData data = new LogData();
		data.setLog(TestData.getLog());
		data.setEndDate(Now.get());
		String date = data.getLog().getDateTime().split("T")[0];
		data.getLog().setDateTime(date + "T00:00");
		message.setData(data);
		message.getHeader().setFunction(Constant.GET_LOG);
		return message;
	}

	public Message getKeyRequest() {
		message.setData(new Data());
		message.getHeader().setFunction(Constant.GET_KEY);
		return message;
	}
}
