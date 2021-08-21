package com.sonar.vishal.medico.common.structure;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sonar.vishal.medico.common.pojo.Bill;

public class BillListData extends Data {

	@SerializedName(value = "List")
	private List<Bill> billList;
	@SerializedName(value = "Count")
	private long totalRowCount;

	public List<Bill> getBillList() {
		return billList;
	}

	public void setBillList(List<Bill> billList) {
		this.billList = billList;
	}

	public long getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(long totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

}
