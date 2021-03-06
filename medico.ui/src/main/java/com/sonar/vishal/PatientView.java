package com.sonar.vishal;

import com.sonar.vishal.ui.structure.PatientStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.ui.VerticalLayout;

public class PatientView extends MedicoView {

	private static final long serialVersionUID = -1462388788386186290L;

	public PatientView() {
		super(UIConstant.PATIENT, UIConstant.ADD_PATIENT, UIConstant.UPDATE_PATIENT, UIConstant.DELETE_PATIENT);
	}

	@Override
	public void setUI() {
		this.structure = new PatientStructure();
		rightLayout = (VerticalLayout) structure.get();
		rightLayout.setSizeFull();
	}
}
