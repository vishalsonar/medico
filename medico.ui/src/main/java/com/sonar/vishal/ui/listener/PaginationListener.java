package com.sonar.vishal.ui.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Bill;
import com.sonar.vishal.medico.common.pojo.Notification;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.structure.NotificationPageData;
import com.sonar.vishal.medico.common.structure.PageData;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.util.UIUtil;
import com.vaadin.addon.pagination.PaginationChangeListener;
import com.vaadin.addon.pagination.PaginationResource;
import com.vaadin.server.VaadinSession;
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
		if (list != null && !list.isEmpty()) {
			createPageList();
		}
		table.setItems(list);
	}

	@SuppressWarnings("unchecked")
	private void createPageList() {
		RestBackend backend = null;
		T reference = list.get(0);
		if (reference instanceof Patient) {
			backend = new RestBackend(Constant.GET_PATIENT_PAGE);
			Backend.message.setData(pageData);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Patient[].class);
			Patient[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Patient[].class);
			list = (List<T>) Arrays.asList(data);
		}
		if (reference instanceof Product) {
			backend = new RestBackend(Constant.GET_PRODUCT_PAGE);
			Backend.message.setData(pageData);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Product[].class);
			Product[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Product[].class);
			list = (List<T>) Arrays.asList(data);
		}
		if (reference instanceof Role) {
			backend = new RestBackend(Constant.GET_ROLE_PAGE);
			Backend.message.setData(pageData);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Role[].class);
			Role[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Role[].class);
			filterRole(new ArrayList<>(Arrays.asList(data)));
		}
		if (reference instanceof User) {
			backend = new RestBackend(Constant.GET_USER_PAGE);
			Backend.message.setData(pageData);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(User[].class);
			User[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), User[].class);
			filterUser(new ArrayList<>(Arrays.asList(data)));
		}
		if (reference instanceof Store) {
			backend = new RestBackend(Constant.GET_STORE_PAGE);
			Backend.message.setData(pageData);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Store[].class);
			Store[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Store[].class);
			list = (List<T>) Arrays.asList(data);
		}
		if (reference instanceof Bill) {
			backend = new RestBackend(Constant.GET_BILL_PAGE);
			Backend.message.setData(pageData);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Bill[].class);
			Bill[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Bill[].class);
			list = (List<T>) Arrays.asList(data);
		}
		if (reference instanceof Notification) {
			backend = new RestBackend(Constant.GET_NOTIFICATION_PAGE);
			NotificationPageData notificationPageData = new NotificationPageData();
			Notification notification = new Notification();
			notification.setUser(UIUtil.getSessionUser());
			notificationPageData.setStartIndex(this.pageData.getStartIndex());
			notificationPageData.setEndIndex(this.pageData.getEndIndex());
			notificationPageData.setNotification(notification);
			Backend.message.setData(notificationPageData);
			JsonObject responseObject = (JsonObject) backend.doPostRespondData(Notification[].class);
			Notification[] data = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Notification[].class);
			list = (List<T>) Arrays.asList(data);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void filterUser(ArrayList<User> userList) {
		User currentUser = (User) VaadinSession.getCurrent().getSession().getAttribute(UIConstant.S_USER);
		list = new ArrayList<>();
		for (User user : userList) {
			if (user.getId() == currentUser.getId()) {
				continue;
			}
			user.update();
			list.add((T) user);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void filterRole(ArrayList<Role> roleList) {
		User currentUser = (User) VaadinSession.getCurrent().getSession().getAttribute(UIConstant.S_USER);
		list = new ArrayList<>();
		for (Role role : roleList) {
			if (currentUser.getRole().getName().equals(role.getName())) {
				continue;
			}
			list.add((T) role);
		}
	}
}
