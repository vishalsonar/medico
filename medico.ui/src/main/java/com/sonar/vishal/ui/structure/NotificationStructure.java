package com.sonar.vishal.ui.structure;

import java.util.Optional;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Notification;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.structure.NotificationData;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.component.TablePagination;
import com.sonar.vishal.ui.definition.GenericStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.util.UIUtil;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;

public class NotificationStructure implements GenericStructure {

	private VerticalLayout layout;
	private Grid<Notification> table;
	protected RestBackend backend;
	protected Notification selectedNotification;
	protected com.vaadin.ui.Notification notification;
	private TablePagination<Notification> notificationTablePagination;

	public NotificationStructure() {
		layout = new VerticalLayout();
		notificationTablePagination = new TablePagination<>();
		table = new Grid<>();
		table.setSizeFull();
		table.setSelectionMode(SelectionMode.SINGLE);
		layout.addComponent(notificationTablePagination.init(table, UIConstant.FILTER_NOTIFICATION));
	}

	@Override
	public Object get() {
		list();
		table.addColumn(Notification::getMessage).setCaption(UIConstant.MESSAGES);
		table.addSelectionListener(new SelectionListener<Notification>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectionChange(SelectionEvent<Notification> event) {
				try {
					Optional<Notification> optionalRole = event.getFirstSelectedItem();
					if (optionalRole.isPresent()) {
						selectedNotification = optionalRole.get();
					}
				} catch (Exception e) {
					LoggerApi.error(getClass().getName(), e.getMessage());
				}
			}
		});
		return layout;
	}

	private void list() {
		Notification notification = UIUtil.getThisUserNotification();
		NotificationData notificationData = new NotificationData();
		notificationData.setNotification(notification);
		backend = new RestBackend(Constant.GET_NOTIFICATION_LIST);
		RestBackend.message.setData(notificationData);
		JsonObject responseObject = (JsonObject) backend.doPostRespondData(Notification[].class);
		long totalCount = responseObject.get(UIConstant.COUNT).getAsLong();
		Notification[] data = GSON.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Notification[].class);
		notificationTablePagination.configurePagination(data, totalCount);
	}

	@Override
	public void action(int token) {
		if (selectedNotification == null) {
			notifyError(Constant.SELECT_ROW_TO_DELETE);
			return;
		}
		if (token == 1) {
			buttonOneaction();
			list();
		}
	}

	@Override
	public void buttonOneaction() {
		NotificationData data = new NotificationData();
		data.setNotification(selectedNotification);
		backend = new RestBackend(Constant.DELETE_NOTIFICATION);
		RestBackend.message.setData(data);
		if (backend.doPostRespondHeader()) {
			notifySuccess(UIConstant.NOTIFICATION_DELETED);
		} else {
			notifyError(UIConstant.NOTIFICATION_DELETE_FAILED);
		}
	}

	@Override
	public void buttonTwoaction() {
		// Do Nothing.
	}

	@Override
	public void buttonThreeaction() {
		// Do Nothing.
	}

}
