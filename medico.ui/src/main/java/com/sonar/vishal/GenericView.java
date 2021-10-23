package com.sonar.vishal;

import java.util.Objects;

import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.definition.GenericStructure;
import com.sonar.vishal.ui.listener.GenericButtonListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class GenericView extends HorizontalSplitPanel implements View {

	private static final long serialVersionUID = -4882359431088310472L;
	private Button buttonOne;
	private Button buttonTwo;
	private Button buttonThree;
	protected final VerticalLayout leftLayout;
	protected final Label logo;
	protected VerticalLayout rightLayout;
	protected boolean isDirty = false;
	protected transient GenericStructure structure;
	protected transient Component component = Component.getInstance();

	public GenericView(String logoString) {
		leftLayout = new VerticalLayout();
		rightLayout = new VerticalLayout();
		logo = Component.getInstance().getLogo(logoString);
		leftLayout.addComponent(logo);
		leftLayout.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
	}

	public GenericView(String logoString, String buttonOneName) {
		this(logoString);
		buttonOne = component.getMenuButton(buttonOneName, VaadinIcons.CHECK);
		Button gotoOption = component.gotoOptionButton();
		leftLayout.addComponents(gotoOption, buttonOne);
	}

	public GenericView(String logoString, String buttonOneName, String buttonTwoName) {
		this(logoString);
		buttonOne = component.getMenuButton(buttonOneName, VaadinIcons.CHECK);
		buttonTwo = component.getMenuButton(buttonTwoName, VaadinIcons.CHECK);
		Button gotoOption = component.gotoOptionButton();
		leftLayout.addComponents(gotoOption, buttonOne, buttonTwo);
	}

	public GenericView(String logoString, String buttonOneName, String buttonTwoName, String buttonThreeName) {
		this(logoString);
		buttonOne = component.getMenuButton(buttonOneName, VaadinIcons.CHECK);
		buttonTwo = component.getMenuButton(buttonTwoName, VaadinIcons.CHECK);
		buttonThree = component.getMenuButton(buttonThreeName, VaadinIcons.CHECK);
		Button gotoOption = component.gotoOptionButton();
		leftLayout.addComponents(gotoOption, buttonOne, buttonTwo, buttonThree);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (!isDirty) {
			boolean hasComponent = getComponentCount() > 0;
			if (!hasComponent) {
				setUI();
				displayUI();
				addListener();
			}
		}
	}

	private void addListener() {
		if (buttonOne != null) {
			buttonOne.addClickListener(new GenericButtonListener(structure, 1));
		}
		if (buttonTwo != null) {
			buttonTwo.addClickListener(new GenericButtonListener(structure, 2));
		}
		if (buttonThree != null) {
			buttonThree.addClickListener(new GenericButtonListener(structure, 3));
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
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(buttonOne, buttonThree, buttonTwo, isDirty, leftLayout, logo, rightLayout);
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
		GenericView other = (GenericView) obj;
		return Objects.equals(buttonOne, other.buttonOne) && Objects.equals(buttonThree, other.buttonThree)
				&& Objects.equals(buttonTwo, other.buttonTwo) && isDirty == other.isDirty
				&& Objects.equals(leftLayout, other.leftLayout) && Objects.equals(logo, other.logo)
				&& Objects.equals(rightLayout, other.rightLayout);
	}

}
