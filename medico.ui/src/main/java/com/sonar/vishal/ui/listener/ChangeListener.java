package com.sonar.vishal.ui.listener;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;

public class ChangeListener implements ViewChangeListener {

	private static final long serialVersionUID = 2332035094615125633L;

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		Object userName = VaadinSession.getCurrent().getAttribute("UserName");
		Object role = VaadinSession.getCurrent().getAttribute("Role");
		if (userName == null || role == null) {
			return false;
		}
		return true;
	}

}
