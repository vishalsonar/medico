package com.sonar.vishal.medico.core.definition;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.structure.Data;

public interface BusinessLogic extends BussinessObject {

	public void getAll();

	public long getTotalRowCount();

	public void getPage(int startIndex, int endIndex);

	public void getById(String id);

	public void search(String keyword);

	public void saveOrUpdate(String functionName, Object data);

	public void delete(Object data);

	public Message execute(String functionName, Object data);

	public Data fromJson(JsonObject dataObject);
}
