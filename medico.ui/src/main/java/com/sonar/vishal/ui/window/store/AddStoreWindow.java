package com.sonar.vishal.ui.window.store;

import com.sonar.vishal.ui.listener.store.AddStoreListener;
import com.sonar.vishal.ui.structure.StoreStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;

public class AddStoreWindow extends MedicoWindow {

	private static final long serialVersionUID = 3946232465891622186L;
	private transient StoreWindowDecorator decorator;

	public AddStoreWindow(StoreStructure structure) {
		super(UIConstant.ADD_STORE, structure);
		decorator = new StoreWindowDecorator();
	}

	@Override
	public void setWindow() {
		addComponents(decorator.name, decorator.line1, decorator.line2, decorator.city, decorator.pinCode,
				decorator.state, decorator.phoneNumber, decorator.dlNumber, decorator.dlExpiry, decorator.sxDlNumber,
				decorator.sxDlExpiry, decorator.panNumber, decorator.cinNumber, decorator.gst, decorator.fassai);
		addAction();
		addCancelListener(this);
		addSubmitListener(new AddStoreListener(decorator.storeBinder, decorator.addressBinder, this, structure));
	}

}
