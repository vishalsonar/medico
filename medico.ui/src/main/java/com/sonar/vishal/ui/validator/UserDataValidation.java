package com.sonar.vishal.ui.validator;

import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.ui.definition.Validator;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.util.UIUtil;

public class UserDataValidation implements Validator<UserData> {

	private static final String INVALID_USER_NAME = "Entered Invalid User Name";
	private static final String MISSMATCH_PASSWORD = "Password Mismatched with Confirm Password";
	private static final String SHORT_LENGTH_PASSWORD = "Password length is to short";
	private static final String PLEASE_SELECT_ROLE = "Invalid assigned to role";
	
	private String confirmPassword;

	public UserDataValidation(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public void doValidation(UserData data) throws MedicoValidationException {
		User user = data.getUser();
		String password = user.getPassword();
		String userName = user.getUserName();
		RoleData roleData = new RoleData();
		roleData.setRole(user.getRole());
		if (userName.trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (password.trim().length() == 0 || confirmPassword.length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (!UIUtil.isString(userName)) {
			throw new MedicoValidationException(INVALID_USER_NAME);
		}
		if (!password.equals(confirmPassword)) {
			throw new MedicoValidationException(MISSMATCH_PASSWORD);
		}
		if (password.length() <= 6) {
			throw new MedicoValidationException(SHORT_LENGTH_PASSWORD);
		}
		if (user.getRole() == null) {
			throw new MedicoValidationException(PLEASE_SELECT_ROLE);
		}
		new RoleDataValidator().doValidation(roleData);
	}

}
