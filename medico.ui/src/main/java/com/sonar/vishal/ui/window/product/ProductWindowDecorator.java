package com.sonar.vishal.ui.window.product;

import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

public class ProductWindowDecorator {

	TextField description;
	TextField pack;
	TextField hsnCode;
	TextField lsq;
	TextField quantity;
	TextField batchNumber;
	TextField expiryDate;
	TextField mrp;
	TextField rate;
	TextField gst;
	TextField amount;
	Binder<Product> productBinder = new Binder<>();
	
	public ProductWindowDecorator() {
		Component component = Component.getInstance();
		description = component.getTextField(UIConstant.DESCRIPTION, UIConstant.DESCRIPTION, UIConstant.FIELD_LENGTH_300);
		pack = component.getTextField(UIConstant.PACK, UIConstant.PACK, UIConstant.FIELD_LENGTH_300);
		hsnCode = component.getTextField(UIConstant.HSN_CODE, UIConstant.HSN_CODE, UIConstant.FIELD_LENGTH_300);
		lsq = component.getTextField(UIConstant.LSQ, UIConstant.LSQ, UIConstant.FIELD_LENGTH_300);
		quantity = component.getTextField(UIConstant.QUANTITY, UIConstant.QUANTITY, UIConstant.FIELD_LENGTH_300);
		batchNumber = component.getTextField(UIConstant.BATCH_NUMBER, UIConstant.BATCH_NUMBER, UIConstant.FIELD_LENGTH_300);
		expiryDate = component.getTextField(UIConstant.EXPIRY_DATE, UIConstant.EXPIRY_DATE, UIConstant.FIELD_LENGTH_300);
		mrp = component.getTextField(UIConstant.MRP, UIConstant.MRP, UIConstant.FIELD_LENGTH_300);
		rate = component.getTextField(UIConstant.RATE, UIConstant.RATE, UIConstant.FIELD_LENGTH_300);
		gst = component.getTextField(UIConstant.GST, UIConstant.GST, UIConstant.FIELD_LENGTH_300);
		amount = component.getTextField(UIConstant.AMOUNT, UIConstant.AMOUNT, UIConstant.FIELD_LENGTH_300);
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
	}
}
