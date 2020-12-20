package com.sonar.vishal.medico.common.structure;

import com.google.gson.annotations.SerializedName;
import com.sonar.vishal.medico.common.pojo.Bill;

public class BillData extends Data {

	@SerializedName(value = "Bill")
	private Bill bill;

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

}
