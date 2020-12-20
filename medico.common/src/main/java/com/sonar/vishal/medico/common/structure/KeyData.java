package com.sonar.vishal.medico.common.structure;

import com.google.gson.annotations.SerializedName;

public class KeyData extends Data {

	@SerializedName(value = "Key")
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
