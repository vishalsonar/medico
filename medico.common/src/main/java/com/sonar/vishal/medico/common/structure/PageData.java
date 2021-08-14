package com.sonar.vishal.medico.common.structure;

import com.google.gson.annotations.SerializedName;

public class PageData extends Data {

	@SerializedName("StartIndex")
	private int startIndex;
	@SerializedName("EndIndex")
	private int endIndex;

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
