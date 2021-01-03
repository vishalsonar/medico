package com.sonar.vishal.medico.core.adapter;

import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.core.definition.BusinessLogic;

public class BusinessLogicAdapter implements BusinessLogic {

	@Override
	public void getAll() {
		// Do nothing
	}

	@Override
	public void getById(String id) {
		// Do nothing
	}

	@Override
	public void saveOrUpdate(String functionName, Object data) {
		// Do nothing
	}

	@Override
	public void delete(Object data) {
		// Do nothing
	}

	@Override
	public Message execute(String functionName, Object data) {
		return null;
	}

}
