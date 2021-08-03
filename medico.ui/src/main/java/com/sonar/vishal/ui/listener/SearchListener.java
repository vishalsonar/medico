package com.sonar.vishal.ui.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.addon.pagination.Pagination;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Grid;

public class SearchListener<T> implements ValueChangeListener<String> {

	private static final long serialVersionUID = -8749092197896815056L;
	private List<T> list;
	private Grid<T> table;
	private Pagination pagination;

	public SearchListener(Grid<T> table, List<T> list, Pagination pagination) {
		this.table = table;
		this.list = list;
		this.pagination = pagination;
	}

	@Override
	public void valueChange(ValueChangeEvent<String> event) {
		String searchValue = event.getValue();
		if (searchValue == null || searchValue.trim().length() == 0 || list.isEmpty()) {
			Component.getInstance().getFailureNotification(UIConstant.NO_DATA_FOUND, UIConstant.NO_DATA_FOUND_MESSAGE).show(Page.getCurrent());
		}
		List<T> dataList = getFilterList(searchValue);
		if (dataList.isEmpty()) {
			Component.getInstance().getFailureNotification(UIConstant.NO_DATA_FOUND, UIConstant.NO_DATA_FOUND_MESSAGE).show(Page.getCurrent());
			dataList = list;
		}
		int dataListCount = dataList.size();
		int subDataListCount = dataListCount <= 20 ? dataListCount : 20;
		table.setItems(dataList.subList(0, subDataListCount));
		pagination.setTotalCount(dataListCount);
		pagination.addPageChangeListener(new PaginationListener<T>(table, dataList));
	}

	@SuppressWarnings("unchecked")
	private List<T> getFilterList(String searchValue) {
		List<T> filteredList = new ArrayList<>();
		Object entry = list.get(0);
		if (entry instanceof Patient) {
			List<Patient> dataList = (List<Patient>) list;
			filteredList = (List<T>) dataList.stream().filter(action -> action.getPatientName().contains(searchValue)).collect(Collectors.toList());
		}
		if (entry instanceof Product) {
			List<Product> dataList = (List<Product>) list;
			filteredList = (List<T>) dataList.stream().filter(action -> action.getDescription().contains(searchValue)).collect(Collectors.toList());
		}
		if (entry instanceof Role) {
			List<Role> dataList = (List<Role>) list;
			filteredList = (List<T>) dataList.stream().filter(action -> action.getName().contains(searchValue)).collect(Collectors.toList());
		}
		if (entry instanceof User) {
			List<User> dataList = (List<User>) list;
			filteredList = (List<T>) dataList.stream().filter(action -> action.getUserName().contains(searchValue)).collect(Collectors.toList());
		}
		if (entry instanceof Store) {
			List<Store> dataList = (List<Store>) list;
			filteredList = (List<T>) dataList.stream().filter(action -> action.getName().contains(searchValue)).collect(Collectors.toList());
		}
		return filteredList;
	}

}
