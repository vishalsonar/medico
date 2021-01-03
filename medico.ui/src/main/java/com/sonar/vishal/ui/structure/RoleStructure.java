package com.sonar.vishal.ui.structure;

import java.util.Optional;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.ui.backend.RestBackend;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.sonar.vishal.ui.window.role.AddRoleWindow;
import com.sonar.vishal.ui.window.role.UpdateRoleWindow;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.server.Page;
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

	public RoleStructure() {
		layout = new VerticalLayout();
		table = new Grid<Role>();
		table.setSizeFull();
		table.setSelectionMode(SelectionMode.SINGLE);
		layout.addComponent(table);
	}

	@Override
	public Object get() {
		list();
		table.addColumn(Role::getId).setCaption("Id");
		table.addColumn(Role::getName).setCaption("Name");
		table.addColumn(Role::getModule).setCaption("Option");
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
					// Do Nothing.
				}
			}
		});
		return layout;
	}

	@Override
	public void list() {
		backend = new RestBackend(Constant.GET_ROLE_LIST);
		Role[] data = (Role[]) backend.doPostRespondData(Role[].class);
		table.setItems(data);
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
			} else {
				notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.GENERAL_ERROR_MESSAGE);
			}
		}
		notification.show(Page.getCurrent());
	}

}
