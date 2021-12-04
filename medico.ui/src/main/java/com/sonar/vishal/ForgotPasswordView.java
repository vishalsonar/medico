package com.sonar.vishal;

import com.sonar.vishal.ui.structure.ForgotPasswordStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;

public class ForgotPasswordView extends MedicoView {

	private static final long serialVersionUID = 2835030691688780204L;

	public ForgotPasswordView() {
		super(UIConstant.MEDICO);
	}

	@Override
	public void setUI() {
		final Panel loginPanel = (Panel) new ForgotPasswordStructure().get();
		rightLayout.addComponent(loginPanel);
		rightLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		rightLayout.setSizeFull();
	}
}
