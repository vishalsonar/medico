package com.sonar.vishal.ui.listener;

import com.sonar.vishal.ui.definition.CRUDStructure;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class UpdateButtonListener implements ClickListener {

	private static final long serialVersionUID = -2546912365344481582L;
	private CRUDStructure structure;

	public UpdateButtonListener(CRUDStructure structure) {
		this.structure = structure;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		this.structure.update();
	}

}
