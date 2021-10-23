package com.sonar.vishal.ui.listener.role;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.ui.definition.ListenerLogic;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.util.UIUtil;
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
		if (id == null) {
			JsonObject responseObject = (JsonObject) UIUtil.getSearchBackend(Constant.SEARCH_ROLE, role.getName()).doPostRespondData(Role[].class);
			Role[] userList = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Role[].class);
			for (Role entry : userList) {
				if (entry.getName().equals(role.getName())) {
					throw new MedicoValidationException(UIConstant.ROLE_NAME_EXIST);
				}
			}
		}
		return data;
	}

}
