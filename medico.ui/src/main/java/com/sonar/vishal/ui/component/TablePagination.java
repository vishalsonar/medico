package com.sonar.vishal.ui.component;

import java.util.Arrays;
import java.util.List;

import com.sonar.vishal.ui.listener.PaginationListener;
import com.vaadin.addon.pagination.Pagination;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalSplitPanel;

public class TablePagination<T> {

	private Pagination pagination;
	private Grid<T> table;
	private VerticalSplitPanel splitLayout;

	public VerticalSplitPanel init(Grid<T> table) {
		this.table = table;
		splitLayout = new VerticalSplitPanel();
		pagination = Component.getInstance().getPagination();
		splitLayout.addComponent(pagination);
		splitLayout.addComponent(table);
		splitLayout.setLocked(true);
		splitLayout.setSplitPosition(10, Unit.PERCENTAGE);
		splitLayout.setSizeFull();
		return splitLayout;
	}

	public void configurePagination(T[] data) {
		List<T> dataList = Arrays.asList(data);
		int dataListCount = dataList.size();
		int subDataListCount = dataListCount <= 20 ? dataListCount : 20;
		table.setItems(dataList.subList(0, subDataListCount));
		pagination.setTotalCount(dataListCount);
		pagination.addPageChangeListener(new PaginationListener<T>(table, dataList));
	}

}
