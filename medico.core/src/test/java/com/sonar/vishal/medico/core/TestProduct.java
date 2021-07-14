package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Product;

public class TestProduct extends UnitTest {

	public TestProduct() throws InvalidKeyException, NoSuchAlgorithmException {
		super();
	}

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException {
		TestApi(data.getAddProductRequest());
		JsonObject response = TestApi(data.getAllProductRequest());
		JsonObject productObject = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray().get(0).getAsJsonObject();
		Product product = gson.fromJson(productObject, Product.class);
		TestApi(data.getProductRequest(product.getId()));
		TestApi(data.getUpdateProductRequest(product));
		TestApi(data.getDeleteProductRequest(product));
	}
}
