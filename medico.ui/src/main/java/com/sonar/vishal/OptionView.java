package com.sonar.vishal;

import com.sonar.vishal.ui.structure.OptionStructure;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;

public class OptionView extends MedicoView {

	private static final long serialVersionUID = 5565865641978914486L;

	public OptionView() {
		super("Option");
	}

	@Override
	public void setUI() {
		final Panel loginPanel = (Panel) new OptionStructure().get();
		rightLayout.addComponent(loginPanel);
		rightLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		rightLayout.setSizeFull();
	}
}
