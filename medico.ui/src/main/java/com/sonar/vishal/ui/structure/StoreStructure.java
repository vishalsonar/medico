package com.sonar.vishal.ui.structure;

import java.util.Optional;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.structure.StoreData;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.component.TablePagination;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.util.UIConstant;
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
import com.vaadin.ui.VerticalSplitPanel;

public class StoreStructure implements CRUDStructure {

	private VerticalLayout layout;
	private VerticalSplitPanel splitLayout;
	private Grid<Store> table;
	private RestBackend backend;
	private Store selectedStore;
	private Notification notification;
	private TablePagination<Store> tablePagination;
	
	public StoreStructure() {
		layout = new VerticalLayout();
		tablePagination = new TablePagination<Store>();
		table = new Grid<>();
		table.setSizeFull();
		table.setSelectionMode(SelectionMode.SINGLE);
		splitLayout = tablePagination.init(table);
		layout.addComponent(splitLayout);
	}

	@Override
	public Object get() {
		list();
		table.addColumn(Store::getId).setCaption(UIConstant.ID);
		table.addColumn(Store::getName).setCaption(UIConstant.NAME);
		table.addColumn(Store::getAddressString).setCaption(UIConstant.ADDRESS);
		table.addColumn(Store::getPhoneNumber).setCaption(UIConstant.PHONE_NUMBER);
		table.addColumn(Store::getDlNumber).setCaption(UIConstant.DL_NUMBER);
		table.addColumn(Store::getDlExpiry).setCaption(UIConstant.DL_EXPIRY);
		table.addColumn(Store::getSxDlNumber).setCaption(UIConstant.SX_DL_NUMBER);
		table.addColumn(Store::getSxDlExpiry).setCaption(UIConstant.SX_DL_EXPIRY);
		table.addColumn(Store::getPanNumber).setCaption(UIConstant.PAN_NUMBER);
		table.addColumn(Store::getCinNumber).setCaption(UIConstant.CIN_NUMBER);
		table.addColumn(Store::getGstin).setCaption(UIConstant.GST);
		table.addColumn(Store::getFassaiNumber).setCaption(UIConstant.FASSAI);
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
					LoggerApi.error(getClass().getName(), e.getMessage());
				}
			}
		});
		return layout;

	}

	@Override
	public void list() {
		backend = new RestBackend(Constant.GET_STORE_LIST);
		Store[] data = (Store[]) backend.doPostRespondData(Store[].class);
		tablePagination.configurePagination(data);
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
			selectedStore = null;
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
				selectedStore = null;
			} else {
				notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.GENERAL_ERROR_MESSAGE);
			}
		}
		notification.show(Page.getCurrent());
	}

}
