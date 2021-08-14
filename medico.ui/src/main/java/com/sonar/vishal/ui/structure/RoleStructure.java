package com.sonar.vishal.ui.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.component.TablePagination;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.sonar.vishal.ui.window.role.AddRoleWindow;
import com.sonar.vishal.ui.window.role.UpdateRoleWindow;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class RoleStructure implements CRUDStructure {

	private VerticalLayout layout;
	private Grid<Role> table;
	private RestBackend backend;
	private Role selectedRole;
	private Notification notification;
	private TablePagination<Role> roleTablePagination;

	public RoleStructure() {
		layout = new VerticalLayout();
		roleTablePagination = new TablePagination<>();
		table = new Grid<>();
		table.setSizeFull();
		table.setSelectionMode(SelectionMode.SINGLE);
		layout.addComponent(roleTablePagination.init(table, UIConstant.FILTER_ROLE));
	}

	@Override
	public Object get() {
		list();
		table.addColumn(Role::getId).setCaption(UIConstant.ID);
		table.addColumn(Role::getName).setCaption(UIConstant.NAME);
		table.addColumn(Role::getModule).setCaption(UIConstant.OPTION);
		table.addSelectionListener(new SelectionListener<Role>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectionChange(SelectionEvent<Role> event) {
				try {
					Optional<Role> optionalRole = event.getFirstSelectedItem();
					if (optionalRole.isPresent()) {
						selectedRole = optionalRole.get();
					}
				} catch(Exception e) {
					LoggerApi.error(getClass().getName(), e.getMessage());
				}
			}
		});
		return layout;
	}

	@Override
	public void list() {
		User thisUser = null;
		Object userData = VaadinSession.getCurrent().getSession().getAttribute(UIConstant.S_USER);
		if (userData instanceof User) {
			thisUser = (User) userData;
		}
		backend = new RestBackend(Constant.GET_ROLE_LIST);
		Role[] data = (Role[]) backend.doPostRespondData(Role[].class);
		List<Role> dataList = new ArrayList<>();
		for (Role role : data) {
			if (thisUser != null && thisUser.getRole().getId() == role.getId()) {
				continue;
			}
			dataList.add(role);
		}
		roleTablePagination.configurePagination(dataList.toArray(new Role[dataList.size()]));
	}

	@Override
	public void add() {
		MedicoWindow window = new AddRoleWindow(this);
		window.setWindow();
		UI.getCurrent().addWindow(window);
	}

	@Override
	public void update() {
		try {
			MedicoWindow window = new UpdateRoleWindow(this, selectedRole);
			window.setWindow();
			UI.getCurrent().addWindow(window);
			selectedRole = null;
		} catch (Exception e) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.SELECT_ROW_TO_UPDATE);
			notification.show(Page.getCurrent());
		}
	}

	@Override
	public void delete() {
		if (selectedRole == null) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.SELECT_ROW_TO_DELETE);
		} else {
			RoleData data = new RoleData();
			data.setRole(selectedRole);
			backend = new RestBackend(Constant.DELETE_ROLE);
			Backend.message.setData(data);
			boolean response = backend.doPostRespondHeader();
			if (response) {
				notification = Component.getInstance().getSuccessNotification(Constant.SUCCESS, Constant.DELETE_ROLE_SUCESS_MESSAGE);
				list();
				selectedRole = null;
			} else {
				notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.GENERAL_ERROR_MESSAGE);
			}
		}
		notification.show(Page.getCurrent());
	}

}
