package com.sonar.vishal.medico.common.message.common;

import com.sonar.vishal.medico.common.structure.Header;
import com.google.gson.annotations.SerializedName;
import com.sonar.vishal.medico.common.structure.Data;

public class Message {

	@SerializedName(value = "Header")
	private Header header;
	@SerializedName(value = "Data")
	private Data data;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
