package com.sonar.vishal.ui.listener.user;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Password;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class UpdateUserListener extends CRUDListener {

	private static final long serialVersionUID = -1165695479980580459L;
	private Binder<User> userBinder;
	private Binder<Password> passwordBinder;
	private transient UserListenerLogic logic;

	public UpdateUserListener(Binder<User> userBinder, Binder<Password> passwordBinder, int id, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.UPDATE_USER, window);
		this.userBinder = userBinder;
		this.id = id;
		this.logic = new UserListenerLogic();
		this.passwordBinder = passwordBinder;
	}
	
	@Override
	protected void doAction() {
		try {
			Backend.message.setData(logic.process(userBinder, passwordBinder, id));
			doPostRespondHeader(Constant.UPDATE_USER_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
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
