package com.sonar.vishal.ui.listener;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Password;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.exception.ValidationException;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.util.UIUtil;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

public class ChangePasswordListener extends CRUDListener {

	private static final long serialVersionUID = 6599959954201666323L;
	private Binder<Password> passwordBinder;
	private Password password;
	private Window changePasswordWindow;

	public ChangePasswordListener(Binder<Password> passwordBinder, Window changePasswordWindow) {
		super(null, Constant.UPDATE_USER, null);
		this.passwordBinder = passwordBinder;
		this.password = new Password();
		this.changePasswordWindow = changePasswordWindow;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Object userObject = VaadinSession.getCurrent().getSession().getAttribute("user");
		if (userObject instanceof User) {
			try {
				passwordBinder.writeBean(password);
				User user = (User) userObject;
				validatePassword(user);
				user.setPassword(password.getConfirmPassword());
				UserData data = new UserData();
				data.setUser(user);
				Backend.message.setData(data);
				doPostRespondHeader(Constant.UPDATE_USER_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
				changePasswordWindow.close();
			} catch (ValidationException e) {
				notifyError(e.getMessage());
			} catch (Exception e) {
				notifyError(Constant.GENERAL_ERROR_MESSAGE);
			}
		} else {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

	private void validatePassword(User user) throws ValidationException {
		if (UIUtil.isAlphaNumericStringOfN(password.getPassword(), UIConstant.TWENTY)) {
			throw new ValidationException(UIConstant.INVALID_REQUIRED_FIELD);
		}
		if (UIUtil.isAlphaNumericStringOfN(password.getNewPassword(), UIConstant.TWENTY)) {
			throw new ValidationException(UIConstant.INVALID_REQUIRED_FIELD);
		}
		if (UIUtil.isAlphaNumericStringOfN(password.getConfirmPassword(), UIConstant.TWENTY)) {
			throw new ValidationException(UIConstant.INVALID_REQUIRED_FIELD);
		}
		if (password.getConfirmPassword().length() < UIConstant.PASSWORD_MIN_LENGTH) {
			throw new ValidationException(UIConstant.SHORT_PASSWORD_LENGTH);
		}
		if (!user.getPassword().equals(password.getPassword())) {
			throw new ValidationException(UIConstant.INVALID_PASSWORD);
		}
		if (!password.getNewPassword().equals(password.getConfirmPassword())) {
			throw new ValidationException(UIConstant.PASSWORD_MISMATCH);
		}
	}

}
