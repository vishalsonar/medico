package com.sonar.vishal.ui.listener.store;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.structure.StoreData;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.exception.ValidationException;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;

public class AddStoreListener extends CRUDListener {

	private static final long serialVersionUID = 8912827547012769994L;
	private Binder<Store> storeBinder;
	private Binder<Address> addressBinder;

	public AddStoreListener(Binder<Store> storeBinder, Binder<Address> addressBinder, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.ADD_STORE, window);
		this.storeBinder = storeBinder;
		this.addressBinder = addressBinder;
	}

	@Override
	protected void doAction() {
		try {
			Store store = new Store();
			Address address = new Address();
			StoreData data = new StoreData();
			this.storeBinder.writeBean(store);
			this.addressBinder.writeBean(address);
			store.setAddress(address);
			validateString(store.getName());
			validateString(store.getAddress().getLine1());
			validateString(store.getAddress().getLine2());
			validateString(store.getAddress().getCity());
			validateString(store.getAddress().getPinCode());
			validateString(store.getAddress().getState());
			validateString(store.getDlNumber());
			validateString(store.getDlExpiry());
			validateString(store.getSxDlNumber());
			validateString(store.getSxDlExpiry());
			validateString(store.getPhoneNumber());
			validateString(store.getPanNumber());
			validateString(store.getGstin());
			validateString(store.getFassaiNumber());
			data.setStore(store);
			Backend.message.setData(data);
			doPostRespondHeader(Constant.ADD_STORE_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}
}
