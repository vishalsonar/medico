package com.sonar.vishal;

import java.util.Objects;

import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.definition.GenerateStructure;
import com.sonar.vishal.ui.listener.AddButtonListener;
import com.sonar.vishal.ui.listener.DeleteButtonListener;
import com.sonar.vishal.ui.listener.GenerateButtonListener;
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
	private Button generateButton;
	protected final VerticalLayout leftLayout;
	protected final Label logo;
	protected VerticalLayout rightLayout;
	protected boolean isDirty = false;
	protected transient CRUDStructure structure;
	protected transient GenerateStructure generateStructure;
	protected transient Component component = Component.getInstance();
	
	public MedicoView(String logoString) {
		leftLayout = new VerticalLayout();
		rightLayout = new VerticalLayout();
		logo = Component.getInstance().getLogo(logoString);
		leftLayout.addComponent(logo);
		leftLayout.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
	}
	
	public MedicoView(String logoString, String generate) {
		this(logoString);
		generateButton = component.getMenuButton(generate, VaadinIcons.BARCODE);
		generateButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		Button gotoOption = component.gotoOptionButton();
		leftLayout.addComponents(gotoOption, generateButton);
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
			boolean hasComponent = getComponentCount() > 0;
			if (!hasComponent) {
				setUI();
				displayUI();
				if (addButton != null && updateButton != null && deleteButton != null) {
					addListener();
				}
				if (generateButton != null) {
					addGenerateListener();
				}
			}
		}
	}

	public void setUI() {
		throw new IllegalStateException("Override Method");
	}

	public void displayUI() {
		this.addComponents(leftLayout, rightLayout);
		this.setSplitPosition(16, Unit.PERCENTAGE);
		this.setLocked(true);
		UI.getCurrent().setContent(this);
		if (this instanceof LoginView || this instanceof OptionView) {
			isDirty = true;
		}
	}

	public void addListener() {
		addButton.addClickListener(new AddButtonListener(structure));
		updateButton.addClickListener(new UpdateButtonListener(structure));
		deleteButton.addClickListener(new DeleteButtonListener(structure));
	}

	private void addGenerateListener() {
		generateButton.addClickListener(new GenerateButtonListener(generateStructure));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(addButton, deleteButton, isDirty, leftLayout, logo, rightLayout, updateButton);
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
		MedicoView other = (MedicoView) obj;
		return Objects.equals(addButton, other.addButton) && Objects.equals(deleteButton, other.deleteButton)
				&& isDirty == other.isDirty && Objects.equals(leftLayout, other.leftLayout)
				&& Objects.equals(logo, other.logo) && Objects.equals(rightLayout, other.rightLayout)
				&& Objects.equals(updateButton, other.updateButton);
	}

}
