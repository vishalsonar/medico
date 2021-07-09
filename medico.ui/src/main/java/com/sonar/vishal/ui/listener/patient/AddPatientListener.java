package com.sonar.vishal.ui.listener.patient;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Patient;
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
	private PatientListenerLogic logic;

	public AddPatientListener(Binder<Patient> patientBinder, Binder<Address> addressBinder, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.ADD_PATIENT, window);
		this.patientBinder = patientBinder;
		this.addressBinder = addressBinder;
		this.logic = new PatientListenerLogic();
	}
	
	@Override
	protected void doAction() {
		try {
			Backend.message.setData(logic.process(patientBinder, addressBinder, null));
			doPostRespondHeader(Constant.ADD_PATIENT_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

}
