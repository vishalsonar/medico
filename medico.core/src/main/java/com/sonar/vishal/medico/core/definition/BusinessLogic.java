package com.sonar.vishal.medico.core.definition;

import com.sonar.vishal.medico.common.message.common.Message;

public interface BusinessLogic extends BussinessObject {

	public void getAll();

	public long getTotalRowCount();

	public void getPage(int startIndex, int endIndex);

	public void getById(String id);

	public void saveOrUpdate(String functionName, Object data);

	public void delete(Object data);

	public Message execute(String functionName, Object data);
}
