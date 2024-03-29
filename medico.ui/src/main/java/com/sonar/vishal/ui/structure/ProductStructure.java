package com.sonar.vishal.ui.structure;

import java.util.Optional;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.structure.ProductData;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.component.TablePagination;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.sonar.vishal.ui.window.product.AddProductWindow;
import com.sonar.vishal.ui.window.product.UpdateProductWindow;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class ProductStructure implements CRUDStructure {

	private VerticalLayout layout;
	private Grid<Product> table;
	private RestBackend backend;
	private Product selectedProduct;
	private Notification notification;
	private TablePagination<Product> productTablePagination;

	public ProductStructure() {
		layout = new VerticalLayout();
		productTablePagination = new TablePagination<>();
		table = new Grid<>();
		table.setSizeFull();
		table.setSelectionMode(SelectionMode.SINGLE);
		layout.addComponent(productTablePagination.init(table, UIConstant.FILTER_PRODUCT));
	}

	public Grid<Product> getTable() {
		return table;
	}

	public void setTable(Grid<Product> table) {
		this.table = table;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	@Override
	public Object get() {
		list();
		table.addColumn(Product::getId).setCaption(UIConstant.ID);
		table.addColumn(Product::getDescription).setCaption(UIConstant.DESCRIPTION);
		table.addColumn(Product::getPack).setCaption(UIConstant.PACK);
		table.addColumn(Product::getHsnCode).setCaption(UIConstant.HSN_CODE);
		table.addColumn(Product::getLsq).setCaption(UIConstant.LSQ);
		table.addColumn(Product::getQuantity).setCaption(UIConstant.QUANTITY);
		table.addColumn(Product::getBatchNumber).setCaption(UIConstant.BATCH_NUMBER);
		table.addColumn(Product::getExpiryDate).setCaption(UIConstant.EXPIRY_DATE);
		table.addColumn(Product::getMrp).setCaption(UIConstant.MRP);
		table.addColumn(Product::getRate).setCaption(UIConstant.RATE);
		table.addColumn(Product::getGst).setCaption(UIConstant.GST);
		table.addColumn(Product::getAmount).setCaption(UIConstant.AMOUNT);
		table.addSelectionListener(new SelectionListener<Product>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectionChange(SelectionEvent<Product> event) {
				try {
					Optional<Product> optionalProduct = event.getFirstSelectedItem();
					if (optionalProduct.isPresent()) {
						selectedProduct = optionalProduct.get();
					}
				} catch(Exception e) {
					LoggerApi.error(getClass().getName(), e.getMessage());
				}
			}
		});
		return layout;
	}

	@Override
	public void list() {
		backend = new RestBackend(Constant.GET_PRODUCT_LIST);
		JsonObject responseObject = (JsonObject) backend.doPostRespondData(Product[].class);
		long totalCount = responseObject.get(UIConstant.COUNT).getAsLong();
		Product[] data = GSON.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), Product[].class);
		productTablePagination.configurePagination(data, totalCount);
	}

	@Override
	public void add() {
		MedicoWindow window = new AddProductWindow(this);
		window.setWindow();
		UI.getCurrent().addWindow(window);
	}

	@Override
	public void update() {
		try {
			MedicoWindow window = new UpdateProductWindow(this, selectedProduct);
			window.setWindow();
			UI.getCurrent().addWindow(window);
			selectedProduct = null;
		} catch (Exception e) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.SELECT_ROW_TO_UPDATE);
			notification.show(Page.getCurrent());
		}	
	}

	@Override
	public void delete() {
		if (selectedProduct == null) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.SELECT_ROW_TO_DELETE);
		} else {
			ProductData data = new ProductData();
			data.setProduct(selectedProduct);
			backend = new RestBackend(Constant.DELETE_PRODUCT);
			Backend.message.setData(data);
			boolean response = backend.doPostRespondHeader();
			if (response) {
				notification = Component.getInstance().getSuccessNotification(Constant.SUCCESS, Constant.DELETE_PRODUCT_SUCESS_MESSAGE);
				list();
				selectedProduct = null;
			} else {
				notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.GENERAL_ERROR_MESSAGE);
			}
		}
		notification.show(Page.getCurrent());
	}

}
