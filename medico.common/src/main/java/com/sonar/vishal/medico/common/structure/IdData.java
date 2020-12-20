package com.sonar.vishal.medico.common.structure;

import com.google.gson.annotations.SerializedName;

public class IdData extends Data {

	@SerializedName(value = "Id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
