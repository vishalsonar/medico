package com.sonar.vishal.ui.listener;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class LogoutListener implements ClickListener {

	private static final long serialVersionUID = 5540545641621078939L;

	@Override
	public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
		Notification notification = Component.getInstance().getSuccessNotification(Constant.SUCCESS, UIConstant.LOGGED_OUT_SUCCESS);
		VaadinSession.getCurrent().getSession().invalidate();
		notification.show(Page.getCurrent());
	}

}
