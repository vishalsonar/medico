package com.sonar.vishal.medico.common.structure;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sonar.vishal.medico.common.pojo.Patient;

public class PatientListData extends Data {

	@SerializedName(value = "List")
	private List<Patient> patientList;

	public List<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}

}
