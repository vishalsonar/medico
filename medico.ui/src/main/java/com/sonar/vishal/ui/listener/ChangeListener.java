package com.sonar.vishal.ui.listener;

import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;

public class ChangeListener implements ViewChangeListener {

	private static final long serialVersionUID = 2332035094615125633L;

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		boolean state = false;
		final String name = event.getViewName();
		Object data = VaadinSession.getCurrent().getSession().getAttribute(UIConstant.S_USER);
		if (data instanceof User) {
			User user = (User) data;
			if (user.getRole() != null) {
				state = true;
			}
		}
		if (name.equals(UIConstant.S_FORGOT_PASSWORD_URL) || name.equals(UIConstant.EMPTY)) {
			state = true;
		}
		return state;
	}

}
