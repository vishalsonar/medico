package com.sonar.vishal.ui.structure.barcode;

import java.util.Optional;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Product;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.component.TablePagination;
import com.sonar.vishal.ui.definition.GenerateStructure;
import com.sonar.vishal.ui.util.BarcodeUtil;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class ProductBarcodeStructure implements GenerateStructure {

	private VerticalLayout layout;
	private VerticalLayout windowLayout;
	private Grid<Product> table;
	private RestBackend backend;
	private Window window;
	private Product selectedProduct;
	private Notification notification;
	private TablePagination<Product> productTablePagination;

	public ProductBarcodeStructure() {
		window = new Window();
		layout = new VerticalLayout();
		windowLayout = new VerticalLayout();
		productTablePagination = new TablePagination<>();
		table = new Grid<>();
		table.setSizeFull();
		table.setSelectionMode(SelectionMode.SINGLE);
		layout.addComponent(productTablePagination.init(table, UIConstant.FILTER_PRODUCT));
		window.setContent(windowLayout);
		window.setSizeFull();
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
				} catch (Exception e) {
					LoggerApi.error(getClass().getName(), e.getMessage());
				}
			}
		});
		return layout;
	}

	@Override
	public void generate() {
		if (selectedProduct == null) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, UIConstant.PLEASE_SELECT_ROW_GENERATE);
			notification.show(Page.getCurrent());
			return;
		}
		String message = String.valueOf(selectedProduct.getId()) + UIConstant.QR_SEPERATOR + selectedProduct.getDescription();
		String imageUrl = BarcodeUtil.generateBarcode(message, 300, 300);
		if (imageUrl != null) {
			imageUrl = BarcodeUtil.IMAGE_INDEX_URL + imageUrl;
			setupWindow(imageUrl);
			UI.getCurrent().addWindow(window);
		} else {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, UIConstant.ERROR_GENERATING_BARCODE);
			notification.show(Page.getCurrent());
		}
	}

	@Override
	public void list() {
		backend = new RestBackend(Constant.GET_PRODUCT_LIST);
		Product[] data = (Product[]) backend.doPostRespondData(Product[].class);
		productTablePagination.configurePagination(data);
	}
	
	private void setupWindow(String imageUrl) {
		ExternalResource imageSource = new ExternalResource(imageUrl);
		String barcodeDesc = UIConstant.MEDICO + UIConstant.BARCODE_SEPERATOR + selectedProduct.getDescription();
		Image image = new Image(barcodeDesc, imageSource);
		windowLayout.addComponents(image);
		windowLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		window.addCloseListener(new CloseListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent e) {
				windowLayout.removeAllComponents();
				table.deselectAll();
				selectedProduct = null;
			}
		});
	}

}
