package com.sonar.vishal.logui.component;

import java.time.LocalDateTime;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;

public class Component {

	public Label getLabel(String labelName) {
		Label label = new Label(labelName);
		label.setResponsive(true);
		label.setSizeFull();
		label.addStyleName(ValoTheme.LABEL_BOLD);
		label.addStyleName(ValoTheme.LABEL_H3);
		return label;
	}

	public Button getFriendlyButton(String label) {
		Button button = new Button(label);
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		button.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button.setWidth(95, Unit.PERCENTAGE);
		return button;
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

	public Notification getServerFailureNotification(String caption) {
		Notification notification = new Notification(caption, "", Notification.Type.ERROR_MESSAGE);
		notification.setPosition(Position.TOP_RIGHT);
		notification.setStyleName(ValoTheme.NOTIFICATION_BAR);
		notification.setDelayMsec(Notification.DELAY_FOREVER);
		notification.setIcon(VaadinIcons.CLOSE_CIRCLE);
		return notification;
	}

	public ComboBox<String> getDropDown(String label, String placeHolder, String[] items) {
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setItems(items);
		comboBox.setCaption(label);
		comboBox.setResponsive(true);
		comboBox.setPlaceholder(placeHolder);
		comboBox.setStyleName(ValoTheme.COMBOBOX_SMALL);
		comboBox.setWidth(95, Unit.PERCENTAGE);
		return comboBox;
	}

	public DateTimeField getDataTimeField(String label) {
		DateTimeField dateTimeField = new DateTimeField();
		dateTimeField.setValue(LocalDateTime.now());
		dateTimeField.setCaption(label);
		dateTimeField.setTextFieldEnabled(false);
		dateTimeField.setStyleName(ValoTheme.DATEFIELD_SMALL);
		dateTimeField.setWidth(95, Unit.PERCENTAGE);
		return dateTimeField;
	}

}