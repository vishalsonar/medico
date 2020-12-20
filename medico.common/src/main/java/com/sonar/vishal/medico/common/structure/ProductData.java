package com.sonar.vishal.medico.common.structure;

import com.google.gson.annotations.SerializedName;
import com.sonar.vishal.medico.common.pojo.Product;

public class ProductData extends Data {

	@SerializedName(value = "Product")
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
