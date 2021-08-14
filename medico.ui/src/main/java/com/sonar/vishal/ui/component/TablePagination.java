package com.sonar.vishal.ui.component;

import java.util.Arrays;
import java.util.List;

import com.sonar.vishal.ui.listener.PaginationListener;
import com.sonar.vishal.ui.listener.SearchListener;
import com.vaadin.addon.pagination.Pagination;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalSplitPanel;

public class TablePagination<T> {

	private Pagination pagination;
	private Grid<T> table;
	private TextField searchField;

	public VerticalSplitPanel init(Grid<T> table, String searchPlaceholder) {
		this.table = table;
		VerticalSplitPanel splitLayout = new VerticalSplitPanel();
		HorizontalSplitPanel searchAndPaginationSplit = new HorizontalSplitPanel();
		pagination = Component.getInstance().getPagination();
		searchField = Component.getInstance().getSearchField(searchPlaceholder);
		searchAndPaginationSplit.addComponent(searchField);
		searchAndPaginationSplit.addComponent(pagination);
		searchAndPaginationSplit.setLocked(true);
		searchAndPaginationSplit.setSplitPosition(40, Unit.PERCENTAGE);
		splitLayout.addComponent(searchAndPaginationSplit);
		splitLayout.addComponent(table);
		splitLayout.setLocked(true);
		splitLayout.setSplitPosition(10, Unit.PERCENTAGE);
		splitLayout.setSizeFull();
		return splitLayout;
	}

	public void configurePagination(T[] data, long totalCount) {
		List<T> dataList = Arrays.asList(data);
		table.setItems(dataList.subList(0, Math.min(20, dataList.size())));
		PaginationListener<T> paginationListener = new PaginationListener<>(table, dataList);
		pagination.setTotalCount(totalCount);
		pagination.setCurrentPage(1);
		pagination.addPageChangeListener(paginationListener);
		SearchListener<T> searchListener = new SearchListener<>(table, dataList, pagination);
		searchField.setValueChangeMode(ValueChangeMode.LAZY);
		searchField.addValueChangeListener(searchListener);
	}

}
