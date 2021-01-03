package com.sonar.vishal.ui.listener;

import com.mysql.cj.util.StringUtils;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.ui.backend.RestBackend;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.ValidationException;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.server.Page;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class CRUDListener implements ClickListener {

	private static final long serialVersionUID = -7246052250751624975L;
	private Notification notification;
	private MedicoWindow window;
	private transient RestBackend backend;
	private transient CRUDStructure structure;

	public CRUDListener(CRUDStructure structure, String message, MedicoWindow window) {
		this.backend = new RestBackend(message);
		this.window = window;
		this.structure = structure;
	}

	protected void validateString(String input) throws ValidationException {
		if (StringUtils.isEmptyOrWhitespaceOnly(input)) {
			throw new ValidationException();
		}
	}

	protected void notifySuccess(String message) {
		notification = Component.getInstance().getSuccessNotification(Constant.SUCCESS, message);
		notification.show(Page.getCurrent());
	}

	protected void notifyError(String message) {
		notification = Component.getInstance().getFailureNotification(Constant.ERROR, message);
		notification.show(Page.getCurrent());
	}

	protected void doPostRespondHeader(String successMessage, String errorMessage) {
		boolean response = backend.doPostRespondHeader();
		if (response) {
			notifySuccess(successMessage);
			structure.list();
			window.close();
		} else {
			notifyError(errorMessage);
		}
	}
	
	protected void doAction() {
		// Do Nothing, override method.
	}

	@Override
	public void buttonClick(ClickEvent event) {
		doAction();
	}

}
