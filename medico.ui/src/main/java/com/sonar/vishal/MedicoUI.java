package com.sonar.vishal;

import javax.servlet.annotation.WebServlet;

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
		getNavigatorUI().addView(UIConstant._ROLE, new RoleView());
		getNavigatorUI().addView(UIConstant._USER, new UserView());
		getNavigatorUI().addView(UIConstant._STORE, new StoreView());
		getNavigatorUI().addView(UIConstant._PRODUCT, new ProductView());
		getNavigatorUI().addView(UIConstant._PATIENT, new PatientView());
		getNavigatorUI().addView(UIConstant._CHANGE_PASSWORD, new ChangePasswordView());
		getNavigatorUI().navigateTo(UIConstant.EMPTY);
		getNavigatorUI().addViewChangeListener(new ChangeListener());
	}

	@WebServlet(urlPatterns = "/*", name = "MedicoUI", asyncSupported = true)
	@VaadinServletConfiguration(ui = MedicoUI.class, productionMode = false)
	public static class LoginServlet extends VaadinServlet {
		private static final long serialVersionUID = -6974150639907912054L;
	}
}
