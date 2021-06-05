package com.sonar.vishal.ui.structure;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.ui.backend.RestBackend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;

public class PatientStructure implements CRUDStructure {

	private VerticalLayout layout;
	private Grid<Patient> table;
	private RestBackend backend;

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
		table.addColumn(Patient::getId).setCaption("Id");
		table.addColumn(Patient::getPatientName).setCaption("Patient Name");
		table.addColumn(Patient::getDoctorName).setCaption("Doctor Name");
		table.addColumn(Patient::getPhoneNumber).setCaption("Phone Number");
		table.addColumn(Patient::getAddressString).setCaption("Address");
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
		// Do Nothing
	}

	@Override
	public void update() {
		// Do Nothing
	}

	@Override
	public void delete() {
		// Do Nothing
	}

}
