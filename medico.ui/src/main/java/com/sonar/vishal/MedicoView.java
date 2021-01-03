package com.sonar.vishal;

import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.listener.AddButtonListener;
import com.sonar.vishal.ui.listener.DeleteButtonListener;
import com.sonar.vishal.ui.listener.UpdateButtonListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MedicoView extends HorizontalSplitPanel implements View {

	private static final long serialVersionUID = -4882359431088310472L;
	private Button addButton;
	private Button updateButton;
	private Button deleteButton;
	protected final VerticalLayout leftLayout;
	protected final Label logo;
	protected VerticalLayout rightLayout;
	protected Component component = Component.getInstance();
	protected boolean isDirty = false;
	protected CRUDStructure structure;

	public MedicoView(String logoString) {
		leftLayout = new VerticalLayout();
		rightLayout = new VerticalLayout();
		logo = Component.getInstance().getLogo(logoString);
		leftLayout.addComponent(logo);
		leftLayout.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
	}

	public MedicoView(String logoString, String add, String update, String delete) {
		this(logoString);
		addButton = component.getMenuButton(add, VaadinIcons.FILE_ADD);
		updateButton = component.getMenuButton(update, VaadinIcons.FILE_REFRESH);
		deleteButton = component.getMenuButton(delete, VaadinIcons.FILE_REMOVE);
		addButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		deleteButton.addStyleName(ValoTheme.BUTTON_DANGER);
		Button gotoOption = component.gotoOptionButton();
		leftLayout.addComponents(gotoOption, addButton, updateButton, deleteButton);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (!isDirty) {
			setUI();
			displayUI();
			if (addButton != null && updateButton != null && deleteButton != null) {
				addListener();
			}
		}
	}

	public void setUI() {

	}

	public void displayUI() {
		this.addComponents(leftLayout, rightLayout);
		this.setSplitPosition(16, Unit.PERCENTAGE);
		this.setLocked(true);
		UI.getCurrent().setContent(this);
		isDirty = true;
	}

	public void addListener() {
		addButton.addClickListener(new AddButtonListener(structure));
		updateButton.addClickListener(new UpdateButtonListener(structure));
		deleteButton.addClickListener(new DeleteButtonListener(structure));
	}

}
