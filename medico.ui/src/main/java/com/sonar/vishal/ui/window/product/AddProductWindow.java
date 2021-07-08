package com.sonar.vishal.ui.window.product;

import java.util.Objects;

import com.sonar.vishal.ui.listener.product.AddProductListener;
import com.sonar.vishal.ui.structure.ProductStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;

public class AddProductWindow extends MedicoWindow {

	private static final long serialVersionUID = 8352973759845085192L;
	private transient ProductWindowDecorator decorator;

	public AddProductWindow(ProductStructure structure) {
		super(UIConstant.ADD_PRODUCT, structure);
		decorator = new ProductWindowDecorator();
	}
	
	@Override
	public void setWindow() {
		addComponents(decorator.description, decorator.pack, decorator.hsnCode, decorator.lsq, decorator.quantity,
				decorator.batchNumber, decorator.expiryDate, decorator.mrp, decorator.rate, decorator.gst,
				decorator.amount);
		addAction();
		addCancelListener(this);
		addSubmitListener(new AddProductListener(decorator.productBinder, this, structure));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(decorator) + Objects.hash(UIConstant.PRODUCT_SALT);
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
		AddProductWindow other = (AddProductWindow) obj;
		return Objects.equals(decorator, other.decorator);
	}

}
