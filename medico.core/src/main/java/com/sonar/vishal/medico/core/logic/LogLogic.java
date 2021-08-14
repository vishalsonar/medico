package com.sonar.vishal.medico.core.logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.Log;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.LogData;
import com.sonar.vishal.medico.common.structure.LogListData;
import com.sonar.vishal.medico.core.definition.BusinessLogic;

public class LogLogic implements BusinessLogic {

	@SuppressWarnings({ "deprecation" })
	@Override
	public void getAll() {
		LogListData replyData = new LogListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Log.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			List<Log> list = (List<Log>) hibernate.<Log>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_LOG_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_LOG_LIST);
			}
			replyData.setLogList(list);
		} else {
			setErrorMessage(Constant.GET_LOG_LIST, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public void getById(String id) {
		throw new IllegalAccessError("ById Method not Allowed");
	}

	@Override
	public void saveOrUpdate(String functionName, Object data) {
		Data replyData = new Data();
		Log log = (Log) data;
		boolean result = hibernate.saveOrUpdate(log);
		if (result) {
			setSucessMessage(functionName);
		} else {
			setErrorMessage(functionName, Constant.EXCEPTION);
		}
		message.setData(replyData);
	}

	@Override
	public void delete(Object data) {
		throw new IllegalAccessError("Delete Method not Allowed");
	}

	@SuppressWarnings({ "deprecation" })
	public void getFilteredLog(LogData data) {
		LogListData replyData = new LogListData();
		String component = data.getLog().getComponent();
		String serverity = data.getLog().getSeverity();
		String startDate = data.getLog().getDateTime();
		String endDate = data.getEndDate();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Log.class);
			if (checkField(component)) {
				criteria.add(Restrictions.eq(Constant.COMPONENT, component));
			}
			if (checkField(serverity)) {
				criteria.add(Restrictions.eq(Constant.SEVERITY, serverity));
			}
			if (checkField(startDate) && checkField(endDate)) {
				criteria.add(Restrictions.between(Constant.DATE_TIME, startDate, endDate));
			}
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			List<Log> list = (List<Log>) hibernate.<Log>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_LOG, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_LOG);
			}
			replyData.setLogList(list);
		} else {
			setErrorMessage(Constant.GET_LOG, Constant.NULL);
		}
		message.setData(replyData);
	}
	
	private boolean checkField(String field) {
		boolean result = false;
		if (!(field == null || field.trim().equals(""))) {
			result = true;
		}
		return result;
	}

	@Override
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_LOG_LIST)) {
			getAll();
		}
		if (functionName.equals(Constant.GET_LOG)) {
			LogData message = (LogData) data;
			getFilteredLog(message);
		}
		if (functionName.equals(Constant.ADD_LOG)) {
			LogData message = (LogData) data;
			saveOrUpdate(functionName, message.getLog());
		}
		message.getHeader().setType(Constant.RESPONSE);
		return message;
	}

}
