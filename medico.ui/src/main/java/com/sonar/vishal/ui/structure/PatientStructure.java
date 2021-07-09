package com.sonar.vishal.ui.structure;

import java.util.Optional;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.structure.PatientData;
import com.sonar.vishal.ui.backend.RestBackend;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.sonar.vishal.ui.window.patient.AddPatientWindow;
import com.sonar.vishal.ui.window.patient.UpdatePatientWindow;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PatientStructure implements CRUDStructure {

	private VerticalLayout layout;
	private Grid<Patient> table;
	private RestBackend backend;
	private Patient selectedPatient;
	private Notification notification;

	public PatientStructure() {
		layout = new VerticalLayout();
		table = new Grid<>();
		table.setSizeFull();
		table.setSelectionMode(SelectionMode.SINGLE);
		layout.addComponent(table);
	}

	@Override
	public Object get() {
		list();
		table.addColumn(Patient::getId).setCaption(UIConstant.ID);
		table.addColumn(Patient::getPatientName).setCaption(UIConstant.PATIENT_NAME);
		table.addColumn(Patient::getDoctorName).setCaption(UIConstant.DOCTOR_NAME);
		table.addColumn(Patient::getPhoneNumber).setCaption(UIConstant.PHONE_NUMBER);
		table.addColumn(Patient::getAddressString).setCaption(UIConstant.ADDRESS);
		table.addSelectionListener(new SelectionListener<Patient>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectionChange(SelectionEvent<Patient> event) {
				try {
					Optional<Patient> optionalPatient = event.getFirstSelectedItem();
					if (optionalPatient.isPresent()) {
						selectedPatient = optionalPatient.get();
					}
				} catch(Exception e) {
					// Do Nothing.
				}
			}
		});
		return layout;
	}

	@Override
	public void list() {
		backend = new RestBackend(Constant.GET_PATIENT_LIST);
		Patient[] data = (Patient[]) backend.doPostRespondData(Patient[].class);
		table.setItems(data);
	}

	@Override
	public void add() {
		MedicoWindow window = new AddPatientWindow(this);
		window.setWindow();
		UI.getCurrent().addWindow(window);
	}

	@Override
	public void update() {
		try {
			MedicoWindow window = new UpdatePatientWindow(this, selectedPatient);
			window.setWindow();
			UI.getCurrent().addWindow(window);
			selectedPatient = null;
		} catch (Exception e) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.SELECT_ROW_TO_UPDATE);
			notification.show(Page.getCurrent());
		}
	}

	@Override
	public void delete() {
		if (selectedPatient == null) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.SELECT_ROW_TO_DELETE);
		} else {
			PatientData data = new PatientData();
			data.setPatient(selectedPatient);
			backend = new RestBackend(Constant.DELETE_PATIENT);
			Backend.message.setData(data);
			boolean response = backend.doPostRespondHeader();
			if (response) {
				notification = Component.getInstance().getSuccessNotification(Constant.SUCCESS, Constant.DELETE_PATIENT_SUCCESS_MESSAGE);
				list();
				selectedPatient = null;
			} else {
				notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.GENERAL_ERROR_MESSAGE);
			}
		}
		notification.show(Page.getCurrent());
	}

}
