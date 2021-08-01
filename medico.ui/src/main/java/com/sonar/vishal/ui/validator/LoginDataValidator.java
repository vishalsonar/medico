package com.sonar.vishal.ui.validator;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.structure.LoginData;
import com.sonar.vishal.ui.definition.Validator;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIUtil;

public class LoginDataValidator implements Validator<LoginData> {

	@Override
	public void doValidation(LoginData data) throws MedicoValidationException {
		if (data == null) {
			throw new MedicoValidationException(Constant.LOGIN_FAILURE_MESSAGE);
		}
		if (!UIUtil.isAlphaNumericString(data.getUserName())) {
			throw new MedicoValidationException(Constant.LOGIN_FAILURE_MESSAGE);
		}
		if (data.getPassword().trim().length() == 0) {
			throw new MedicoValidationException(Constant.LOGIN_FAILURE_MESSAGE);
		}
	}

}
