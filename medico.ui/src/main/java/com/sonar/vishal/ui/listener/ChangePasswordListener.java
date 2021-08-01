package com.sonar.vishal.ui.listener;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Password;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.validator.PasswordDataValidator;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

public class ChangePasswordListener extends CRUDListener {

	private static final long serialVersionUID = 6599959954201666323L;
	private Binder<Password> passwordBinder;
	private transient Password password;
	private Window changePasswordWindow;

	public ChangePasswordListener(Binder<Password> passwordBinder, Window changePasswordWindow) {
		super(null, Constant.UPDATE_USER, null);
		this.passwordBinder = passwordBinder;
		this.password = new Password();
		this.changePasswordWindow = changePasswordWindow;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Object userObject = VaadinSession.getCurrent().getSession().getAttribute(UIConstant.S_USER);
		if (userObject instanceof User) {
			try {
				passwordBinder.writeBean(password);
				User user = (User) userObject;
				new PasswordDataValidator().doValidation(password);
				user.setPassword(password.getConfirmPassword());
				UserData data = new UserData();
				data.setUser(user);
				Backend.message.setData(data);
				doPostRespondHeader(Constant.USER_PASSWORD_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
				changePasswordWindow.close();
			} catch (MedicoValidationException e) {
				notifyError(e.getMessage());
			} catch (Exception e) {
				LoggerApi.error(getClass().getName(), e.getMessage());
				notifyError(Constant.GENERAL_ERROR_MESSAGE);
			}
		} else {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

}
