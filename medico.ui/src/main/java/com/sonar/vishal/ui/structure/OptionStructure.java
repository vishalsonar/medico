package com.sonar.vishal.ui.structure;

import com.sonar.vishal.ui.definition.Structure;
import com.sonar.vishal.ui.enumeration.Access;
import com.sonar.vishal.ui.listener.LogoutListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.util.UIUtil;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

public class OptionStructure implements Structure {

	private Panel panel;
	private GridLayout grid;

	public OptionStructure() {
		panel = new Panel();
		grid = new GridLayout(4, 2);
		grid.setSizeFull();
		grid.setMargin(true);
		grid.setSpacing(true);
		panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
		panel.setContent(grid);
	}

	@Override
	public Object get() {
		Button logoutButton = COMPONENT.getOptionButton(UIConstant.LOGOUT, UIConstant.EMPTY, VaadinIcons.SIGN_OUT);
		logoutButton.addClickListener(new LogoutListener());
		String[] accessArray = UIUtil.getSessionUser().getRole().getModule().split(UIConstant.COMMA);
		for (String accessString : accessArray) {
			if (Access.contains(accessString)) {
				String lowerCase = accessString.toLowerCase();
				String label = lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
				if (!label.equals(UIConstant.LOGIN)) {
					grid.addComponent(COMPONENT.getOptionButton(label, lowerCase, Access.getIcon(accessString)));
				}
			}
		}
		grid.addComponent(COMPONENT.getOptionButton(UIConstant.CHANGE_PASSWORD, UIConstant.S_CHANGE_PASSWORD, VaadinIcons.RETWEET));
		grid.addComponent(logoutButton);
		return panel;
	}

}
