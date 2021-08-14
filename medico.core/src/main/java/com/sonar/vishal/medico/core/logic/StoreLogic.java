package com.sonar.vishal.medico.core.logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.IdData;
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
			List<Store> list = (List<Store>) hibernate.<Store>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_STORE_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_STORE_LIST);
			}
			replyData.setStoreList(list);
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
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_STORE_LIST)) {
			getAll();
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
}
