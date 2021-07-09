package com.sonar.vishal.ui.window.user;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.listener.user.UpdateUserListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;

public class UpdateUserWindow extends MedicoWindow {
	
	private static final long serialVersionUID = 7945272477175968378L;
	private transient UserWindowDecorator decorator;
	private transient User selectedUser;

	public UpdateUserWindow(CRUDStructure structure, User selectedUser) {
		super(UIConstant.UPDATE_USER, structure);
		decorator = new UserWindowDecorator();
		this.selectedUser = selectedUser;
	}
	
	@Override
	public void setWindow() {
		decorator.name.setValue(selectedUser.getUserName());
		List<String> oldSelection = Arrays.asList(selectedUser.getRole().getName());
		decorator.optionGroup.setValue(oldSelection.get(0));
		addComponents(decorator.name, decorator.password, decorator.confirmPassword, decorator.optionGroup);
		addAction();
		addCancelListener(this);
		addSubmitListener(new UpdateUserListener(decorator.userBinder, selectedUser.getId(), this, structure));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(decorator) + Objects.hash(UIConstant.UPDATE_USER_SALT);
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
		UpdateUserWindow other = (UpdateUserWindow) obj;
		return Objects.equals(decorator, other.decorator);
	}

}
