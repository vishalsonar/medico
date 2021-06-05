package com.sonar.vishal.ui.window.store;

import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.ui.listener.store.AddStoreListener;
import com.sonar.vishal.ui.structure.StoreStructure;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

public class AddStoreWindow extends MedicoWindow {

	private static final long serialVersionUID = 3946232465891622186L;
	private Binder<Store> storeBinder = new Binder<>();
	private Binder<Address> addressBinder = new Binder<>();

	public AddStoreWindow(StoreStructure structure) {
		super("Add Store", structure);
		this.structure = structure;
	}

	@Override
	public void setWindow() {
		TextField name = COMPONENT.getTextField("Store Name", "Store Name", "300");
		TextField line1 = COMPONENT.getTextField("Address Line 1", "Line 1", "300");
		TextField line2 = COMPONENT.getTextField("Address Line 2", "Line 2", "300");
		TextField city = COMPONENT.getTextField("City", "City", "300");
		TextField pinCode = COMPONENT.getTextField("Pin Code", "Pin Code", "300");
		TextField state = COMPONENT.getTextField("State", "State", "300");
		TextField phoneNumber = COMPONENT.getTextField("Phone Number", "Phone Number", "300");
		TextField dlNumber = COMPONENT.getTextField("DL Number", "DL Number", "300");
		TextField dlExpiry = COMPONENT.getTextField("DL Expiry", "DL Expiry", "300");
		TextField sxDlNumber = COMPONENT.getTextField("Sx DL Number", "Sx DL Number", "300");
		TextField sxDlExpiry = COMPONENT.getTextField("Sx DL Expiry", "Sx DL Expiry", "300");
		TextField panNumber = COMPONENT.getTextField("Pan Number", "Pan Number", "300");
		TextField cinNumber = COMPONENT.getTextField("Cin Number", "Cin Number", "300");
		TextField gst = COMPONENT.getTextField("GST", "GST", "300");
		TextField fassai = COMPONENT.getTextField("FASSAI", "FASSAI", "300");
		storeBinder.bind(name, Store::getName, Store::setName);
		addressBinder.bind(line1, Address::getLine1, Address::setLine1);
		addressBinder.bind(line2, Address::getLine2, Address::setLine2);
		addressBinder.bind(city, Address::getCity, Address::setCity);
		addressBinder.bind(pinCode, Address::getPinCode, Address::setPinCode);
		addressBinder.bind(state, Address::getState, Address::setState);
		storeBinder.bind(phoneNumber, Store::getPhoneNumber, Store::setPhoneNumber);
		storeBinder.bind(dlNumber, Store::getDlNumber, Store::setDlNumber);
		storeBinder.bind(dlExpiry, Store::getDlExpiry, Store::setDlExpiry);
		storeBinder.bind(sxDlNumber, Store::getSxDlNumber, Store::setSxDlNumber);
		storeBinder.bind(sxDlExpiry, Store::getSxDlExpiry, Store::setSxDlExpiry);
		storeBinder.bind(panNumber, Store::getPanNumber, Store::setPanNumber);
		storeBinder.bind(cinNumber, Store::getCinNumber, Store::setCinNumber);
		storeBinder.bind(gst, Store::getGstin, Store::setGstin);
		storeBinder.bind(fassai, Store::getFassaiNumber, Store::setFassaiNumber);
		addComponents(name, line1, line2, city, pinCode, state, phoneNumber, dlNumber, dlExpiry, sxDlNumber, sxDlExpiry,
				panNumber, cinNumber, gst, fassai);
		addAction();
		addCancelListener(this);
		addSubmitListener(new AddStoreListener(storeBinder, addressBinder, this, structure));
	}

}
