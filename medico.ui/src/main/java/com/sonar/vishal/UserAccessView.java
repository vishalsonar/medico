package com.sonar.vishal;

import com.sonar.vishal.ui.structure.UserAccessStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.VerticalLayout;

public class UserAccessView extends GenericView {

	private static final long serialVersionUID = 1679394478007742231L;

	public UserAccessView() {
		super(UIConstant.ACCESS, UIConstant.LOCK_USER, UIConstant.UNLOCK_USER, UIConstant.RESET_PASSWORD);
		buttonOne.setIcon(VaadinIcons.LOCK);
		buttonTwo.setIcon(VaadinIcons.UNLOCK);
		buttonThree.setIcon(VaadinIcons.ROTATE_LEFT);
	}

	@Override
	public void setUI() {
		this.structure = new UserAccessStructure();
		rightLayout = (VerticalLayout) structure.get();
		rightLayout.setSizeFull();
	}
}
