package com.sonar.vishal.ui.window;

import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.listener.WindowCloseListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class MedicoWindow extends Window {

	private static final long serialVersionUID = -6757832858315701226L;
	private Panel panel;
	private FormLayout form;
	private VerticalLayout layout;
	private HorizontalLayout action;
	private Button submit;
	private Button cancel;
	protected transient CRUDStructure structure;
	protected transient Component COMPONENT = Component.getInstance();

	public MedicoWindow(String label, CRUDStructure structure) {
		this.structure = structure;
		panel = new Panel();
		form = new FormLayout();
		layout = new VerticalLayout();
		action = new HorizontalLayout();
		submit = COMPONENT.getFriendlyButton("Submit", "100");
		cancel = COMPONENT.getDangerButton("Cancel", "100");
		action.addComponents(submit, cancel);
		form.addComponent(COMPONENT.getPageLabel(label));
		panel.setContent(form);
		panel.setWidth("500px");
		panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
		layout.addComponent(panel);
		layout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		this.setSizeFull();
		this.setContent(layout);
	}

	public void setWindow() {
		// Do Nothing.
	}

	public void addSubmitListener(ClickListener listener) {
		submit.addClickListener(listener);
	}

	public void addCancelListener(MedicoWindow window) {
		cancel.addClickListener(new WindowCloseListener(window));
	}

	public void addComponents(com.vaadin.ui.Component... components) {
		form.addComponents(components);
	}

	public void addAction() {
		form.addComponent(action);
	}
}
