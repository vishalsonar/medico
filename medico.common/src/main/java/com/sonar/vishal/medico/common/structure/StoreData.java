package com.sonar.vishal.medico.common.structure;

import com.google.gson.annotations.SerializedName;
import com.sonar.vishal.medico.common.pojo.Store;

public class StoreData extends Data {

	@SerializedName(value = "Store")
	private Store store;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
