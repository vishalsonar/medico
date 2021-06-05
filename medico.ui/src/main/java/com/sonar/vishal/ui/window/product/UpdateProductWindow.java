package com.sonar.vishal.ui.window.product;

import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.listener.product.AddProductListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

public class UpdateProductWindow extends MedicoWindow {

	private static final long serialVersionUID = -8168803717546976563L;
	private Binder<Product> productBinder = new Binder<>();
	private Product selectedProduct;
	private transient CRUDStructure structure;

	public UpdateProductWindow(CRUDStructure structure, Product selectedProduct) {
		super("Update Role", structure);
		this.selectedProduct = selectedProduct;
		this.structure = structure;
	}

	@Override
	public void setWindow() {
		TextField description = COMPONENT.getTextField("Description", "Description", "300");
		TextField pack = COMPONENT.getTextField("Pack", "Pack", "300");
		TextField hsnCode = COMPONENT.getTextField("HSN Code", "HSN Code", "300");
		TextField lsq = COMPONENT.getTextField("LSQ", "LSQ", "300");
		TextField quantity = COMPONENT.getTextField("Quantity", "Quantity", "300");
		TextField batchNumber = COMPONENT.getTextField("Batch Number", "Batch Number", "300");
		TextField expiryDate = COMPONENT.getTextField("Expiry Date", "Expirt Date", "300");
		TextField mrp = COMPONENT.getTextField("MRP", "MRP", "300");
		TextField rate = COMPONENT.getTextField("Rate", "Rate", "300");
		TextField gst = COMPONENT.getTextField("GST", "GST", "300");
		TextField amount = COMPONENT.getTextField("Amount", "Amount", "300");
		productBinder.bind(description, Product::getDescription, Product::setDescription);
		productBinder.bind(pack, Product::getPack, Product::setPack);
		productBinder.bind(hsnCode, Product::getHsnCode, Product::setHsnCode);
		productBinder.bind(lsq, Product::getLsq, Product::setLsq);
		productBinder.bind(quantity, Product::getQuantity, Product::setQuantity);
		productBinder.bind(batchNumber, Product::getBatchNumber, Product::setBatchNumber);
		productBinder.bind(expiryDate, Product::getExpiryDate, Product::setExpiryDate);
		productBinder.bind(mrp, Product::getMrp, Product::setMrp);
		productBinder.bind(rate, Product::getRate, Product::setRate);
		productBinder.bind(gst, Product::getGst, Product::setGst);
		productBinder.bind(amount, Product::getAmount, Product::setAmount);
		description.setValue(selectedProduct.getDescription());
		pack.setValue(selectedProduct.getPack());
		hsnCode.setValue(selectedProduct.getHsnCode());
		lsq.setValue(selectedProduct.getLsq());
		quantity.setValue(selectedProduct.getQuantity());
		batchNumber.setValue(selectedProduct.getBatchNumber());
		expiryDate.setValue(selectedProduct.getExpiryDate());
		mrp.setValue(selectedProduct.getMrp());
		rate.setValue(selectedProduct.getRate());
		gst.setValue(selectedProduct.getGst());
		amount.setValue(selectedProduct.getAmount());
		addComponents(description, pack, hsnCode, lsq, quantity, batchNumber, expiryDate, mrp, rate, gst, amount);
		addAction();
		addCancelListener(this);
		addSubmitListener(new AddProductListener(productBinder, this, structure));
	}

}
