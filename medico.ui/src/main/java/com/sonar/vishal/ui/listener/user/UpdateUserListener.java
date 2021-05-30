package com.sonar.vishal.ui.listener.user;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.ValidationException;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;

public class UpdateUserListener extends CRUDListener {

	private static final long serialVersionUID = -1165695479980580459L;
	private Binder<User> userBinder;
	private int id;

	public UpdateUserListener(Binder<User> userBinder, int id, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.UPDATE_USER, window);
		this.userBinder = userBinder;
		this.id = id;
	}
	
	@Override
	protected void doAction() {
		try {
			User user = new User();
			userBinder.writeBean(user);
			user.setId(id);
			if (UserRoleValueListener.selectedRole == null) {
				throw new ValidationException();
			}
			user.setRole(UserRoleValueListener.selectedRole);
			validateString(user.getUserName());
			UserData data = new UserData();
			data.setUser(user);
			Backend.message.setData(data);
			doPostRespondHeader(Constant.UPDATE_USER_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}
}
