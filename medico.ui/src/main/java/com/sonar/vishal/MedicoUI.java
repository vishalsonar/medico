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
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

@Theme("mytheme")
public class MedicoUI extends UI {

	private static final long serialVersionUID = -112040433551458450L;

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

	public static synchronized Navigator getNavigatorUI() {
		Object navigator = VaadinSession.getCurrent().getSession().getAttribute(UIConstant.NAVIGATOR);
		if (navigator instanceof Navigator) {
			return (Navigator) navigator;
		}
		return null;
	}

	public static synchronized void setNavigatorUI(Navigator navigator) {
		if (navigator != null) {
			VaadinSession.getCurrent().getSession().setAttribute(UIConstant.NAVIGATOR, navigator);
		}
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		if (getNavigatorUI() == null) {
			Navigator navigator = new Navigator(this, this);
			navigator.addView(UIConstant.EMPTY, new LoginView());
			navigator.addView(UIConstant.OPTION_PAGE, new OptionView());
			navigator.addView(UIConstant.S_ROLE, new RoleView());
			navigator.addView(UIConstant.S_USER, new UserView());
			navigator.addView(UIConstant.S_STORE, new StoreView());
			navigator.addView(UIConstant.S_PRODUCT, new ProductView());
			navigator.addView(UIConstant.S_PATIENT, new PatientView());
			navigator.addView(UIConstant.S_CHANGE_PASSWORD, new ChangePasswordView());
			navigator.addView(UIConstant.S_BARCODE, new BarcodeView());
			navigator.addView(UIConstant.S_BILL, new BillView());
			navigator.addView(UIConstant.S_ACCESS, new UserAccessView());
			navigator.addView(UIConstant.S_FORGOT_PASSWORD_URL, new ForgotPasswordView());
			navigator.addView(UIConstant.S_NOTIFICATION, new NotificationView());
			navigator.addViewChangeListener(new ChangeListener());
			setNavigatorUI(navigator);
		}
		getNavigatorUI().navigateTo(UIConstant.EMPTY);
	}

	@WebServlet(urlPatterns = "/*", name = "MedicoUI", asyncSupported = true)
	@VaadinServletConfiguration(ui = MedicoUI.class, productionMode = true)
	public static class LoginServlet extends VaadinServlet {
		private static final long serialVersionUID = -6974150639907912054L;
	}
}
