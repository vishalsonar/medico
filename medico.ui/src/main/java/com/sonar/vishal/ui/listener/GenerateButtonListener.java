package com.sonar.vishal.ui.listener;

import com.sonar.vishal.ui.definition.GenerateStructure;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class GenerateButtonListener implements ClickListener {

	private static final long serialVersionUID = 6292121779261797226L;
	private transient GenerateStructure generateStructure;

	public GenerateButtonListener(GenerateStructure generateStructure) {
		this.generateStructure = generateStructure;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		this.generateStructure.generate();
	}

}
