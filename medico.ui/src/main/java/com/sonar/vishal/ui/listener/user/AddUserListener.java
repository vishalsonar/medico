package com.sonar.vishal.ui.listener.user;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class AddUserListener extends CRUDListener {

	private static final long serialVersionUID = -6795980482409634717L;
	private Binder<User> userBinder;
	private transient UserListenerLogic logic;

	public AddUserListener(Binder<User> userBinder, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.ADD_USER, window);
		this.userBinder = userBinder;
		this.logic = new UserListenerLogic();
	}

	@Override
	protected void doAction() {
		try {
			Backend.message.setData(logic.process(userBinder, null));
			doPostRespondHeader(Constant.ADD_USER_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

}
