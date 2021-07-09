package com.sonar.vishal;

import com.sonar.vishal.ui.structure.StoreStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.ui.VerticalLayout;

public class StoreView extends MedicoView {

	private static final long serialVersionUID = 1059783135912487706L;

	public StoreView() {
		super(UIConstant.STORE, UIConstant.ADD_STORE, UIConstant.UPDATE_STORE, UIConstant.DELETE_STORE);
	}

	@Override
	public void setUI() {
		this.structure = new StoreStructure();
		rightLayout = (VerticalLayout) structure.get();
		rightLayout.setSizeFull();
	}
}
