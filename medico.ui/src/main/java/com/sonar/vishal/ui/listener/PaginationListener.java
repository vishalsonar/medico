package com.sonar.vishal.ui.listener;

import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.structure.PageData;
import com.vaadin.addon.pagination.PaginationChangeListener;
import com.vaadin.addon.pagination.PaginationResource;
import com.vaadin.ui.Grid;

public class PaginationListener<T> implements PaginationChangeListener {

	private static final long serialVersionUID = -662219306642379510L;
	private Grid<T> table;
	private transient List<T> list;
	private transient PageData pageData;

	public PaginationListener(Grid<T> table, List<T> list) {
		this.table = table;
		this.list = list;
		this.pageData = new PageData();
	}

	@Override
	public void changed(PaginationResource event) {
		pageData.setStartIndex(event.fromIndex());
		pageData.setEndIndex(event.toIndex());
		createPageList();
		table.setItems(list);
	}

	@SuppressWarnings("unchecked")
	private void createPageList() {
		RestBackend backend = null;
		T refrence = list.get(0);
		if (refrence instanceof Patient) {
			backend = new RestBackend(Constant.GET_PATIENT_PAGE);
			Backend.message.setData(pageData);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Patient[].class);
			Patient[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Patient[].class);
			list = (List<T>) Arrays.asList(data);
		}
		if (refrence instanceof Product) {
			backend = new RestBackend(Constant.GET_PRODUCT_PAGE);
			Backend.message.setData(pageData);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Product[].class);
			Product[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Product[].class);
			list = (List<T>) Arrays.asList(data);
		}
		if (refrence instanceof Role) {
			backend = new RestBackend(Constant.GET_ROLE_PAGE);
			Backend.message.setData(pageData);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Role[].class);
			Role[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Role[].class);
			list = (List<T>) Arrays.asList(data);
		}
		if (refrence instanceof User) {
			backend = new RestBackend(Constant.GET_USER_PAGE);
			Backend.message.setData(pageData);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(User[].class);
			User[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), User[].class);
			list = (List<T>) Arrays.asList(data);
		}
		if (refrence instanceof Store) {
			backend = new RestBackend(Constant.GET_STORE_PAGE);
			Backend.message.setData(pageData);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Store[].class);
			Store[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Store[].class);
			list = (List<T>) Arrays.asList(data);
		}
	}
}
