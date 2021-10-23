package com.sonar.vishal.ui.structure;

import java.util.Properties;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.medico.common.util.Reader;
import com.sonar.vishal.ui.definition.GenericStructure;
import com.sonar.vishal.ui.util.UIConstant;

public class UserAccessStructure extends UserStructure implements GenericStructure {
	
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
		Properties property = Reader.loadProperties(this, UIConstant.PROPERTIES_FILE_NAME);
		String password = property.getProperty(UIConstant.RESET_STRING_LITERAL);
		if (password == null) {
			notifyError(UIConstant.RESET_PASSWORD_FAILED);
			return;
		}
		UserData userData = new UserData();
		selectedUser.setLoginAttempt(0);
		selectedUser.setPassword(password);
		userData.setUser(selectedUser);
		doPostRespondHeader(userData, Constant.UPDATE_USER_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
	}

}
