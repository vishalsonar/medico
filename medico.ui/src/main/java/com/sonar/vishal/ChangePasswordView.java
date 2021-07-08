package com.sonar.vishal;

import com.sonar.vishal.ui.window.ChangePasswordWindow;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.UI;

public class ChangePasswordView extends MedicoView {

	private static final long serialVersionUID = -5733280017414108518L;

	public ChangePasswordView() {
		super("");
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (!isDirty) {
			displayUI();
		}
		MedicoWindow window = new ChangePasswordWindow();
		window.setWindow();
		UI.getCurrent().addWindow(window);
	}
}
