package com.sonar.vishal.medico.core.logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.IdData;
import com.sonar.vishal.medico.common.structure.PageData;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.medico.common.structure.UserListData;
import com.sonar.vishal.medico.common.util.Hashing;
import com.sonar.vishal.medico.core.definition.BusinessLogic;

public class UserLogic implements BusinessLogic {

	@Override
	@SuppressWarnings({ "deprecation" })
	public void getAll() {
		UserListData replyData = new UserListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(User.class);
			criteria.createCriteria(Constant.ROLE);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setMaxResults(20);
			List<User> list = hibernate.<User>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_USER_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_USER_LIST);
			}
			replyData.setUserList(list);
			replyData.setTotalRowCount(getTotalRowCount());
		} else {
			setErrorMessage(Constant.GET_USER_LIST, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public void getById(String id) {
		UserData replyData = new UserData();
		User user = (User) hibernate.selectObjectById(User.class, Integer.valueOf(id));
		if (user == null) {
			setErrorMessage(Constant.GET_USER, Constant.NULL);
		} else {
			setSucessMessage(Constant.GET_USER);
			replyData.setUser(user);
		}
		message.setData(replyData);
	}

	@Override
	public void saveOrUpdate(String functionName, Object data) {
		Data replyData = new Data();
		User user = (User) data;
		user.setPassword(Hashing.getHashValue(user.getPassword()));
		boolean result = hibernate.saveOrUpdate(user);
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
		User user = (User) data;
		boolean result = hibernate.delete(User.class, user.getId());
		if (result) {
			setSucessMessage(Constant.DELETE_USER);
		} else {
			setErrorMessage(Constant.DELETE_USER, Constant.EXCEPTION);
		}
		message.setData(replyData);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public long getTotalRowCount() {
		long count = 0;
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(User.class);
			count = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			session.close();
		}
		return count;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void getPage(int startIndex, int endIndex) {
		UserListData replyData = new UserListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(User.class);
			criteria.createCriteria(Constant.ROLE);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(Math.abs(startIndex - endIndex));
			List<User> list = hibernate.<User>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_USER_PAGE, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_USER_PAGE);
			}
			replyData.setUserList(list);
			replyData.setTotalRowCount(getTotalRowCount());
		} else {
			setErrorMessage(Constant.GET_USER_PAGE, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_USER_LIST)) {
			getAll();
		}
		if (functionName.equals(Constant.GET_USER_PAGE)) {
			PageData message = (PageData) data;
			getPage(message.getStartIndex(), message.getEndIndex());
		}
		if (functionName.equals(Constant.GET_USER)) {
			IdData message = (IdData) data;
			getById(message.getId());
		}
		if (functionName.equals(Constant.ADD_USER) || functionName.equals(Constant.UPDATE_USER)) {
			UserData message = (UserData) data;
			saveOrUpdate(functionName, message.getUser());
		}
		if (functionName.equals(Constant.DELETE_USER)) {
			UserData message = (UserData) data;
			delete(message.getUser());
		}
		message.getHeader().setType(Constant.RESPONSE);
		return message;
	}

}
