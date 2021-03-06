package com.sonar.vishal.ui.structure;

import com.sonar.vishal.ui.definition.Structure;
import com.sonar.vishal.ui.listener.LogoutListener;
import com.sonar.vishal.ui.util.UIConstant;
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
		grid.addComponent(COMPONENT.getOptionButton(UIConstant.BILL, UIConstant.S_BILL, VaadinIcons.ALIGN_JUSTIFY));
		grid.addComponent(COMPONENT.getOptionButton(UIConstant.PRODUCT, UIConstant.S_PRODUCT, VaadinIcons.CLIPBOARD_CROSS));
		grid.addComponent(COMPONENT.getOptionButton(UIConstant.PATIENT, UIConstant.S_PATIENT, VaadinIcons.USER_HEART));
		grid.addComponent(COMPONENT.getOptionButton(UIConstant.STORE, UIConstant.S_STORE, VaadinIcons.SHOP));
		grid.addComponent(COMPONENT.getOptionButton(UIConstant.USER, UIConstant.S_USER, VaadinIcons.USER));
		grid.addComponent(COMPONENT.getOptionButton(UIConstant.ROLE, UIConstant.S_ROLE, VaadinIcons.TASKS));
		grid.addComponent(COMPONENT.getOptionButton(UIConstant.CHANGE_PASSWORD, UIConstant.S_CHANGE_PASSWORD, VaadinIcons.RETWEET));
		grid.addComponent(logoutButton);
		return panel;
	}

}
