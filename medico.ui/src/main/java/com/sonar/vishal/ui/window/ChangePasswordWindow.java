package com.sonar.vishal.ui.window;

import com.sonar.vishal.MedicoUI;
import com.vaadin.ui.PasswordField;

public class ChangePasswordWindow extends MedicoWindow {

	private static final long serialVersionUID = 3229424004527229673L;

	public ChangePasswordWindow() {
		super("Change Password", null);
	}

	@Override
	public void setWindow() {
		PasswordField oldPassword = COMPONENT.getPasswordField("Old Password", "Old Password", "300");
		PasswordField password = COMPONENT.getPasswordField("New Password", "New Password", "300");
		PasswordField confirmPassword = COMPONENT.getPasswordField("Confirm Password", "Confirm Password", "300");
		addComponents(oldPassword, password, confirmPassword);
		addAction();
		addCancelListener(this);
		this.addCloseListener(new CloseListener() {

			private static final long serialVersionUID = 5576724209334374475L;

			@Override
			public void windowClose(CloseEvent e) {
				MedicoUI.getNavigatorUI().navigateTo("optionpage");
			}
		});
		// addSubmitListener(new AddUserListener(userBinder, this, structure));
	}
}
