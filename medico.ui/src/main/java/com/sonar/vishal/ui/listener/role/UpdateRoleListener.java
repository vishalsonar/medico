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

public class UpdateRoleListener extends CRUDListener {

	private static final long serialVersionUID = 2432222050770117485L;
	private Binder<Role> roleBinder;
	private int id;

	public UpdateRoleListener(Binder<Role> roleBinder, int id, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.UPDATE_ROLE, window);
		this.roleBinder = roleBinder;
		this.id = id;
	}

	@Override
	protected void doAction() {
		try {
			Role role = new Role();
			RoleData data = new RoleData();
			this.roleBinder.writeBean(role);
			role.setId(id);
			role.setModule(RoleOptionValueListener.getSelectedOption());
			validateString(role.getName());
			validateString(role.getModule());
			data.setRole(role);
			Backend.message.setData(data);
			doPostRespondHeader(Constant.UPDATE_ROLE_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

}
