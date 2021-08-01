package com.sonar.vishal.ui.structure;

import java.util.Optional;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.component.TablePagination;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.sonar.vishal.ui.window.user.AddUserWindow;
import com.sonar.vishal.ui.window.user.UpdateUserWindow;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class UserStructure implements CRUDStructure {

	private VerticalLayout layout;
	private VerticalSplitPanel splitLayout;
	private Grid<User> table;
	private RestBackend backend;
	private User selectedUser;
	private Notification notification;
	private TablePagination<User> tablePagination;

	public UserStructure() {
		layout = new VerticalLayout();
		tablePagination = new TablePagination<User>();
		table = new Grid<>();
		table.setSizeFull();
		table.setSelectionMode(SelectionMode.SINGLE);
		splitLayout = tablePagination.init(table);
		layout.addComponent(splitLayout);
	}

	@Override
	public Object get() {
		list();
		table.addColumn(User::getId).setCaption(UIConstant.ID);
		table.addColumn(User::getUserName).setCaption(UIConstant.NAME);
		table.addColumn(User::getPassword).setCaption(UIConstant.PASSWORD);
		table.addColumn(User::getRoleAsString).setCaption(UIConstant.ROLE);
		table.addSelectionListener(new SelectionListener<User>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectionChange(SelectionEvent<User> event) {
				try {
					Optional<User> optionalRole = event.getFirstSelectedItem();
					if (optionalRole.isPresent()) {
						selectedUser = optionalRole.get();
					}
				} catch (Exception e) {
					LoggerApi.error(getClass().getName(), e.getMessage());
				}
			}
		});
		return layout;
	}

	@Override
	public void list() {
		backend = new RestBackend(Constant.GET_USER_LIST);
		User[] data = (User[]) backend.doPostRespondData(User[].class);
		for (User user : data) {
			user.update();
		}
		tablePagination.configurePagination(data);
	}

	@Override
	public void add() {
		MedicoWindow window = new AddUserWindow(this);
		window.setWindow();
		UI.getCurrent().addWindow(window);
	}

	@Override
	public void update() {
		try {
			MedicoWindow window = new UpdateUserWindow(this, selectedUser);
			window.setWindow();
			UI.getCurrent().addWindow(window);
			selectedUser = null;
		} catch (Exception e) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.SELECT_ROW_TO_UPDATE);
			notification.show(Page.getCurrent());
		}
	}

	@Override
	public void delete() {
		if (selectedUser == null) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.SELECT_ROW_TO_DELETE);
		} else {
			UserData data = new UserData();
			data.setUser(selectedUser);
			backend = new RestBackend(Constant.DELETE_USER);
			Backend.message.setData(data);
			boolean response = backend.doPostRespondHeader();
			if (response) {
				notification = Component.getInstance().getSuccessNotification(Constant.SUCCESS, Constant.DELETE_USER_SUCESS_MESSAGE);
				list();
				selectedUser = null;
			} else {
				notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.GENERAL_ERROR_MESSAGE);
			}
		}
		notification.show(Page.getCurrent());
	}

}
