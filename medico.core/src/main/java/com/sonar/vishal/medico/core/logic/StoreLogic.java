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
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.IdData;
import com.sonar.vishal.medico.common.structure.PageData;
import com.sonar.vishal.medico.common.structure.SearchData;
import com.sonar.vishal.medico.common.structure.StoreData;
import com.sonar.vishal.medico.common.structure.StoreListData;
import com.sonar.vishal.medico.core.definition.BusinessLogic;

public class StoreLogic implements BusinessLogic {

	@Override
	@SuppressWarnings({ "deprecation" })
	public void getAll() {
		StoreListData replyData = new StoreListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Store.class);
			criteria.createCriteria(Constant.ADDRESS);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setMaxResults(20);
			List<Store> list = hibernate.<Store>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_STORE_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_STORE_LIST);
			}
			replyData.setStoreList(list);
			replyData.setTotalRowCount(getTotalRowCount());
		} else {
			setErrorMessage(Constant.GET_STORE_LIST, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public void getById(String id) {
		StoreData replyData = new StoreData();
		Store store = (Store) hibernate.selectObjectById(Store.class, Integer.valueOf(id));
		if (store == null) {
			setErrorMessage(Constant.GET_STORE, Constant.NULL);
		} else {
			setSucessMessage(Constant.GET_STORE);
			replyData.setStore(store);
		}
		message.setData(replyData);
	}

	@Override
	public void saveOrUpdate(String functionName, Object data) {
		Data replyData = new Data();
		Store store = (Store) data;
		boolean result = hibernate.saveOrUpdate(store);
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
		Store store = (Store) data;
		boolean result = hibernate.delete(Store.class, store.getId());
		if (result) {
			setSucessMessage(Constant.DELETE_STORE);
		} else {
			setErrorMessage(Constant.DELETE_STORE, Constant.EXCEPTION);
		}
		message.setData(replyData);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void getPage(int startIndex, int endIndex) {
		StoreListData replyData = new StoreListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Store.class);
			criteria.createCriteria(Constant.ADDRESS);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(Math.abs(startIndex - endIndex));
			List<Store> list = hibernate.<Store>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_STORE_PAGE, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_STORE_PAGE);
			}
			replyData.setStoreList(list);
			replyData.setTotalRowCount(getTotalRowCount());
		} else {
			setErrorMessage(Constant.GET_STORE_PAGE, Constant.NULL);
		}
		message.setData(replyData);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public long getTotalRowCount() {
		long count = 0;
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Store.class);
			count = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			session.close();
		}
		return count;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void search(String keyword) {
		StoreListData replyData = new StoreListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Store.class);
			criteria.createCriteria(Constant.ADDRESS);
			criteria.add(Restrictions.like(Constant.NAME, keyword, MatchMode.ANYWHERE));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			List<Store> list = hibernate.<Store>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.SEARCH_STORE, Constant.NULL);
				replyData.setTotalRowCount(0);
			} else {
				setSucessMessage(Constant.SEARCH_STORE);
				replyData.setTotalRowCount(list.size());
			}
			replyData.setStoreList(list);
		} else {
			setErrorMessage(Constant.SEARCH_STORE, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_STORE_LIST)) {
			getAll();
		}
		if (functionName.equals(Constant.GET_STORE_PAGE)) {
			PageData message = (PageData) data;
			getPage(message.getStartIndex(), message.getEndIndex());
		}
		if (functionName.equals(Constant.SEARCH_STORE)) {
			SearchData message = (SearchData) data;
			search(message.getKeyword());
		}
		if (functionName.equals(Constant.GET_STORE)) {
			IdData message = (IdData) data;
			getById(message.getId());
		}
		if (functionName.equals(Constant.ADD_STORE) || functionName.equals(Constant.UPDATE_STORE)) {
			StoreData message = (StoreData) data;
			saveOrUpdate(functionName, message.getStore());
		}
		if (functionName.equals(Constant.DELETE_STORE)) {
			StoreData message = (StoreData) data;
			delete(message.getStore());
		}
		message.getHeader().setType(Constant.RESPONSE);
		return message;
	}

	@Override
	public Data fromJson(JsonObject dataObject) {
		return gson.fromJson(dataObject, StoreData.class);
	}
}
