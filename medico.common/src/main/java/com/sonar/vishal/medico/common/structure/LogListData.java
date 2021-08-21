package com.sonar.vishal.medico.common.structure;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sonar.vishal.medico.common.pojo.Log;

public class LogListData extends Data {

	@SerializedName(value = "List")
	private List<Log> logList;
	@SerializedName(value = "Count")
	private long totalRowCount;

	public List<Log> getLogList() {
		return logList;
	}

	public void setLogList(List<Log> logList) {
		this.logList = logList;
	}

	public long getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(long totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

}
