package com.sonar.vishal.ui.listener;

import java.util.List;

import com.vaadin.addon.pagination.PaginationChangeListener;
import com.vaadin.addon.pagination.PaginationResource;
import com.vaadin.ui.Grid;

public class PaginationListener<T> implements PaginationChangeListener {

	private static final long serialVersionUID = -662219306642379510L;
	private Grid<T> table;
	private transient List<T> list;

	public PaginationListener(Grid<T> table, List<T> list) {
		this.table = table;
		this.list = list;
	}

	@Override
	public void changed(PaginationResource event) {
		List<T> subList = list.subList(event.fromIndex(), event.toIndex());
		table.setItems(subList);
	}

}
