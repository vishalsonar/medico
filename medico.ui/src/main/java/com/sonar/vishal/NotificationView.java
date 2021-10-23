package com.sonar.vishal;

import com.sonar.vishal.ui.structure.NotificationStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class NotificationView extends GenericView {

	private static final long serialVersionUID = -6476710699637939610L;

	public NotificationView() {
		super(UIConstant.NOTIFICATION, UIConstant.DELETE_NOTIFICATION);
		buttonOne.addStyleName(ValoTheme.BUTTON_DANGER);
		buttonOne.setIcon(VaadinIcons.WARNING);
	}

	@Override
	public void setUI() {
		this.structure = new NotificationStructure();
		rightLayout = (VerticalLayout) structure.get();
		rightLayout.setSizeFull();
	}
}
