package com.sonar.vishal.ui.listener.role;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.ValidationException;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;

public class AddRoleListener extends CRUDListener {

	private static final long serialVersionUID = 6381327197810779730L;
	private Binder<Role> roleBinder;

	public AddRoleListener(Binder<Role> roleBinder, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.ADD_ROLE, window);
		this.roleBinder = roleBinder;
	}

	@Override
	protected void doAction() {
		try {
			Role role = new Role();
			RoleData data = new RoleData();
			this.roleBinder.writeBean(role);
			role.setModule(RoleOptionValueListener.getSelectedOption());
			validateString(role.getName());
			validateString(role.getModule());
			data.setRole(role);
			Backend.message.setData(data);
			doPostRespondHeader(Constant.ADD_ROLE_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

}
