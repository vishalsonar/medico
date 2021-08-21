package com.sonar.vishal;

import com.sonar.vishal.ui.structure.BillStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.ui.VerticalLayout;

public class BillView extends MedicoView {

	private static final long serialVersionUID = -2071971327618792221L;

	public BillView() {
		super(UIConstant.BILL, UIConstant.VIEW_BILL);
	}

	@Override
	public void setUI() {
		BillStructure billStructure = new BillStructure();
		this.structure = billStructure;
		this.generateStructure = billStructure;
		rightLayout = (VerticalLayout) structure.get();
		rightLayout.setSizeFull();
	}
}
