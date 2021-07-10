package com.sonar.vishal.ui.definition;

import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.vaadin.data.ValidationException;

public interface BiListenerLogic<T, U> {

	Data process(T parentBinder, U childBinder, Integer id) throws ValidationException, MedicoValidationException;
}
