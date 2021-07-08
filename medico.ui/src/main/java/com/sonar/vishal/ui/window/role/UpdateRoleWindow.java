package com.sonar.vishal.ui.window.role;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.listener.role.RoleOptionValueListener;
import com.sonar.vishal.ui.listener.role.UpdateRoleListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.TextField;

public class UpdateRoleWindow extends MedicoWindow {

	private static final long serialVersionUID = -3029061649785789120L;
	private Binder<Role> roleBinder = new Binder<>();
	private transient Role selectedRole;

	public UpdateRoleWindow(CRUDStructure structure, Role selectedRole) {
		super("Update Role", structure);
		this.selectedRole = selectedRole;
	}

	@Override
	public void setWindow() {
		TextField name = COMPONENT.getTextField("Name", "Role Name", "300");
		String[] options = { "Login", "Option", "Bill", "Product", "Patient", "Store", "User", "Role" };
		CheckBoxGroup<String> optionGroup = COMPONENT.getCheckBoxGroup("Options", options);
		optionGroup.addValueChangeListener(new RoleOptionValueListener());
		roleBinder.bind(name, Role::getName, Role::setName);
		name.setValue(selectedRole.getName());
		String[] optionArray = selectedRole.getModule().split(",");
		optionGroup.setValue(new HashSet<>(Arrays.asList(optionArray)));
		addComponents(name, optionGroup);
		addAction();
		addCancelListener(this);
		addSubmitListener(new UpdateRoleListener(roleBinder, selectedRole.getId(), this, structure));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(roleBinder);
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
		return Objects.equals(roleBinder, other.roleBinder);
	}
	
}
