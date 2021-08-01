package com.sonar.vishal.ui.validator;

import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.ui.definition.Validator;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIUtil;

public class RoleDataValidator implements Validator<RoleData> {

	private static final String INVALID_ROLE_NAME = "Entered Invalid Role Name";
	private static final String PLEASE_SELECT_MODULE = "Invalid assigned Modules";

	@Override
	public void doValidation(RoleData data) throws MedicoValidationException {
		Role role = data.getRole();
		String name = role.getName();
		String module = role.getModule();
		if (!UIUtil.isString(name)) {
			throw new MedicoValidationException(INVALID_ROLE_NAME);
		}
		if (module == null) {
			throw new MedicoValidationException(PLEASE_SELECT_MODULE);
		}
		if (module.trim().length() == 0) {
			throw new MedicoValidationException(PLEASE_SELECT_MODULE);
		}
	}

}
