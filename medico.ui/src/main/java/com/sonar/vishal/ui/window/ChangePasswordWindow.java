package com.sonar.vishal.ui.window;

import java.util.Objects;

import com.sonar.vishal.MedicoUI;
import com.sonar.vishal.medico.common.pojo.Password;
import com.sonar.vishal.ui.listener.ChangePasswordListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.data.Binder;
import com.vaadin.ui.PasswordField;

public class ChangePasswordWindow extends MedicoWindow {

	private static final long serialVersionUID = 3229424004527229673L;
	private Binder<Password> passwordBinder = new Binder<>();

	public ChangePasswordWindow() {
		super(UIConstant.CHANGE_PASSWORD, null);
	}

	@Override
	public void setWindow() {
		PasswordField password = component.getPasswordField(UIConstant.OLD_PASSWORD, UIConstant.OLD_PASSWORD, UIConstant.FIELD_LENGTH_300);
		PasswordField newPassword = component.getPasswordField(UIConstant.NEW_PASSWORD, UIConstant.NEW_PASSWORD, UIConstant.FIELD_LENGTH_300);
		PasswordField confirmPassword = component.getPasswordField(UIConstant.CONFIRM_PASSWORD, UIConstant.CONFIRM_PASSWORD, UIConstant.FIELD_LENGTH_300);
		passwordBinder.bind(password, Password::getPassword, Password::setPassword);
		passwordBinder.bind(newPassword, Password::getNewPassword, Password::setNewPassword);
		passwordBinder.bind(confirmPassword, Password::getConfirmPassword, Password::setConfirmPassword);
		addComponents(password, newPassword, confirmPassword);
		addAction();
		addSubmitListener(new ChangePasswordListener(passwordBinder, this));
		addCancelListenerToThisWindow();
		addCancelListener(this);
	}

	private void addCancelListenerToThisWindow() {
		this.addCloseListener(new CloseListener() {

			private static final long serialVersionUID = 5576724209334374475L;

			@Override
			public void windowClose(CloseEvent e) {
				MedicoUI.getNavigatorUI().navigateTo(UIConstant.OPTION_PAGE);
			}
		});
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(passwordBinder);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangePasswordWindow other = (ChangePasswordWindow) obj;
		return Objects.equals(passwordBinder, other.passwordBinder);
	}
	
}
