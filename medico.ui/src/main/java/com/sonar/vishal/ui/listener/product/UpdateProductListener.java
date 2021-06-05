package com.sonar.vishal.ui.listener.product;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.structure.ProductData;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.ValidationException;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;

public class UpdateProductListener extends CRUDListener {

	private static final long serialVersionUID = 1745543091156516137L;
	private Binder<Product> productBinder;
	private int id;

	public UpdateProductListener(Binder<Product> productBinder, int id, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.UPDATE_PRODUCT, window);
		this.productBinder = productBinder;
		this.id = id;
	}

	@Override
	protected void doAction() {
		try {
			Product product = new Product();
			ProductData data = new ProductData();
			this.productBinder.writeBean(product);
			product.setId(id);
			validateString(product.getDescription());
			validateString(product.getPack());
			validateString(product.getHsnCode());
			validateString(product.getLsq());
			validateString(product.getQuantity());
			validateString(product.getBatchNumber());
			validateString(product.getExpiryDate());
			validateString(product.getMrp());
			validateString(product.getRate());
			validateString(product.getGst());
			validateString(product.getAmount());
			data.setProduct(product);
			Backend.message.setData(data);
			doPostRespondHeader(Constant.UPDATE_PRODUCT_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}
}
