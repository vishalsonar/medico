package com.sonar.vishal.medico.core.logic;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.structure.KeyData;
import com.sonar.vishal.medico.core.adapter.BusinessLogicAdapter;

public class KeyLogic extends BusinessLogicAdapter {

	@Override
	public Message execute(String functionName, Object data) {
		setSucessMessage(Constant.GET_KEY);
		message.getHeader().setType(Constant.RESPONSE);
		message.setData((KeyData) data);
		return message;
	}
}
