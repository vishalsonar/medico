package com.sonar.vishal;

import com.sonar.vishal.ui.structure.PatientStructure;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class PatientView extends MedicoView {

	private static final long serialVersionUID = -1462388788386186290L;

	public PatientView() {
		super("Patient");
		Button gotoOption = component.gotoOptionButton();
		leftLayout.addComponents(gotoOption);
	}

	@Override
	public void setUI() {
		this.structure = new PatientStructure();
		rightLayout = (VerticalLayout) structure.get();
		rightLayout.setSizeFull();
	}
}
