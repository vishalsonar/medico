package com.sonar.vishal.ui.listener;

import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class WindowCloseListener implements ClickListener {

	private static final long serialVersionUID = 9002913844207195785L;
	private MedicoWindow window;

	public WindowCloseListener(MedicoWindow window) {
		this.window = window;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		window.close();
	}

}
