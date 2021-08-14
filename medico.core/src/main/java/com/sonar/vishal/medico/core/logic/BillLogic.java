package com.sonar.vishal.medico.core.logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.Bill;
import com.sonar.vishal.medico.common.structure.BillData;
import com.sonar.vishal.medico.common.structure.BillListData;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.IdData;
import com.sonar.vishal.medico.core.definition.BusinessLogic;

public class BillLogic implements BusinessLogic {

	@Override
	@SuppressWarnings({ "deprecation" })
	public void getAll() {
		BillListData replyData = new BillListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Bill.class);
			criteria.createCriteria(Constant.PATIENT);
			criteria.createCriteria(Constant.STORE);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			List<Bill> list = hibernate.<Bill>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_BILL_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_BILL_LIST);
			}
			replyData.setBillList(list);
		} else {
			setErrorMessage(Constant.GET_BILL_LIST, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public void getById(String id) {
		BillData replyData = new BillData();
		Bill bill = (Bill) hibernate.selectObjectById(Bill.class, Integer.valueOf(id));
		if (bill == null) {
			setErrorMessage(Constant.GET_BILL, Constant.NULL);
		} else {
			setSucessMessage(Constant.GET_BILL);
			replyData.setBill(bill);
		}
		message.setData(replyData);
	}

	@Override
	public void saveOrUpdate(String functionName, Object data) {
		Data replyData = new Data();
		Bill bill = (Bill) data;
		boolean result = hibernate.saveOrUpdate(bill);
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
		Bill bill = (Bill) data;
		boolean result = hibernate.delete(Bill.class, bill.getId());
		if (result) {
			setSucessMessage(Constant.DELETE_BILL);
		} else {
			setErrorMessage(Constant.DELETE_BILL, Constant.EXCEPTION);
		}
		message.setData(replyData);
	}

	@Override
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_BILL_LIST)) {
			getAll();
		}
		if (functionName.equals(Constant.GET_BILL)) {
			IdData message = (IdData) data;
			getById(message.getId());
		}
		if (functionName.equals(Constant.ADD_BILL) || functionName.equals(Constant.UPDATE_BILL)) {
			BillData message = (BillData) data;
			saveOrUpdate(functionName, message.getBill());
		}
		if (functionName.equals(Constant.DELETE_BILL)) {
			BillData message = (BillData) data;
			delete(message.getBill());
		}
		message.getHeader().setType(Constant.RESPONSE);
		return message;
	}

}
