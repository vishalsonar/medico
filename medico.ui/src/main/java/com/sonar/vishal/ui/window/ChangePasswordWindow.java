package com.sonar.vishal.ui.window;

import com.sonar.vishal.MedicoUI;
import com.sonar.vishal.medico.common.pojo.Password;
import com.sonar.vishal.ui.listener.ChangePasswordListener;
import com.vaadin.data.Binder;
import com.vaadin.ui.PasswordField;

public class ChangePasswordWindow extends MedicoWindow {

	private static final long serialVersionUID = 3229424004527229673L;
	private Binder<Password> passwordBinder = new Binder<>();

	public ChangePasswordWindow() {
		super("Change Password", null);
	}

	@Override
	public void setWindow() {
		PasswordField password = COMPONENT.getPasswordField("Old Password", "Old Password", "300");
		PasswordField newPassword = COMPONENT.getPasswordField("New Password", "New Password", "300");
		PasswordField confirmPassword = COMPONENT.getPasswordField("Confirm Password", "Confirm Password", "300");
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
				MedicoUI.getNavigatorUI().navigateTo("optionpage");
			}
		});
	}
}
