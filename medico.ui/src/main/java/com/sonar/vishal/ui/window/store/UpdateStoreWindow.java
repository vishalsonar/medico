package com.sonar.vishal.ui.window.store;

import java.util.Objects;

import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.listener.store.UpdateStoreListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;

public class UpdateStoreWindow extends MedicoWindow {

	private static final long serialVersionUID = 1286841791002697168L;
	private transient StoreWindowDecorator decorator;
	private transient Store selectedStore;

	public UpdateStoreWindow(CRUDStructure structure, Store selectedStore) {
		super(UIConstant.UPDATE_STORE, structure);
		decorator = new StoreWindowDecorator();
		this.selectedStore = selectedStore;
	}

	@Override
	public void setWindow() {
		decorator.name.setValue(selectedStore.getName());
		decorator.line1.setValue(selectedStore.getAddress().getLine1());
		decorator.line2.setValue(selectedStore.getAddress().getLine2());
		decorator.city.setValue(selectedStore.getAddress().getCity());
		decorator.pinCode.setValue(selectedStore.getAddress().getPinCode());
		decorator.state.setValue(selectedStore.getAddress().getState());
		decorator.phoneNumber.setValue(selectedStore.getPhoneNumber());
		decorator.dlNumber.setValue(selectedStore.getDlNumber());
		decorator.dlExpiry.setValue(selectedStore.getDlExpiry());
		decorator.sxDlNumber.setValue(selectedStore.getSxDlNumber());
		decorator.sxDlExpiry.setValue(selectedStore.getSxDlExpiry());
		decorator.panNumber.setValue(selectedStore.getPanNumber());
		decorator.cinNumber.setValue(selectedStore.getCinNumber());
		decorator.gst.setValue(selectedStore.getGstin());
		decorator.fassai.setValue(selectedStore.getFassaiNumber());
		addComponents(decorator.name, decorator.line1, decorator.line2, decorator.city, decorator.pinCode,
				decorator.state, decorator.phoneNumber, decorator.dlNumber, decorator.dlExpiry, decorator.sxDlNumber,
				decorator.sxDlExpiry, decorator.panNumber, decorator.cinNumber, decorator.gst, decorator.fassai);
		addAction();
		addCancelListener(this);
		addSubmitListener(new UpdateStoreListener(decorator.storeBinder, decorator.addressBinder, selectedStore.getId(), this, structure));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(decorator, selectedStore);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateStoreWindow other = (UpdateStoreWindow) obj;
		return Objects.equals(decorator, other.decorator) && Objects.equals(selectedStore, other.selectedStore);
	}

}
