package com.sonar.vishal.ui.validator;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.structure.LoginData;
import com.sonar.vishal.ui.definition.Validator;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIUtil;

public class ForgotPasswordValidator implements Validator<LoginData> {

	@Override
	public void doValidation(LoginData data) throws MedicoValidationException {
		if (data == null) {
			throw new MedicoValidationException(Constant.INVALID_USER_NAME);
		}
		if (!UIUtil.isAlphaNumericString(data.getUserName())) {
			throw new MedicoValidationException(Constant.INVALID_USER_NAME);
		}
	}

}
