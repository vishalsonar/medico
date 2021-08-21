package com.sonar.vishal;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.annotation.WebServlet;

import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.medico.common.util.LoggerMessage;
import com.sonar.vishal.ui.listener.ChangeListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("mytheme")
public class MedicoUI extends UI {

	private static final long serialVersionUID = -112040433551458450L;
	private static Navigator navigator;
	
	static {
		try {
			LoggerApi.setComponent(LoggerMessage.MEDICOUI);
			LoggerApi.setIp(InetAddress.getLocalHost().getHostAddress());
			LoggerApi.info(LoggerMessage.MEDICOUI_CLASS_NAME, LoggerMessage.MEDICOUI_INITIALIZE);
		} catch (UnknownHostException e) {
			LoggerApi.setIp(LoggerMessage.EMPTY);
			LoggerApi.error(LoggerMessage.MEDICOLOGUI_CLASS_NAME, LoggerMessage.UNKOWN_HOST_EXCEPTION);
		}
	}

	public static Navigator getNavigatorUI() {
		return navigator;
	}

	public static void setNavigatorUI(Navigator navigator) {
		if (navigator != null) {
			MedicoUI.navigator = navigator;
		}
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		setNavigatorUI(new Navigator(this, this));
		getNavigatorUI().addView(UIConstant.EMPTY, new LoginView());
		getNavigatorUI().addView(UIConstant.OPTION_PAGE, new OptionView());
		getNavigatorUI().addView(UIConstant.S_ROLE, new RoleView());
		getNavigatorUI().addView(UIConstant.S_USER, new UserView());
		getNavigatorUI().addView(UIConstant.S_STORE, new StoreView());
		getNavigatorUI().addView(UIConstant.S_PRODUCT, new ProductView());
		getNavigatorUI().addView(UIConstant.S_PATIENT, new PatientView());
		getNavigatorUI().addView(UIConstant.S_CHANGE_PASSWORD, new ChangePasswordView());
		getNavigatorUI().addView(UIConstant.S_BARCODE, new BarcodeView());
		getNavigatorUI().addView(UIConstant.S_BILL, new BillView());
		getNavigatorUI().navigateTo(UIConstant.EMPTY);
		getNavigatorUI().addViewChangeListener(new ChangeListener());
	}

	@WebServlet(urlPatterns = "/*", name = "MedicoUI", asyncSupported = true)
	@VaadinServletConfiguration(ui = MedicoUI.class, productionMode = false)
	public static class LoginServlet extends VaadinServlet {
		private static final long serialVersionUID = -6974150639907912054L;
	}
}
