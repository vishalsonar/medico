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
import com.sonar.vishal.medico.common.pojo.Log;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.LogData;
import com.sonar.vishal.medico.common.structure.LogListData;
import com.sonar.vishal.medico.common.structure.PageData;
import com.sonar.vishal.medico.common.structure.SearchData;
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
			criteria.setMaxResults(20);
			List<Log> list = hibernate.<Log>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_LOG_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_LOG_LIST);
			}
			replyData.setLogList(list);
			replyData.setTotalRowCount(getTotalRowCount());
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
			List<Log> list = hibernate.<Log>executeCriteria(session, criteria);
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
	@SuppressWarnings("deprecation")
	public void getPage(int startIndex, int endIndex) {
		LogListData replyData = new LogListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Log.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(Math.abs(startIndex - endIndex));
			List<Log> list = hibernate.<Log>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_LOG_PAGE, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_LOG_PAGE);
			}
			replyData.setLogList(list);
			replyData.setTotalRowCount(getTotalRowCount());
		} else {
			setErrorMessage(Constant.GET_LOG_PAGE, Constant.NULL);
		}
		message.setData(replyData);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public long getTotalRowCount() {
		long count = 0;
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Log.class);
			count = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			session.close();
		}
		return count;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void search(String keyword) {
		LogListData replyData = new LogListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Log.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.like(Constant.MESSAGE, keyword, MatchMode.ANYWHERE));
			List<Log> list = hibernate.<Log>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.SEARCH_LOG, Constant.NULL);
				replyData.setTotalRowCount(0);
			} else {
				setSucessMessage(Constant.SEARCH_LOG);
				replyData.setTotalRowCount(list.size());
			}
			replyData.setLogList(list);
		} else {
			setErrorMessage(Constant.SEARCH_LOG, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_LOG_LIST)) {
			getAll();
		}
		if (functionName.equals(Constant.GET_LOG_PAGE)) {
			PageData message = (PageData) data;
			getPage(message.getStartIndex(), message.getEndIndex());
		}
		if (functionName.equals(Constant.SEARCH_LOG)) {
			SearchData message = (SearchData) data;
			search(message.getKeyword());
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

	@Override
	public Data fromJson(JsonObject dataObject) {
		return gson.fromJson(dataObject, LogData.class);
	}

}
