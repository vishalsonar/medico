package com.sonar.vishal.medico.core.logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.structure.PatientListData;
import com.sonar.vishal.medico.core.adapter.BusinessLogicAdapter;

public class PatientLogic extends BusinessLogicAdapter {

	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void getAll() {
		PatientListData replyData = new PatientListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Patient.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			List<Patient> list = (List<Patient>) hibernate.executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_PATIENT_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_PATIENT_LIST);
			}
			replyData.setPatientList(list);
		} else {
			setErrorMessage(Constant.GET_PATIENT_LIST, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_PATIENT_LIST)) {
			getAll();
		}
		message.getHeader().setType(Constant.RESPONSE);
		return message;
	}

}
