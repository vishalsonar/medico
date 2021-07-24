package com.sonar.vishal.ui.listener.patient;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class AddPatientListener extends CRUDListener {

	private static final long serialVersionUID = 1192220773813275312L;
	private Binder<Patient> patientBinder;
	private Binder<Address> addressBinder;
	private transient PatientListenerLogic logic;

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
		} catch (MedicoValidationException e) {
			notifyError(e.getMessage());
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			LoggerApi.error(getClass().getName(), e.getMessage());
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

}
