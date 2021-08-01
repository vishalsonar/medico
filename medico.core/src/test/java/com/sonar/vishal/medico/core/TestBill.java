package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Bill;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.pojo.Store;

public class TestBill extends UnitTest {

	public TestBill() throws InvalidKeyException, NoSuchAlgorithmException {
		super();
	}

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException {
		TestApi(data.getAddPatientRequest());
		JsonObject response = TestApi(data.getAllPatientRequest());
		JsonObject patientObject = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray().get(0).getAsJsonObject();
		Patient patient = gson.fromJson(patientObject, Patient.class);
		assertNotNull(patient);
		TestApi(data.getAddStoreRequest());
		response = TestApi(data.getAllStoreRequest());
		JsonObject storeObject = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray().get(0).getAsJsonObject();
		Store store = gson.fromJson(storeObject, Store.class);
		assertNotNull(store);
		TestApi(data.getAddBillRequest(patient, store));
		response = TestApi(data.getAllBillRequest());
		JsonObject billObject = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray().get(0).getAsJsonObject();
		Bill bill = gson.fromJson(billObject, Bill.class);
		assertNotNull(bill);
		TestApi(data.getBillRequest(bill.getId()));
		TestApi(data.getUpdateBillRequest(bill));
		TestApi(data.getDeleteBillRequest(bill));
	}

}
