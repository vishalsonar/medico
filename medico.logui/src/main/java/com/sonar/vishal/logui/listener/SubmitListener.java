package com.sonar.vishal.logui.listener;

import java.util.List;

import com.sonar.vishal.logui.component.Component;
import com.sonar.vishal.logui.component.LogUIConstant;
import com.sonar.vishal.logui.logic.LogLogic;
import com.sonar.vishal.medico.common.pojo.Log;
import com.sonar.vishal.medico.common.structure.LogData;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.server.Page;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;

public class SubmitListener implements ClickListener {

	private static final long serialVersionUID = -6004988276634900129L;
	private Grid<Log> table;
	private Binder<Log> logBinder;
	private Binder<LogData> logDataBinder;
	private transient LogLogic logic;
	private transient Component component;

	public SubmitListener(Binder<Log> logBinder, Binder<LogData> logDataBinder, Grid<Log> table) {
		this.logBinder = logBinder;
		this.logDataBinder = logDataBinder;
		this.logic = new LogLogic();
		this.component = new Component();
		this.table = table;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		try {
			Log log = new Log();
			LogData logData = new LogData();
			logBinder.writeBean(log);
			logDataBinder.writeBean(logData);
			logData.setLog(log);
			List<Log> list = logic.getFilteredLog(logData);
			if (list == null) {
				component.getFailureNotification(LogUIConstant.NO_DATA_FOUND, LogUIConstant.EMPTY).show(Page.getCurrent());
				return;
			}
			if (list.isEmpty()) {
				component.getFailureNotification(LogUIConstant.NO_DATA_FOUND, LogUIConstant.PLEASE_TRY_OTHER_COMBINATION).show(Page.getCurrent());
				return;
			}
			table.setItems(list);
		} catch (ValidationException e) {
			// Do Nothing.
		}
	}

}
