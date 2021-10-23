package com.sonar.vishal.ui.listener;

import com.sonar.vishal.MedicoUI;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;

public class SignUpLinkListener implements ClickListener {

	private static final long serialVersionUID = 1193255767127006475L;
	TextField userName;

	public SignUpLinkListener(TextField userName) {
		this.userName = userName;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		userName.setValue(UIConstant.EMPTY);
		MedicoUI.getNavigatorUI().navigateTo(UIConstant.EMPTY);
	}

}
