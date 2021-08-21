package com.sonar.vishal.medico.common.structure;

import com.google.gson.annotations.SerializedName;

public class SearchData extends Data {

	@SerializedName("Keyword")
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
