package com.sonar.vishal.ui.listener;

import com.sonar.vishal.ui.definition.CRUDStructure;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class AddButtonListener implements ClickListener {

	private static final long serialVersionUID = 7244765390058050494L;
	private transient CRUDStructure structure;

	public AddButtonListener(CRUDStructure structure) {
		this.structure = structure;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		this.structure.add();
	}

}
