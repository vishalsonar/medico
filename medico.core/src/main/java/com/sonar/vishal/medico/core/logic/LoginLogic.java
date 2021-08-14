package com.sonar.vishal.medico.core.logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.structure.LoginData;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.medico.common.util.Hashing;
import com.sonar.vishal.medico.core.adapter.BusinessLogicAdapter;

public class LoginLogic extends BusinessLogicAdapter {

	@SuppressWarnings("deprecation")
	public void isValidUser(LoginData data) {
		UserData userData = new UserData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq(Constant.USERNAME, data.getUserName()));
			criteria.add(Restrictions.eq(Constant.PASSWORD, Hashing.getHashValue(data.getPassword())));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			List<?> list = hibernate.executeCriteria(session, criteria);
			if (list != null && list.size() == 1) {
				setSucessMessage(Constant.LOGIN);
				User user = (User) list.get(0);
				userData.setUser(user);
			} else {
				setErrorMessage(Constant.LOGIN, Constant.EXCEPTION);
			}
		} else {
			setErrorMessage(Constant.LOGIN, Constant.NULL);
		}
		message.setData(userData);
	}

	@Override
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.LOGIN)) {
			LoginData message = (LoginData) data;
			isValidUser(message);
		}
		message.getHeader().setType(Constant.RESPONSE);
		return message;
	}
}
