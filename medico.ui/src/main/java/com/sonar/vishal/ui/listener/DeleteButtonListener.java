package com.sonar.vishal.ui.listener;

import com.sonar.vishal.ui.definition.CRUDStructure;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class DeleteButtonListener implements ClickListener {

	private static final long serialVersionUID = 3725027732893318339L;
	private CRUDStructure structure;

	public DeleteButtonListener(CRUDStructure structure) {
		this.structure = structure;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		this.structure.delete();
	}

}
