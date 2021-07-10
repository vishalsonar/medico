package com.sonar.vishal.ui.window.patient;

import java.util.Objects;

import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.listener.patient.UpdatePatientListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;

public class UpdatePatientWindow extends MedicoWindow {

	private static final long serialVersionUID = -262384955881959894L;
	private transient Patient selectedPatient;
	private transient PatientWindowDecorator decorator;

	public UpdatePatientWindow(CRUDStructure structure, Patient selectedPatient) {
		super(UIConstant.UPDATE_PATIENT, structure);
		this.selectedPatient = selectedPatient;
		decorator = new PatientWindowDecorator();
	}

	@Override
	public void setWindow() {
		decorator.name.setValue(selectedPatient.getPatientName());
		decorator.phoneNumber.setValue(selectedPatient.getPhoneNumber());
		decorator.doctorName.setValue(selectedPatient.getDoctorName());
		decorator.line1.setValue(selectedPatient.getAddress().getLine1());
		decorator.line2.setValue(selectedPatient.getAddress().getLine2());
		decorator.city.setValue(selectedPatient.getAddress().getCity());
		decorator.pinCode.setValue(selectedPatient.getAddress().getPinCode());
		decorator.state.setValue(selectedPatient.getAddress().getState());
		addComponents(decorator.name, decorator.phoneNumber, decorator.doctorName, decorator.line1, decorator.line2,
				decorator.city, decorator.pinCode, decorator.state);
		addAction();
		addCancelListener(this);
		addSubmitListener(new UpdatePatientListener(decorator.patientBinder, decorator.addressBinder, selectedPatient.getId(), this, structure));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(decorator, selectedPatient);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdatePatientWindow other = (UpdatePatientWindow) obj;
		return Objects.equals(decorator, other.decorator) && Objects.equals(selectedPatient, other.selectedPatient);
	}
}
