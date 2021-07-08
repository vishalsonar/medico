package com.sonar.vishal.ui.window.patient;

import java.util.Objects;

import com.sonar.vishal.ui.listener.patient.AddPatientListener;
import com.sonar.vishal.ui.structure.PatientStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;

public class AddPatientWindow extends MedicoWindow {

	private static final long serialVersionUID = 6602793284125960723L;
	private transient PatientWindowDecorator decorator;

	public AddPatientWindow(PatientStructure structure) {
		super(UIConstant.ADD_PATIENT, structure);
		decorator = new PatientWindowDecorator();
	}

	@Override
	public void setWindow() {
		addComponents(decorator.name, decorator.phoneNumber, decorator.doctorName, decorator.line1, decorator.line2,
				decorator.city, decorator.pinCode, decorator.state);
		addAction();
		addCancelListener(this);
		addSubmitListener(new AddPatientListener(decorator.patientBinder, decorator.addressBinder, this, structure));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(decorator);
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
		AddPatientWindow other = (AddPatientWindow) obj;
		return Objects.equals(decorator, other.decorator);
	}

}
