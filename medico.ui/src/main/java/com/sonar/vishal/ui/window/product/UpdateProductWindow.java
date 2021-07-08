package com.sonar.vishal.ui.window.product;

import java.util.Objects;

import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.listener.product.UpdateProductListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;

public class UpdateProductWindow extends MedicoWindow {

	private static final long serialVersionUID = -8168803717546976563L;
	private transient Product selectedProduct;
	private transient ProductWindowDecorator decorator;

	public UpdateProductWindow(CRUDStructure structure, Product selectedProduct) {
		super(UIConstant.UPDATE_PRODUCT, structure);
		this.selectedProduct = selectedProduct;
		decorator = new ProductWindowDecorator();
	}

	@Override
	public void setWindow() {
		decorator.description.setValue(selectedProduct.getDescription());
		decorator.pack.setValue(selectedProduct.getPack());
		decorator.hsnCode.setValue(selectedProduct.getHsnCode());
		decorator.lsq.setValue(selectedProduct.getLsq());
		decorator.quantity.setValue(selectedProduct.getQuantity());
		decorator.batchNumber.setValue(selectedProduct.getBatchNumber());
		decorator.expiryDate.setValue(selectedProduct.getExpiryDate());
		decorator.mrp.setValue(selectedProduct.getMrp());
		decorator.rate.setValue(selectedProduct.getRate());
		decorator.gst.setValue(selectedProduct.getGst());
		decorator.amount.setValue(selectedProduct.getAmount());
		addComponents(decorator.description, decorator.pack, decorator.hsnCode, decorator.lsq, decorator.quantity,
				decorator.batchNumber, decorator.expiryDate, decorator.mrp, decorator.rate, decorator.gst,
				decorator.amount);
		addAction();
		addCancelListener(this);
		addSubmitListener(new UpdateProductListener(decorator.productBinder, selectedProduct.getId(), this, structure));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(decorator, selectedProduct);
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
		UpdateProductWindow other = (UpdateProductWindow) obj;
		return Objects.equals(decorator, other.decorator) && Objects.equals(selectedProduct, other.selectedProduct);
	}

}
