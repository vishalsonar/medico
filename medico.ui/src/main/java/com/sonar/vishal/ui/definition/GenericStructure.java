package com.sonar.vishal.ui.definition;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.ui.component.Component;
import com.vaadin.server.Page;

public interface GenericStructure extends Structure {

	public void action(int token);

	public void buttonOneaction();

	public void buttonTwoaction();

	public void buttonThreeaction();

	public default void notifySuccess(String message) {
		Component.getInstance().getSuccessNotification(Constant.SUCCESS, message).show(Page.getCurrent());
	}

	public default void notifyError(String message) {
		Component.getInstance().getFailureNotification(Constant.ERROR, message).show(Page.getCurrent());
	}
}
