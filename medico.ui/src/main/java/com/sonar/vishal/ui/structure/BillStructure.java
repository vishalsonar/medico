package com.sonar.vishal.ui.structure;

import java.util.Optional;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Bill;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.component.TablePagination;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.definition.GenerateStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;

public class BillStructure implements GenerateStructure, CRUDStructure {

	private VerticalLayout layout;
	private Grid<Bill> table;
	private RestBackend backend;
	private Bill selectedBill;
	private TablePagination<Bill> patientTablePagination;

	public BillStructure() {
		layout = new VerticalLayout();
		patientTablePagination = new TablePagination<>();
		table = new Grid<>();
		table.setSizeFull();
		table.setSelectionMode(SelectionMode.SINGLE);
		layout.addComponent(patientTablePagination.init(table, UIConstant.FILTER_BILL));
	}

	@Override
	public Object get() {
		list();
		table.addColumn(Bill::getId).setCaption(UIConstant.ID);
		table.addColumn(Bill::getBillNumber).setCaption(UIConstant.BILL_NUMBER);
		table.addColumn(Bill::displayStoreName).setCaption(UIConstant.STORE_NAME);
		table.addColumn(Bill::displayPatientName).setCaption(UIConstant.PATIENT_NAME);
		table.addSelectionListener(new SelectionListener<Bill>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectionChange(SelectionEvent<Bill> event) {
				try {
					Optional<Bill> optionalBill = event.getFirstSelectedItem();
					if (optionalBill.isPresent()) {
						selectedBill = optionalBill.get();
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
		backend = new RestBackend(Constant.GET_BILL_LIST);
		JsonObject responseObject = (JsonObject) backend.doPostRespondData(Bill[].class);
		long totalCount = responseObject.get(UIConstant.COUNT).getAsLong();
		Bill[] data = GSON.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Bill[].class);
		patientTablePagination.configurePagination(data, totalCount);
	}

	@Override
	public void generate() {
		if (selectedBill != null) {
			
		}
	}

	@Override
	public void add() {
		throw new IllegalAccessError();
	}

	@Override
	public void update() {
		throw new IllegalAccessError();
	}

	@Override
	public void delete() {
		throw new IllegalAccessError();
	}

}
