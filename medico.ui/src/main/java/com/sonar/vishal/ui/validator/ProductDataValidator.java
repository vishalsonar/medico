package com.sonar.vishal.ui.validator;

import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.structure.ProductData;
import com.sonar.vishal.ui.definition.Validator;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIConstant;

public class ProductDataValidator implements Validator<ProductData> {

	private static final String INVALID_DESCRIPTION = "Invalid Description";
	private static final String INVALID_PACK = "Invalid Pack";
	private static final String INVALID_HSN_CODE = "Invalid HSN Number";
	private static final String INVALID_LSQ = "Invalid LSQ";
	private static final String INVALID_QUANTITY = "Invalid Quantity";
	private static final String INVALID_BATCH_NUMBER = "Invalid Batch Number";
	private static final String INVALID_EXPIRY_DATE = "Invalid Expiry Date";
	private static final String INVALID_MRP = "Invalid MRP";
	private static final String INVALID_RATE = "Invalid Rate";
	private static final String INVALID_GST = "Invalid Rate";
	private static final String INVALID_AMOUNT = "Invalid Amount";

	@Override
	public void doValidation(ProductData data) throws MedicoValidationException {
		Product product = data.getProduct();
		handleNullEmpty(product);
		isAlphaNumericSpaceHyphen(product.getDescription(), INVALID_DESCRIPTION);
		isAlphaNumericSpace(product.getPack(), INVALID_PACK);
		isNumeric(product.getHsnCode(), INVALID_HSN_CODE);
		isNumeric(product.getQuantity(), INVALID_QUANTITY);
		isAlphaNumeric(product.getLsq(), INVALID_LSQ);
		isAlphaNumericSpaceHyphen(product.getBatchNumber(), INVALID_BATCH_NUMBER);
		isNumeric(product.getExpiryDate(), INVALID_EXPIRY_DATE);
		isDecimalNumeric(product.getMrp(), INVALID_MRP);
		isDecimalNumeric(product.getRate(), INVALID_RATE);
		isDecimalNumeric(product.getGst(), INVALID_GST);
		isDecimalNumeric(product.getAmount(), INVALID_AMOUNT);
	}

	private void handleNullEmpty(Product product) throws MedicoValidationException {
		if (product.getDescription().trim().length() == 0 || product.getPack().trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (product.getHsnCode().trim().length() == 0 || product.getLsq().trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (product.getQuantity().trim().length() == 0 || product.getBatchNumber().trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (product.getExpiryDate().trim().length() == 0 || product.getMrp().trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (product.getRate().trim().length() == 0 || product.getGst().trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (product.getAmount().trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
	}

}
