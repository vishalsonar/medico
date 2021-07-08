package com.sonar.vishal.ui.window.role;

import java.util.Objects;

import com.sonar.vishal.ui.listener.role.AddRoleListener;
import com.sonar.vishal.ui.structure.RoleStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;

public class AddRoleWindow extends MedicoWindow {

	private static final long serialVersionUID = 791231962125024417L;
	private transient RoleWindowDecorator decorator;

	public AddRoleWindow(RoleStructure structure) {
		super(UIConstant.ADD_ROLE, structure);
		decorator = new RoleWindowDecorator();
	}

	@Override
	public void setWindow() {
		addComponents(decorator.name, decorator.optionGroup);
		addAction();
		addCancelListener(this);
		addSubmitListener(new AddRoleListener(decorator.roleBinder, this, structure));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(decorator) + Objects.hash(UIConstant.ROLE_SALT);
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
		AddRoleWindow other = (AddRoleWindow) obj;
		return Objects.equals(decorator, other.decorator);
	}
	
}
