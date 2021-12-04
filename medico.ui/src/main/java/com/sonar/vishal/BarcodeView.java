package com.sonar.vishal;

import com.sonar.vishal.ui.structure.barcode.ProductBarcodeStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.ui.VerticalLayout;

public class BarcodeView extends MedicoView {

	private static final long serialVersionUID = -4648781378839035263L;

	public BarcodeView() {
		super(UIConstant.BARCODE, UIConstant.GENERATE_BARCODE);
	}

	@Override
	public void setUI() {
		this.generateStructure = new ProductBarcodeStructure();
		rightLayout = (VerticalLayout) generateStructure.get();
		rightLayout.setSizeFull();
	}
}
