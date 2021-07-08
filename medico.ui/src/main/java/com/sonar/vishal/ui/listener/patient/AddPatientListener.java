package com.sonar.vishal.ui.listener.patient;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.structure.PatientData;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.ValidationException;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;

public class AddPatientListener extends CRUDListener {

	private static final long serialVersionUID = 1192220773813275312L;
	private Binder<Patient> patientBinder;
	private Binder<Address> addressBinder;

	public AddPatientListener(Binder<Patient> patientBinder, Binder<Address> addressBinder, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.ADD_PATIENT, window);
		this.patientBinder = patientBinder;
		this.addressBinder = addressBinder;
	}
	
	@Override
	protected void doAction() {
		try {
			Patient patient = new Patient();
			Address address = new Address();
			PatientData data = new PatientData();
			this.addressBinder.writeBean(address);
			this.patientBinder.writeBean(patient);
			patient.setAddress(address);

			// Replace with validation
			validateString(patient.getDoctorName());
			data.setPatient(patient);
			Backend.message.setData(data);
			doPostRespondHeader(Constant.ADD_PATIENT_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

}
