package com.sonar.vishal.ui.definition;

import com.sonar.vishal.medico.common.structure.Data;

public interface ListenerLogic<T> {

	Data process(T binder, Integer id) throws Exception;

}
