package com.sonar.vishal;

import com.sonar.vishal.ui.structure.ProductStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.ui.VerticalLayout;

public class ProductView extends MedicoView {

	private static final long serialVersionUID = -4232391220811966162L;

	public ProductView() {
		super(UIConstant.PRODUCT, UIConstant.ADD_PRODUCT, UIConstant.UPDATE_PRODUCT, UIConstant.DELETE_PRODUCT);
	}

	@Override
	public void setUI() {
		this.structure = new ProductStructure();
		rightLayout = (VerticalLayout) structure.get();
		rightLayout.setSizeFull();
	}
}
