package com.sonar.vishal.medico.common.structure;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sonar.vishal.medico.common.pojo.Patient;

public class PatientListData extends Data {

	@SerializedName(value = "List")
	private List<Patient> patientList;
	@SerializedName(value = "Count")
	private long totalRowCount;

	public List<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}

	public long getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(long totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

}
