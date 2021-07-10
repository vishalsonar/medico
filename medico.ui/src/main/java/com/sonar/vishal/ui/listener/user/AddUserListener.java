package com.sonar.vishal.ui.listener.user;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Password;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class AddUserListener extends CRUDListener {

	private static final long serialVersionUID = -6795980482409634717L;
	private Binder<User> userBinder;
	private Binder<Password> passwordBinder;
	private transient UserListenerLogic logic;

	public AddUserListener(Binder<User> userBinder, Binder<Password> passwordBinder, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.ADD_USER, window);
		this.userBinder = userBinder;
		this.passwordBinder = passwordBinder;
		this.logic = new UserListenerLogic();
	}

	@Override
	protected void doAction() {
		try {
			Backend.message.setData(logic.process(userBinder, passwordBinder, null));
			doPostRespondHeader(Constant.ADD_USER_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (MedicoValidationException e) {
			notifyError(e.getMessage());
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

}
