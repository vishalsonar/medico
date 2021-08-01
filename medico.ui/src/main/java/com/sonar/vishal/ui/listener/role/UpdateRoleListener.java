package com.sonar.vishal.ui.listener.role;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class UpdateRoleListener extends CRUDListener {

	private static final long serialVersionUID = 2432222050770117485L;
	private Binder<Role> roleBinder;
	private transient RoleListenerLogic logic;

	public UpdateRoleListener(Binder<Role> roleBinder, int id, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.UPDATE_ROLE, window);
		this.roleBinder = roleBinder;
		this.id = id;
		this.logic = new RoleListenerLogic();
	}

	@Override
	protected void doAction() {
		try {
			Backend.message.setData(logic.process(roleBinder, id));
			doPostRespondHeader(Constant.UPDATE_ROLE_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (MedicoValidationException e) {
			notifyError(e.getMessage());
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			LoggerApi.error(getClass().getName(), e.getMessage());
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

}
