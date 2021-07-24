package com.sonar.vishal.ui.listener.patient;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class UpdatePatientListener extends CRUDListener {

	private static final long serialVersionUID = -647560492328455522L;
	private Binder<Patient> patientBinder = new Binder<>();
	private Binder<Address> addressBinder = new Binder<>();
	private transient PatientListenerLogic logic;

	public UpdatePatientListener(Binder<Patient> patientBinder, Binder<Address> addressBinder, int id, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.UPDATE_PATIENT, window);
		this.id = id;
		this.patientBinder = patientBinder;
		this.addressBinder = addressBinder;
		this.logic = new PatientListenerLogic();
	}

	@Override
	protected void doAction() {
		try {
			Backend.message.setData(logic.process(patientBinder, addressBinder, id));
			doPostRespondHeader(Constant.UPDATE_PATIENT_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (MedicoValidationException e) {
			notifyError(e.getMessage());
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

}
