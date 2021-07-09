package com.sonar.vishal.ui.component;

import java.util.List;

import com.sonar.vishal.MedicoUI;
import com.sonar.vishal.ui.listener.GoToOptionListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class Component {

	private static Component component;

	private Component() {
		// Invisible Constructor
	}

	public static Component getInstance() {
		if (component == null)
			return new Component();
		return component;
	}

	public TextField getTextField(String label, String placeHolder, String width) {
		TextField field = new TextField(label);
		field.setPlaceholder(placeHolder);
		field.setMaxLength(20);
		field.setResponsive(true);
		field.setHeight(UIConstant._30);
		field.setWidth(width);
		return field;
	}

	public PasswordField getPasswordField(String label, String placeHolder, String width) {
		PasswordField field = new PasswordField(label);
		field.setPlaceholder(placeHolder);
		field.setMaxLength(20);
		field.setResponsive(true);
		field.setHeight(UIConstant._30);
		field.setWidth(width);
		return field;
	}

	public Label getLabel(String labelName) {
		Label label = new Label(labelName);
		label.setResponsive(true);
		return label;
	}

	public Button getButton(String label, String width) {
		Button button = new Button(label);
		button.setWidth(width);
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		return button;
	}

	public Button getFriendlyButton(String label, String width) {
		Button button = getButton(label, width);
		button.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		return button;
	}

	public Button getDangerButton(String label, String width) {
		Button button = getButton(label, width);
		button.addStyleName(ValoTheme.BUTTON_DANGER);
		return button;
	}

	public Button getOptionButton(String label, String navigateTo, Resource icon) {
		Button button = new Button(label);
		button.setSizeFull();
		button.setIcon(icon);
		button.addStyleName(ValoTheme.BUTTON_LARGE);
		button.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
		button.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				MedicoUI.getNavigatorUI().navigateTo(navigateTo);
			}
		});
		return button;
	}

	public Link getLink(String label, String url) {
		Link link = new Link(label, new ExternalResource(url));
		link.addStyleName(ValoTheme.LINK_SMALL);
		return link;
	}

	public Label getLogo(String logoString) {
		Label label = new Label(logoString);
		label.addStyleName(ValoTheme.LABEL_BOLD);
		label.addStyleName(ValoTheme.LABEL_H1);
		label.setResponsive(true);
		return label;
	}

	public Notification getSuccessNotification(String caption, String description) {
		Notification notification = new Notification(caption, description, Notification.Type.HUMANIZED_MESSAGE);
		notification.setPosition(Position.TOP_RIGHT);
		notification.setDelayMsec(1000);
		notification.setStyleName(ValoTheme.NOTIFICATION_SUCCESS);
		return notification;
	}

	public Notification getFailureNotification(String caption, String description) {
		Notification notification = new Notification(caption, description, Notification.Type.ERROR_MESSAGE);
		notification.setPosition(Position.TOP_RIGHT);
		notification.setDelayMsec(1000);
		notification.setStyleName(ValoTheme.NOTIFICATION_ERROR);
		return notification;
	}

	public Label getPageLabel(String label) {
		Label name = new Label(label);
		name.addStyleName(ValoTheme.LABEL_H2);
		return name;
	}

	public Button getMenuButton(String label, Resource icon) {
		Button button = new Button(label);
		button.addStyleName(ValoTheme.BUTTON_PRIMARY);
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		button.setIcon(icon);
		button.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
		button.setSizeFull();
		return button;
	}

	public Button gotoOptionButton() {
		Button button = new Button("Option");
		button.addStyleName(ValoTheme.BUTTON_PRIMARY);
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		button.setIcon(VaadinIcons.ARROW_BACKWARD);
		button.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
		button.setSizeFull();
		button.addClickListener(new GoToOptionListener());
		return button;
	}

	public CheckBoxGroup<String> getCheckBoxGroup(String label, String... items) {
		CheckBoxGroup<String> option = new CheckBoxGroup<>();
		option.setCaption(label);
		option.setItems(items);
		return option;
	}
	
	public RadioButtonGroup<String> getRadioGroupList(String label, List<String> items){
		RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
		radioGroup.setCaption(label);
		radioGroup.setItems(items);
		return radioGroup;
	}
}
