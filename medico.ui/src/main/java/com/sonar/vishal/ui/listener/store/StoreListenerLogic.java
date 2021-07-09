package com.sonar.vishal.ui.listener.store;

import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.StoreData;
import com.sonar.vishal.ui.definition.BiListenerLogic;
import com.vaadin.data.Binder;

public class StoreListenerLogic implements BiListenerLogic<Binder<Store>, Binder<Address>> {

	@Override
	public Data process(Binder<Store> parentBinder, Binder<Address> childBinder, Integer id) throws Exception {
		Store store = new Store();
		Address address = new Address();
		StoreData data = new StoreData();
		parentBinder.writeBean(store);
		childBinder.writeBean(address);
		store.setAddress(address);
		if (id != null) {
			store.setId(id);
		}
		data.setStore(store);
		return data;
	}

}
