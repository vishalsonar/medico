package com.sonar.vishal.ui.definition;

import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.vaadin.data.ValidationException;

public interface ListenerLogic<T> {

	Data process(T binder, Integer id) throws ValidationException, MedicoValidationException;

}
