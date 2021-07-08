package com.sonar.vishal.ui.window.user;

import java.util.Objects;

import com.sonar.vishal.ui.listener.user.AddUserListener;
import com.sonar.vishal.ui.structure.UserStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;

public class AddUserWindow extends MedicoWindow {

	private static final long serialVersionUID = 7007173310923942341L;
	private transient UserWindowDecorator decorator;

	public AddUserWindow(UserStructure structure) {
		super(UIConstant.ADD_USER, structure);
		decorator = new UserWindowDecorator();
	}
	
	@Override
	public void setWindow() {
		addComponents(decorator.name, decorator.password, decorator.confirmPassword, decorator.optionGroup);
		addAction();
		addCancelListener(this);
		addSubmitListener(new AddUserListener(decorator.userBinder, this, structure));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(decorator);
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
		AddUserWindow other = (AddUserWindow) obj;
		return Objects.equals(decorator, other.decorator);
	}
	
}
