package com.sonar.vishal.ui.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Bill;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.structure.SearchData;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.util.UIUtil;
import com.vaadin.addon.pagination.Pagination;
import com.vaadin.server.Page;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

public class SearchListener<T> implements ClickListener {

	private static final long serialVersionUID = -8749092197896815056L;
	private transient List<T> list;
	private Grid<T> table;
	private Pagination pagination;
	private TextField searchField;
	private transient RestBackend backend;

	public SearchListener(Grid<T> table, List<T> list, Pagination pagination, TextField searchField) {
		this.table = table;
		this.list = list;
		this.pagination = pagination;
		this.searchField = searchField;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		String searchValue = searchField.getValue();
		if (searchValue == null || searchValue.trim().length() == 0 || list.isEmpty() || !UIUtil.isAlphaNumericSpaceHypenString(searchValue)) {
			Component.getInstance().getFailureNotification(UIConstant.NO_DATA_FOUND, UIConstant.NO_DATA_FOUND_MESSAGE).show(Page.getCurrent());
			return;
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
			setBackend(Constant.SEARCH_PATIENT, searchValue);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Patient[].class);
			Patient[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Patient[].class);
			filteredList = (List<T>) Arrays.asList(data);
		}
		if (entry instanceof Product) {
			setBackend(Constant.SEARCH_PRODUCT, searchValue);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Product[].class);
			Product[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Product[].class);
			filteredList = (List<T>) Arrays.asList(data);
		}
		if (entry instanceof Role) {
			setBackend(Constant.SEARCH_ROLE, searchValue);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Role[].class);
			Role[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Role[].class);
			List<Role> roleList = new ArrayList<>(Arrays.asList(data));
			User thisUser = UIUtil.getSessionUser();
			if (thisUser != null) {
				Role role = thisUser.getRole();
				filteredList = (List<T>) roleList.stream().filter(item -> !item.getName().equals(role.getName())).collect(Collectors.toList());
			}
		}
		if (entry instanceof User) {
			setBackend(Constant.SEARCH_USER, searchValue);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(User[].class);
			User[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), User[].class);
			List<User> userList = new ArrayList<>(Arrays.asList(data));
			User thisUser = UIUtil.getSessionUser();
			if (thisUser != null) {
				filteredList = (List<T>) userList.stream().filter(item -> !item.getUserName().equals(thisUser.getUserName())).collect(Collectors.toList());
			}
		}
		if (entry instanceof Store) {
			setBackend(Constant.SEARCH_STORE, searchValue);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Store[].class);
			Store[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Store[].class);
			filteredList = (List<T>) Arrays.asList(data);
		}
		if (entry instanceof Bill) {
			setBackend(Constant.SEARCH_BILL, searchValue);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Bill[].class);
			Bill[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Bill[].class);
			filteredList = (List<T>) Arrays.asList(data);
		}
		return filteredList;
	}
	
	private void setBackend(String functionName, String keyword) {
		backend = new RestBackend(functionName);
		SearchData searchData = new SearchData();
		searchData.setKeyword(keyword);
		Backend.message.setData(searchData);
	}

}
