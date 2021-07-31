package com.sonar.vishal.logui.listener;

import com.sonar.vishal.logui.component.Component;
import com.sonar.vishal.logui.component.LogUIConstant;
import com.sonar.vishal.medico.common.pojo.Log;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ExpandLogListener implements ClickListener {

	private static final long serialVersionUID = 9101476375205575789L;
	private Log selectedLog;
	private Component component;
	
	public ExpandLogListener() {
		this.component = new Component();
	}

	public Log getSelectedLog() {
		return selectedLog;
	}

	public void setSelectedLog(Log selectedLog) {
		this.selectedLog = selectedLog;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (selectedLog == null) {
			component.getFailureNotification(LogUIConstant.INVALID_SELECTED_LOG, LogUIConstant.EMPTY).show(Page.getCurrent());
		} else {
			Window window = new Window();
			Button button = component.getWindowCloseButton(LogUIConstant.CLOSE);
			VerticalLayout layout = new VerticalLayout();
			layout.addComponent(new Label(LogUIConstant.HEADLINE_LOG_VIEW, ContentMode.HTML));
			layout.addComponent(new Label(getLogDisplay(), ContentMode.HTML));
			layout.addComponent(button);
			window.setContent(layout);
			window.center();
			window.setSizeFull();
			button.addClickListener(new ClickListener() {
				
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					window.close();
				}
			});
			UI.getCurrent().addWindow(window);
		}
	}

	private String getLogDisplay() {
		StringBuilder builder = new StringBuilder();
		builder.append(LogUIConstant.ID_LABEL).append(selectedLog.getId()).append(LogUIConstant.BR)
			   .append(LogUIConstant.DATE_TIME_LABEL).append(selectedLog.getDateTime()).append(LogUIConstant.BR)
			   .append(LogUIConstant.IP_LABEL).append(selectedLog.getIp()).append(LogUIConstant.BR)
			   .append(LogUIConstant.COMPONENT_LABEL).append(selectedLog.getComponent()).append(LogUIConstant.BR)
			   .append(LogUIConstant.CLASS_NAME_LABEL).append(selectedLog.getClassName()).append(LogUIConstant.BR)
			   .append(LogUIConstant.SEVERITY_LABEL).append(selectedLog.getSeverity()).append(LogUIConstant.BR)
			   .append(LogUIConstant.USER_ID_LABEL).append(selectedLog.getUserId()).append(LogUIConstant.BR)
			   .append(LogUIConstant.MESSAGE_LABEL).append(selectedLog.getMessage()).append(LogUIConstant.BR);
		return builder.toString();
	}

}
