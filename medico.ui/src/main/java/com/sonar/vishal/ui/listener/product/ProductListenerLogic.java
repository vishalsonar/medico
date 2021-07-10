package com.sonar.vishal.ui.listener.product;

import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.ProductData;
import com.sonar.vishal.ui.definition.ListenerLogic;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.validator.ProductDataValidator;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class ProductListenerLogic implements ListenerLogic<Binder<Product>> {

	@Override
	public Data process(Binder<Product> binder, Integer id) throws ValidationException, MedicoValidationException {
		Product product = new Product();
		ProductData data = new ProductData();
		binder.writeBean(product);
		if (id != null) {
			product.setId(id);
		}
		data.setProduct(product);
		new ProductDataValidator().doValidation(data);
		return data;
	}

}
