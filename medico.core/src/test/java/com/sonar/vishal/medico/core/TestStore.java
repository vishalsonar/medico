package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Store;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestStore extends UnitTest {

	public TestStore() throws InvalidKeyException, NoSuchAlgorithmException {
		super();
	}

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException {
		TestApi(data.getAddStoreRequest());
		JsonObject response = TestApi(data.getAllStoreRequest());
		JsonObject storeObject = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray().get(0).getAsJsonObject();
		Store store = gson.fromJson(storeObject, Store.class);
		assertNotNull(store);
		TestApi(data.getStoreRequest(store.getId()));
		TestApi(data.getUpdateStoreRequest(store));
		TestApi(data.getDeleteStoreRequest(store));
	}
}
