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
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.IdData;
import com.sonar.vishal.medico.common.structure.PageData;
import com.sonar.vishal.medico.common.structure.PatientData;
import com.sonar.vishal.medico.common.structure.PatientListData;
import com.sonar.vishal.medico.common.structure.SearchData;
import com.sonar.vishal.medico.core.definition.BusinessLogic;

public class PatientLogic implements BusinessLogic {

	@Override
	@SuppressWarnings({ "deprecation" })
	public void getAll() {
		PatientListData replyData = new PatientListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Patient.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setMaxResults(20);
			List<Patient> list = hibernate.<Patient>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_PATIENT_LIST, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_PATIENT_LIST);
			}
			replyData.setPatientList(list);
			replyData.setTotalRowCount(getTotalRowCount());
		} else {
			setErrorMessage(Constant.GET_PATIENT_LIST, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public void getById(String id) {
		PatientData replyData = new PatientData();
		Patient patient = (Patient) hibernate.selectObjectById(Patient.class, Integer.valueOf(id));
		if (patient == null) {
			setErrorMessage(Constant.GET_PATIENT, Constant.NULL);
		} else {
			setSucessMessage(Constant.GET_PATIENT);
			replyData.setPatient(patient);
		}
		message.setData(replyData);
	}

	@Override
	public void saveOrUpdate(String functionName, Object data) {
		Data replyData = new Data();
		Patient patient = (Patient) data;
		boolean result = hibernate.saveOrUpdate(patient);
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
		Patient patient = (Patient) data;
		boolean result = hibernate.delete(Patient.class, patient.getId());
		if (result) {
			setSucessMessage(Constant.DELETE_PATIENT);
		} else {
			setErrorMessage(Constant.DELETE_PATIENT, Constant.EXCEPTION);
		}
		message.setData(replyData);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void getPage(int startIndex, int endIndex) {
		PatientListData replyData = new PatientListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Patient.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(Math.abs(startIndex - endIndex));
			List<Patient> list = hibernate.<Patient>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.GET_PATIENT_PAGE, Constant.NULL);
			} else {
				setSucessMessage(Constant.GET_PATIENT_PAGE);
			}
			replyData.setPatientList(list);
			replyData.setTotalRowCount(getTotalRowCount());
		} else {
			setErrorMessage(Constant.GET_PATIENT_PAGE, Constant.NULL);
		}
		message.setData(replyData);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public long getTotalRowCount() {
		long count = 0;
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Patient.class);
			count = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			session.close();
		}
		return count;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void search(String keyword) {
		PatientListData replyData = new PatientListData();
		Session session = hibernate.getSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(Patient.class);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.like(Constant.PATIENT_NAME, keyword, MatchMode.ANYWHERE));
			List<Patient> list = hibernate.<Patient>executeCriteria(session, criteria);
			if (list == null) {
				setErrorMessage(Constant.SEARCH_PATIENT, Constant.NULL);
				replyData.setTotalRowCount(0);
			} else {
				setSucessMessage(Constant.SEARCH_PATIENT);
				replyData.setTotalRowCount(list.size());
			}
			replyData.setPatientList(list);
		} else {
			setErrorMessage(Constant.SEARCH_PATIENT, Constant.NULL);
		}
		message.setData(replyData);
	}

	@Override
	public Message execute(String functionName, Object data) {
		if (functionName.equals(Constant.GET_PATIENT_LIST)) {
			getAll();
		}
		if (functionName.equals(Constant.GET_PATIENT_PAGE)) {
			PageData message = (PageData) data;
			getPage(message.getStartIndex(), message.getEndIndex());
		}
		if (functionName.equals(Constant.SEARCH_PATIENT)) {
			SearchData message = (SearchData) data;
			search(message.getKeyword());
		}
		if (functionName.equals(Constant.GET_PATIENT)) {
			IdData message = (IdData) data;
			getById(message.getId());
		}
		if (functionName.equals(Constant.ADD_PATIENT) || functionName.equals(Constant.UPDATE_PATIENT)) {
			PatientData message = (PatientData) data;
			saveOrUpdate(functionName, message.getPatient());
		}
		if (functionName.equals(Constant.DELETE_PATIENT)) {
			PatientData message = (PatientData) data;
			delete(message.getPatient());
		}
		message.getHeader().setType(Constant.RESPONSE);
		return message;
	}

	@Override
	public Data fromJson(JsonObject dataObject) {
		return gson.fromJson(dataObject, PatientData.class);
	}

}
