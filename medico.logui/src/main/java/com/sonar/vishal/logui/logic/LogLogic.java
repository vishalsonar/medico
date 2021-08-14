package com.sonar.vishal.logui.logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sonar.vishal.logui.component.LogUIConstant;
import com.sonar.vishal.medico.common.Hibernate;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Log;
import com.sonar.vishal.medico.common.structure.LogData;

public class LogLogic {

	private List<Log> list;
	private Hibernate hibernate;

	public LogLogic() {
		hibernate = Hibernate.getInstance();
	}

	@SuppressWarnings({ "deprecation" })
	public List<Log> getAll() {
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Log.class);
			criteria.addOrder(Order.desc(LogUIConstant.ID_SMALL));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			list = hibernate.<Log>executeCriteria(session, criteria);
		}
		return list;
	}

	@SuppressWarnings({ "deprecation" })
	public List<Log> getFilteredLog(LogData data) {
		String component = data.getLog().getComponent();
		String serverity = data.getLog().getSeverity();
		String startDate = data.getLog().getDateTime();
		String endDate = data.getEndDate();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Log.class);
			if (!(component == null || component.trim().equals(""))) {
				criteria.add(Restrictions.eq(Constant.COMPONENT, component));
			}
			if (!(serverity == null || serverity.trim().equals(""))) {
				criteria.add(Restrictions.eq(Constant.SEVERITY, serverity));
			}
			if (!(startDate == null || startDate.trim().equals("") || endDate == null || endDate.trim().equals(""))) {
				criteria.add(Restrictions.between(Constant.DATE_TIME, startDate, endDate));
			}
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			list = hibernate.<Log>executeCriteria(session, criteria);
		}
		return list;
	}

}
