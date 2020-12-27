package com.sonar.vishal.ui.structure;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.ui.backend.RestBackend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.window.AddRoleWindow;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class RoleStructure implements CRUDStructure {

	private VerticalLayout layout;
	private Grid<Role> table;
	private RestBackend backend;
	private Role selectedRole;

	public RoleStructure() {
		backend = new RestBackend(Constant.GET_ROLE_LIST);
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
				selectedRole = event.getFirstSelectedItem().get();
			}
		});
		return layout;
	}

	@Override
	public void list() {
		Role[] data = (Role[]) backend.doPostRespondData(Role[].class);
		table.setItems(data);
	}

	@Override
	public void add() {
		MedicoWindow window = new AddRoleWindow();
		window.setWindow();
		UI.getCurrent().addWindow(window);
	}

	@Override
	public void update() {
		System.out.println("update");
	}

	@Override
	public void delete() {
		System.out.println("delete");
	}

}
