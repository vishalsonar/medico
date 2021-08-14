package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Patient;

public class TestPatient extends UnitTest {

	public TestPatient() throws InvalidKeyException, NoSuchAlgorithmException {
		super();
	}

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException {
		TestApi(data.getAddPatientRequest());
		JsonObject response = TestApi(data.getAllPatientRequest());
		TestApi(data.getPagePatientRequest());
		JsonObject patientObject = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray().get(0).getAsJsonObject();
		Patient patient = gson.fromJson(patientObject, Patient.class);
		assertNotNull(patient);
		TestApi(data.getPatientRequest(patient.getId()));
		TestApi(data.getUpdatePatientRequest(patient));
		TestApi(data.getDeletePatientRequest(patient));
	}
}
