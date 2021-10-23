package com.sonar.vishal.ui.listener;

import com.sonar.vishal.ui.definition.GenericStructure;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class GenericButtonListener implements ClickListener {

	private static final long serialVersionUID = -7459906845038147424L;
	private transient GenericStructure structure;
	private int token;

	public GenericButtonListener(GenericStructure structure, int token) {
		this.structure = structure;
		this.token = token;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		this.structure.action(token);
	}

}
