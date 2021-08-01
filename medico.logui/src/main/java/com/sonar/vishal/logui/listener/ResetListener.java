package com.sonar.vishal.logui.listener;

import java.util.List;

import com.sonar.vishal.logui.component.Component;
import com.sonar.vishal.logui.component.LogUIConstant;
import com.sonar.vishal.logui.logic.LogLogic;
import com.sonar.vishal.medico.common.pojo.Log;
import com.vaadin.addon.pagination.Pagination;
import com.vaadin.server.Page;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;

public class ResetListener implements ClickListener {

	private static final long serialVersionUID = -5875037201765744411L;
	private transient Component component;
	private transient LogLogic logLogic;
	private Grid<Log> table;
	private Pagination pagination;

	public ResetListener(Grid<Log> table, Pagination pagination) {
		component = new Component();
		logLogic = new LogLogic();
		this.table = table;
		this.pagination = pagination;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		List<Log> data = logLogic.getAll();
		if (data == null) {
			component.getServerFailureNotification(LogUIConstant.INITIALIZATION_FAILED).show(Page.getCurrent());
		} else {
			pagination.setTotalCount(data.size());
			pagination.addPageChangeListener(new PaginationListener(table, data));
			table.setItems(data.subList(0, 20));
		}
	}

}
