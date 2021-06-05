package com.sonar.vishal.ui.structure;

import com.sonar.vishal.ui.definition.Structure;
import com.sonar.vishal.ui.listener.LogoutListener;
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
		Button logoutButton = COMPONENT.getOptionButton("Logout", "", VaadinIcons.SIGN_OUT);
		logoutButton.addClickListener(new LogoutListener());
		grid.addComponent(COMPONENT.getOptionButton("Bill", "bill", VaadinIcons.ALIGN_JUSTIFY));
		grid.addComponent(COMPONENT.getOptionButton("Product", "product", VaadinIcons.CLIPBOARD_CROSS));
		grid.addComponent(COMPONENT.getOptionButton("Patient", "patient", VaadinIcons.USER_HEART));
		grid.addComponent(COMPONENT.getOptionButton("Store", "store", VaadinIcons.SHOP));
		grid.addComponent(COMPONENT.getOptionButton("User", "user", VaadinIcons.USER));
		grid.addComponent(COMPONENT.getOptionButton("Role", "role", VaadinIcons.TASKS));
		grid.addComponent(COMPONENT.getOptionButton("Change Password", "changePassword", VaadinIcons.RETWEET));
		grid.addComponent(logoutButton);
		return panel;
	}

}
