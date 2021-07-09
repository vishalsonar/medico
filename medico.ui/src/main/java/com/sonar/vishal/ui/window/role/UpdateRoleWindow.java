package com.sonar.vishal.ui.window.role;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.listener.role.UpdateRoleListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;

public class UpdateRoleWindow extends MedicoWindow {

	private static final long serialVersionUID = -3029061649785789120L;
	private transient RoleWindowDecorator decorator;
	private transient Role selectedRole;

	public UpdateRoleWindow(CRUDStructure structure, Role selectedRole) {
		super(UIConstant.UPDATE_ROLE, structure);
		decorator = new RoleWindowDecorator();
		this.selectedRole = selectedRole;
	}

	@Override
	public void setWindow() {
		decorator.name.setValue(selectedRole.getName());
		String[] optionArray = selectedRole.getModule().split(UIConstant.COMMA);
		decorator.optionGroup.setValue(new HashSet<>(Arrays.asList(optionArray)));
		addComponents(decorator.name, decorator.optionGroup);
		addAction();
		addCancelListener(this);
		addSubmitListener(new UpdateRoleListener(decorator.roleBinder, selectedRole.getId(), this, structure));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(decorator) + Objects.hash(UIConstant.UPDATE_ROLE_SALT);
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
		UpdateRoleWindow other = (UpdateRoleWindow) obj;
		return Objects.equals(decorator, other.decorator);
	}
	
}
