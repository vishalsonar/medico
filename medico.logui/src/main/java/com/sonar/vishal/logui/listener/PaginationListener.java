package com.sonar.vishal.logui.listener;

import java.util.List;

import com.sonar.vishal.logui.logic.LogLogic;
import com.sonar.vishal.medico.common.pojo.Log;
import com.vaadin.addon.pagination.PaginationChangeListener;
import com.vaadin.addon.pagination.PaginationResource;
import com.vaadin.ui.Grid;

public class PaginationListener implements PaginationChangeListener {

	private static final long serialVersionUID = -662219306642379510L;
	private Grid<Log> table;
	private transient LogLogic logLogic;

	public PaginationListener(Grid<Log> table) {
		this.table = table;
		logLogic = new LogLogic();
	}

	@Override
	public void changed(PaginationResource event) {
		List<Log> subList = logLogic.getPage(event.fromIndex(), event.toIndex());
		table.setItems(subList);
	}

}
