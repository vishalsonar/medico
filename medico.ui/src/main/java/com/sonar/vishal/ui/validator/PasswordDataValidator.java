package com.sonar.vishal.ui.validator;

import com.sonar.vishal.medico.common.pojo.Password;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.ui.definition.Validator;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.server.VaadinSession;

public class PasswordDataValidator implements Validator<Password> {

	private static final String EXISTING_MISSMATCH_PASSWORD = "Password Mismatched with Existing Password";
	private static final String MISSMATCH_PASSWORD = "New Password Mismatched with Confirm Password";
	private static final String SHORT_LENGTH_PASSWORD = "Password length is to short";

	@Override
	public void doValidation(Password data) throws MedicoValidationException {
		Object userObject = VaadinSession.getCurrent().getSession().getAttribute(UIConstant.S_USER);
		User user = (User) userObject;
		String currentPassword = user.getPassword();
		String password = data.getPassword();
		String newPassword = data.getNewPassword();
		String confirmPassword = data.getConfirmPassword();
		if (currentPassword.trim().length() == 0 || password.trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (newPassword.trim().length() == 0 || confirmPassword.trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (!currentPassword.equals(password)) {
			throw new MedicoValidationException(EXISTING_MISSMATCH_PASSWORD);
		}
		if (!newPassword.equals(confirmPassword)) {
			throw new MedicoValidationException(MISSMATCH_PASSWORD);
		}
		if (newPassword.length() <= 6) {
			throw new MedicoValidationException(SHORT_LENGTH_PASSWORD);
		}
	}

}
