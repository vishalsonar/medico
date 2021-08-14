package com.sonar.vishal.medico.common.structure;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sonar.vishal.medico.common.pojo.Product;

public class ProductListData extends Data {

	@SerializedName(value = "List")
	private List<Product> productList;
	@SerializedName(value = "Count")
	private long totalRowCount;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public long getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(long totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

}
