package com.sonar.vishal.medico.core.logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.IdData;
import com.sonar.vishal.medico.common.structure.ProductData;
import com.sonar.vishal.medico.common.structure.ProductListData;
import com.sonar.vishal.medico.core.definition.BusinessLogic;

public class ProductLogic implements BusinessLogic {

	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void getAll() {
		ProductListData replyData = new ProductListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			List<Product> list = (List<Product>) hibernate.executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_PRODUCT_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_PRODUCT_LIST);
			}
			replyData.setProductList(list);
		} else {
			setErrorMessage(Constant.GET_PRODUCT_LIST, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public void getById(String id) {
		ProductData replyData = new ProductData();
		Product product = (Product) hibernate.selectObjectById(Product.class, Integer.valueOf(id));
		if (product == null) {
			setErrorMessage(Constant.GET_PRODUCT, Constant.NULL);
		} else {
			setSucessMessage(Constant.GET_PRODUCT);
			replyData.setProduct(product);
		}
		message.setData(replyData);
	}

	@Override
	public void saveOrUpdate(String functionName, Object data) {
		Data replyData = new Data();
		Product product = (Product) data;
		boolean result = hibernate.saveOrUpdate(product);
		if (result) {
			setSucessMessage(functionName);
		} else {
			setErrorMessage(functionName, Constant.EXCEPTION);
		}
		message.setData(replyData);
	}

	@Override
	public void delete(Object data) {
		Data replyData = new Data();
		Product product = (Product) data;
		boolean result = hibernate.delete(Product.class, product.getId());
		if (result) {
			setSucessMessage(Constant.DELETE_PRODUCT);
		} else {
			setErrorMessage(Constant.DELETE_PRODUCT, Constant.EXCEPTION);
		}
		message.setData(replyData);
	}

	@Override
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_PRODUCT_LIST)) {
			getAll();
		}
		if (functionName.equals(Constant.GET_PRODUCT)) {
			IdData message = (IdData) data;
			getById(message.getId());
		}
		if (functionName.equals(Constant.ADD_PRODUCT) || functionName.equals(Constant.UPDATE_PRODUCT)) {
			ProductData message = (ProductData) data;
			saveOrUpdate(functionName, message.getProduct());
		}
		if (functionName.equals(Constant.DELETE_PRODUCT)) {
			ProductData message = (ProductData) data;
			delete(message.getProduct());
		}
		message.getHeader().setType(Constant.RESPONSE);
		return message;
	}
}
