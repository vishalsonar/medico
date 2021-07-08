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

public class UpdatePatientListener extends CRUDListener {

	private static final long serialVersionUID = -647560492328455522L;
	private int id;
	private Binder<Patient> patientBinder = new Binder<>();
	private Binder<Address> addressBinder = new Binder<>();

	public UpdatePatientListener(Binder<Patient> patientBinder, Binder<Address> addressBinder, int id, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.UPDATE_PATIENT, window);
		this.id = id;
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
			patient.setId(id);

			// Replace with validation
			validateString(patient.getDoctorName());
			data.setPatient(patient);
			Backend.message.setData(data);
			doPostRespondHeader(Constant.UPDATE_PATIENT_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

}
