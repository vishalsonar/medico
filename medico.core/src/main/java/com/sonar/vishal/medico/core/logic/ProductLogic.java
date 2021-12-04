package com.sonar.vishal.medico.core.logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.IdData;
import com.sonar.vishal.medico.common.structure.PageData;
import com.sonar.vishal.medico.common.structure.ProductData;
import com.sonar.vishal.medico.common.structure.ProductListData;
import com.sonar.vishal.medico.common.structure.SearchData;
import com.sonar.vishal.medico.core.definition.BusinessLogic;

public class ProductLogic implements BusinessLogic {

	@Override
	@SuppressWarnings({ "deprecation" })
	public void getAll() {
		ProductListData replyData = new ProductListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setMaxResults(20);
			List<Product> list = hibernate.<Product>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_PRODUCT_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_PRODUCT_LIST);
			}
			replyData.setProductList(list);
			replyData.setTotalRowCount(getTotalRowCount());
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
	@SuppressWarnings("deprecation")
	public void getPage(int startIndex, int endIndex) {
		ProductListData replyData = new ProductListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(Math.abs(startIndex - endIndex));
			List<Product> list = hibernate.<Product>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_PRODUCT_PAGE, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_PRODUCT_PAGE);
			}
			replyData.setProductList(list);
			replyData.setTotalRowCount(getTotalRowCount());
		} else {
			setErrorMessage(Constant.GET_PRODUCT_PAGE, Constant.NULL);
		}
		message.setData(replyData);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public long getTotalRowCount() {
		long count = 0;
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Product.class);
			count = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			session.close();
		}
		return count;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void search(String keyword) {
		ProductListData replyData = new ProductListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.like(Constant.DESCRIPTION, keyword, MatchMode.ANYWHERE));
			List<Product> list = hibernate.<Product>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.SEARCH_PRODUCT, Constant.NULL);
				replyData.setTotalRowCount(0);
			} else {
				setSucessMessage(Constant.SEARCH_PRODUCT);
				replyData.setTotalRowCount(list.size());
			}
			replyData.setProductList(list);
		} else {
			setErrorMessage(Constant.SEARCH_PRODUCT, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_PRODUCT_LIST)) {
			getAll();
		}
		if (functionName.equals(Constant.GET_PRODUCT_PAGE)) {
			PageData message = (PageData) data;
			getPage(message.getStartIndex(), message.getEndIndex());
		}
		if (functionName.equals(Constant.SEARCH_PRODUCT)) {
			SearchData message = (SearchData) data;
			search(message.getKeyword());
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

	@Override
	public Data fromJson(JsonObject dataObject) {
		return gson.fromJson(dataObject, ProductData.class);
	}
}
