package com.sonar.vishal.ui.structure;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.definition.GenericStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.server.Page;

public class UserAccessStructure extends UserStructure implements GenericStructure {

	private void notifySuccess(String message) {
		notification = Component.getInstance().getSuccessNotification(Constant.SUCCESS, message);
		notification.show(Page.getCurrent());
	}

	private void notifyError(String message) {
		notification = Component.getInstance().getFailureNotification(Constant.ERROR, message);
		notification.show(Page.getCurrent());
	}

	private void doPostRespondHeader(UserData userData, String successMessage, String errorMessage) {
		Backend.message.setData(userData);
		Backend.message.getHeader().setFunction(Constant.UPDATE_USER);
		boolean response = backend.doPostRespondHeader();
		if (response) {
			notifySuccess(successMessage);
		} else {
			notifyError(errorMessage);
		}
	}

	@Override
	public void action(int token) {
		if (selectedUser == null) {
			notifyError(Constant.SELECT_ROW_TO_DELETE);
			return;
		}
		if (token == 1) {
			buttonOneaction();
		}
		if (token == 2) {
			buttonTwoaction();
		}
		if (token == 3) {
			buttonThreeaction();
		}
		if (token == 1 || token == 2 || token == 3) {
			list();
		}
	}

	@Override
	public void buttonOneaction() {
		UserData userData = new UserData();
		selectedUser.setLoginAttempt(3);
		userData.setUser(selectedUser);
		doPostRespondHeader(userData, Constant.UPDATE_USER_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
	}

	@Override
	public void buttonTwoaction() {
		UserData userData = new UserData();
		selectedUser.setLoginAttempt(0);
		userData.setUser(selectedUser);
		doPostRespondHeader(userData, Constant.UPDATE_USER_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
	}

	@Override
	public void buttonThreeaction() {
		UserData userData = new UserData();
		selectedUser.setLoginAttempt(0);
		selectedUser.setPassword(UIConstant.RESET_PASSWORD_STRING);
		userData.setUser(selectedUser);
		doPostRespondHeader(userData, Constant.UPDATE_USER_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
	}

}
