package com.sonar.vishal.ui.listener.product;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class UpdateProductListener extends CRUDListener {

	private static final long serialVersionUID = 1745543091156516137L;
	private Binder<Product> productBinder;
	private transient ProductListenerLogic logic;

	public UpdateProductListener(Binder<Product> productBinder, int id, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.UPDATE_PRODUCT, window);
		this.productBinder = productBinder;
		this.id = id;
		this.logic = new ProductListenerLogic();
	}

	@Override
	protected void doAction() {
		try {
			Backend.message.setData(logic.process(productBinder, id));
			doPostRespondHeader(Constant.UPDATE_PRODUCT_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (MedicoValidationException e) {
			notifyError(e.getMessage());
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}
}
