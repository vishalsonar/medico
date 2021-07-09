package com.sonar.vishal.ui.listener.store;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.ui.definition.Backend;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.listener.CRUDListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class UpdateStoreListener extends CRUDListener {

	private static final long serialVersionUID = -4272343966792618258L;
	private Binder<Store> storeBinder;
	private Binder<Address> addressBinder;
	private transient StoreListenerLogic logic;

	public UpdateStoreListener(Binder<Store> storeBinder, Binder<Address> addressBinder, int id, MedicoWindow window, CRUDStructure structure) {
		super(structure, Constant.UPDATE_STORE, window);
		this.storeBinder = storeBinder;
		this.addressBinder = addressBinder;
		this.id = id;
		this.logic = new StoreListenerLogic();
	}

	@Override
	protected void doAction() {
		try {
			Backend.message.setData(logic.process(storeBinder, addressBinder, id));
			doPostRespondHeader(Constant.UPDATE_STORE_SUCCESS_MESSAGE, Constant.GENERAL_ERROR_MESSAGE);
		} catch (ValidationException e) {
			notifyError(Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notifyError(Constant.GENERAL_ERROR_MESSAGE);
		}
	}

}
