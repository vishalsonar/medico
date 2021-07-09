package com.sonar.vishal.ui.listener.product;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.ValidationException;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;

public class AddProductListener extends CRUDListener {

	private static final long serialVersionUID = -6422529977267864632L;
	private Binder<Product> productBinder;
	private ProductListenerLogic logic;

	public AddProductListener(Binder<Product> productBinder, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.ADD_PRODUCT, window);
		this.productBinder = productBinder;
		this.logic = new ProductListenerLogic();
	}

	@Override
	protected void doAction() {
		try {
			Backend.message.setData(logic.process(productBinder, null));
			doPostRespondHeader(Constant.ADD_PRODUCT_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}
}
