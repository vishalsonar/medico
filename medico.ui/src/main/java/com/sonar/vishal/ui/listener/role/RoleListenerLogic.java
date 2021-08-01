package com.sonar.vishal.ui.listener.role;

import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.ui.definition.ListenerLogic;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.validator.RoleDataValidator;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class RoleListenerLogic implements ListenerLogic<Binder<Role>> {

	@Override
	public Data process(Binder<Role> binder, Integer id) throws ValidationException, MedicoValidationException {
		Role role = new Role();
		RoleData data = new RoleData();
		binder.writeBean(role);
		if (id != null) {
			role.setId(id);
		}
		role.setModule(RoleOptionValueListener.getSelectedOption());
		data.setRole(role);
		new RoleDataValidator().doValidation(data);
		return data;
	}

}
