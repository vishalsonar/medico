package com.sonar.vishal.medico.core.logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.IdData;
import com.sonar.vishal.medico.common.structure.PageData;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.medico.common.structure.RoleListData;
import com.sonar.vishal.medico.common.structure.SearchData;
import com.sonar.vishal.medico.core.definition.BusinessLogic;

public class RoleLogic implements BusinessLogic {

	@Override
	@SuppressWarnings({ "deprecation" })
	public void getAll() {
		RoleListData replyData = new RoleListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Role.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setMaxResults(20);
			List<Role> list = hibernate.<Role>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_ROLE_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_ROLE_LIST);
			}
			replyData.setRoleList(list);
			replyData.setTotalRowCount(getTotalRowCount());
		} else {
			setErrorMessage(Constant.GET_ROLE_LIST, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public void getById(String id) {
		RoleData replyData = new RoleData();
		Role role = (Role) hibernate.selectObjectById(Role.class, Integer.valueOf(id));
		if (role == null) {
			setErrorMessage(Constant.GET_ROLE, Constant.NULL);
		} else {
			setSucessMessage(Constant.GET_ROLE);
			replyData.setRole(role);
		}
		message.setData(replyData);
	}

	@Override
	public void saveOrUpdate(String functionName, Object data) {
		Data replyData = new Data();
		Role role = (Role) data;
		boolean result = hibernate.saveOrUpdate(role);
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
		Role role = (Role) data;
		boolean result = hibernate.delete(Role.class, role.getId());
		if (result) {
			setSucessMessage(Constant.DELETE_ROLE);
		} else {
			setErrorMessage(Constant.DELETE_ROLE, Constant.EXCEPTION);
		}
		message.setData(replyData);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void getPage(int startIndex, int endIndex) {
		RoleListData replyData = new RoleListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Role.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(Math.abs(startIndex - endIndex));
			List<Role> list = hibernate.<Role>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_ROLE_PAGE, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_ROLE_PAGE);
			}
			replyData.setRoleList(list);
			replyData.setTotalRowCount(getTotalRowCount());
		} else {
			setErrorMessage(Constant.GET_ROLE_PAGE, Constant.NULL);
		}
		message.setData(replyData);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public long getTotalRowCount() {
		long count = 0;
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Role.class);
			count = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			session.close();
		}
		return count;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void search(String keyword) {
		RoleListData replyData = new RoleListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Role.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.like(Constant.NAME, keyword, MatchMode.ANYWHERE));
			List<Role> list = hibernate.<Role>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.SEARCH_ROLE, Constant.NULL);
				replyData.setTotalRowCount(0);
			} else {
				setSucessMessage(Constant.SEARCH_ROLE);
				replyData.setTotalRowCount(list.size());
			}
			replyData.setRoleList(list);
		} else {
			setErrorMessage(Constant.SEARCH_ROLE, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_ROLE_LIST)) {
			getAll();
		}
		if (functionName.equals(Constant.GET_ROLE_PAGE)) {
			PageData message = (PageData) data;
			getPage(message.getStartIndex(), message.getEndIndex());
		}
		if (functionName.equals(Constant.SEARCH_ROLE)) {
			SearchData message = (SearchData) data;
			search(message.getKeyword());
		}
		if (functionName.equals(Constant.GET_ROLE)) {
			IdData message = (IdData) data;
			getById(message.getId());
		}
		if (functionName.equals(Constant.ADD_ROLE) || functionName.equals(Constant.UPDATE_ROLE)) {
			RoleData message = (RoleData) data;
			saveOrUpdate(functionName, message.getRole());
		}
		if (functionName.equals(Constant.DELETE_ROLE)) {
			RoleData message = (RoleData) data;
			delete(message.getRole());
		}
		message.getHeader().setType(Constant.RESPONSE);
		return message;
	}
}
