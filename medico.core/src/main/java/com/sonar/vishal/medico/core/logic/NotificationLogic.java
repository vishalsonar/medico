package com.sonar.vishal.medico.core.logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.Notification;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.IdData;
import com.sonar.vishal.medico.common.structure.NotificationData;
import com.sonar.vishal.medico.common.structure.NotificationListData;
import com.sonar.vishal.medico.common.structure.NotificationPageData;
import com.sonar.vishal.medico.common.structure.NotificationSearchData;
import com.sonar.vishal.medico.core.definition.BusinessLogic;

public class NotificationLogic implements BusinessLogic {

	private Notification data;

	private Criterion getCriterion() {
		if (data.getUser().getRole().getName().equals(Constant.ADMIN)) {
			Criterion thisUserCriterion = Restrictions.eq(Constant.USER_ID, data.getUser().getId());
			Criterion nullCriterion = Restrictions.isNull(Constant.USER_ID);
			return Restrictions.or(thisUserCriterion, nullCriterion);
		}
		return Restrictions.eq(Constant.USER_ID, data.getUser().getId());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void getAll() {
		NotificationListData replyData = new NotificationListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Notification.class);
			criteria.add(getCriterion());
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setMaxResults(20);
			List<Notification> list = hibernate.<Notification>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_NOTIFICATION_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_NOTIFICATION_LIST);
			}
			replyData.setNotificationList(list);
			replyData.setTotalRowCount(getTotalRowCount());
		} else {
			setErrorMessage(Constant.GET_NOTIFICATION_LIST, Constant.NULL);
		}
		message.setData(replyData);
	}

	@SuppressWarnings("deprecation")
	@Override
	public long getTotalRowCount() {
		long count = 0;
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Notification.class);
			criteria.add(getCriterion());
			count = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			session.close();
		}
		return count;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void getPage(int startIndex, int endIndex) {
		NotificationListData replyData = new NotificationListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Notification.class);
			criteria.add(getCriterion());
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(Math.abs(startIndex - endIndex));
			List<Notification> list = hibernate.<Notification>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_NOTIFICATION_PAGE, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_NOTIFICATION_PAGE);
			}
			replyData.setNotificationList(list);
			replyData.setTotalRowCount(getTotalRowCount());
		} else {
			setErrorMessage(Constant.GET_NOTIFICATION_PAGE, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public void getById(String id) {
		NotificationData replyData = new NotificationData();
		Notification notification = (Notification) hibernate.selectObjectById(Notification.class, Integer.valueOf(id));
		if (notification == null) {
			setErrorMessage(Constant.GET_NOTIFICATION, Constant.NULL);
		} else {
			setSucessMessage(Constant.GET_NOTIFICATION);
			replyData.setNotification(notification);
		}
		message.setData(replyData);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void search(String keyword) {
		NotificationListData replyData = new NotificationListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Notification.class);
			criteria.add(getCriterion());
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.like(Constant.MESSAGE, keyword, MatchMode.ANYWHERE));
			List<Notification> list = hibernate.<Notification>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.SEARCH_NOTIFICATION, Constant.NULL);
				replyData.setTotalRowCount(0);
			} else {
				setSucessMessage(Constant.SEARCH_NOTIFICATION);
				replyData.setTotalRowCount(list.size());
			}
			replyData.setNotificationList(list);
		} else {
			setErrorMessage(Constant.SEARCH_NOTIFICATION, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public void saveOrUpdate(String functionName, Object data) {
		Data replyData = new Data();
		Notification notification = (Notification) data;
		boolean result = hibernate.saveOrUpdate(notification);
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
		Notification notification = (Notification) data;
		boolean result = hibernate.delete(Notification.class, notification.getId());
		if (result) {
			setSucessMessage(Constant.DELETE_NOTIFICATION);
		} else {
			setErrorMessage(Constant.DELETE_NOTIFICATION, Constant.EXCEPTION);
		}
		message.setData(replyData);
	}

	@Override
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_NOTIFICATION_LIST)) {
			NotificationData notificationData = (NotificationData) data;
			this.data = notificationData.getNotification();
			getAll();
		}
		if (functionName.equals(Constant.GET_NOTIFICATION_PAGE)) {
			NotificationPageData message = (NotificationPageData) data;
			this.data = message.getNotification();
			getPage(message.getStartIndex(), message.getEndIndex());
		}
		if (functionName.equals(Constant.SEARCH_NOTIFICATION)) {
			NotificationSearchData message = (NotificationSearchData) data;
			this.data = message.getNotification();
			search(message.getKeyword());
		}
		if (functionName.equals(Constant.GET_NOTIFICATION)) {
			IdData message = (IdData) data;
			getById(message.getId());
		}
		if (functionName.equals(Constant.ADD_NOTIFICATION) || functionName.equals(Constant.UPDATE_NOTIFICATION)) {
			NotificationData message = (NotificationData) data;
			saveOrUpdate(functionName, message.getNotification());
		}
		if (functionName.equals(Constant.DELETE_NOTIFICATION)) {
			NotificationData message = (NotificationData) data;
			delete(message.getNotification());
		}
		message.getHeader().setType(Constant.RESPONSE);
		return message;
	}

	@Override
	public Data fromJson(JsonObject dataObject) {
		return gson.fromJson(dataObject, NotificationData.class);
	}

}
