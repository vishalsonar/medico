package com.sonar.vishal;

import com.sonar.vishal.ui.structure.LoginStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;

public class LoginView extends MedicoView {

	private static final long serialVersionUID = -4955808842847956150L;

	public LoginView() {
		super(UIConstant.MEDICO);
	}

	@Override
	public void setUI() {
		final Panel loginPanel = (Panel) new LoginStructure().get();
		rightLayout.addComponent(loginPanel);
		rightLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		rightLayout.setSizeFull();
	}

}
