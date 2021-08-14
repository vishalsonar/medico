package com.sonar.vishal.medico.core.logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.IdData;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.medico.common.structure.RoleListData;
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
			List<Role> list = hibernate.<Role>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_ROLE_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_ROLE_LIST);
			}
			replyData.setRoleList(list);
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
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_ROLE_LIST)) {
			getAll();
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
