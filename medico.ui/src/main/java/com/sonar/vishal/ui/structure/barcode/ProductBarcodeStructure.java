package com.sonar.vishal.ui.structure.barcode;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.definition.GenerateStructure;
import com.sonar.vishal.ui.structure.ProductStructure;
import com.sonar.vishal.ui.util.BarcodeUtil;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class ProductBarcodeStructure extends ProductStructure implements GenerateStructure {

	private VerticalLayout windowLayout;
	private Window window;

	public ProductBarcodeStructure() {
		window = new Window();
		windowLayout = new VerticalLayout();
		window.setContent(windowLayout);
		window.setSizeFull();
	}

	@Override
	public void generate() {
		Notification notification;
		if (getSelectedProduct() == null) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, UIConstant.PLEASE_SELECT_ROW_GENERATE);
			notification.show(Page.getCurrent());
			return;
		}
		String message = String.valueOf(getSelectedProduct().getId()) + UIConstant.QR_SEPERATOR + getSelectedProduct().getDescription();
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

	private void setupWindow(String imageUrl) {
		ExternalResource imageSource = new ExternalResource(imageUrl);
		String barcodeDesc = UIConstant.MEDICO + UIConstant.BARCODE_SEPERATOR + getSelectedProduct().getDescription();
		Image image = new Image(barcodeDesc, imageSource);
		windowLayout.addComponents(image);
		windowLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		window.addCloseListener(new CloseListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent e) {
				windowLayout.removeAllComponents();
				getTable().deselectAll();
				setSelectedProduct(null);
			}
		});
	}

}
