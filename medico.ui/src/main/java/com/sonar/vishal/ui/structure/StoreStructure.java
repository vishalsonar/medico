package com.sonar.vishal.ui.structure;

import java.util.Optional;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.structure.StoreData;
import com.sonar.vishal.ui.backend.RestBackend;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.sonar.vishal.ui.window.store.AddStoreWindow;
import com.sonar.vishal.ui.window.store.UpdateStoreWindow;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class StoreStructure implements CRUDStructure {

	private VerticalLayout layout;
	private Grid<Store> table;
	private RestBackend backend;
	private Store selectedStore;
	private Notification notification;

	public StoreStructure() {
		layout = new VerticalLayout();
		table = new Grid<>();
		table.setSizeFull();
		table.setSelectionMode(SelectionMode.SINGLE);
		layout.addComponent(table);
	}

	@Override
	public Object get() {
		list();
		table.addColumn(Store::getId).setCaption("Id");
		table.addColumn(Store::getName).setCaption("Name");
		table.addColumn(Store::getAddressString).setCaption("Address");
		table.addColumn(Store::getPhoneNumber).setCaption("Phone Number");
		table.addColumn(Store::getDlNumber).setCaption("DL Number");
		table.addColumn(Store::getDlExpiry).setCaption("DL Expiry");
		table.addColumn(Store::getSxDlNumber).setCaption("Sx DL Number");
		table.addColumn(Store::getSxDlExpiry).setCaption("Sx DL Number Expiry");
		table.addColumn(Store::getPanNumber).setCaption("PAN Number");
		table.addColumn(Store::getCinNumber).setCaption("CIN Number");
		table.addColumn(Store::getGstin).setCaption("GST");
		table.addColumn(Store::getFassaiNumber).setCaption("FASSAI");
		table.addSelectionListener(new SelectionListener<Store>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectionChange(SelectionEvent<Store> event) {
				try {
					Optional<Store> optionalStore = event.getFirstSelectedItem();
					if (optionalStore.isPresent()) {
						selectedStore = optionalStore.get();
					}
				} catch (Exception e) {
					// Do Nothing.
				}
			}
		});
		return layout;

	}

	@Override
	public void list() {
		backend = new RestBackend(Constant.GET_STORE_LIST);
		Store[] data = (Store[]) backend.doPostRespondData(Store[].class);
		table.setItems(data);
	}

	@Override
	public void add() {
		MedicoWindow window = new AddStoreWindow(this);
		window.setWindow();
		UI.getCurrent().addWindow(window);
	}

	@Override
	public void update() {
		try {
			MedicoWindow window = new UpdateStoreWindow(this, selectedStore);
			window.setWindow();
			UI.getCurrent().addWindow(window);
		} catch (Exception e) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.SELECT_ROW_TO_UPDATE);
			notification.show(Page.getCurrent());
		}
	}

	@Override
	public void delete() {
		if (selectedStore == null) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.SELECT_ROW_TO_DELETE);
		} else {
			StoreData data = new StoreData();
			data.setStore(selectedStore);
			backend = new RestBackend(Constant.DELETE_STORE);
			Backend.message.setData(data);
			boolean response = backend.doPostRespondHeader();
			if (response) {
				notification = Component.getInstance().getSuccessNotification(Constant.SUCCESS, Constant.DELETE_STORE_SUCESS_MESSAGE);
				list();
			} else {
				notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.GENERAL_ERROR_MESSAGE);
			}
		}
		notification.show(Page.getCurrent());
	}

}
