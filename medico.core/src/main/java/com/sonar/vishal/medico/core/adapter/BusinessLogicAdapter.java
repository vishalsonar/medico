package com.sonar.vishal.medico.core.adapter;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.structure.Data;
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

	@Override
	public void getPage(int startIndex, int endIndex) {
		// Do nothing
	}

	@Override
	public long getTotalRowCount() {
		return 0L;
	}

	@Override
	public void search(String keyword) {
		// Do nothing
	}

	@Override
	public Data fromJson(JsonObject dataObject) {
		return null;
	}

}
