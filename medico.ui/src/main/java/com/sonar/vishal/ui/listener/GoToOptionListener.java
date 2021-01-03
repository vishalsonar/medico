package com.sonar.vishal.ui.listener;

import com.sonar.vishal.MedicoUI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class GoToOptionListener implements ClickListener {

	private static final long serialVersionUID = 8647360326444775959L;

	@Override
	public void buttonClick(ClickEvent event) {
		MedicoUI.getNavigatorUI().navigateTo("optionpage");
	}

}
