package com.sonar.vishal;

import javax.servlet.annotation.WebServlet;

import com.sonar.vishal.ui.listener.ChangeListener;
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
		getNavigatorUI().addView("", new LoginView());
		getNavigatorUI().addView("optionpage", new OptionView());
		getNavigatorUI().addView("role", new RoleView());
		getNavigatorUI().addView("user", new UserView());
		getNavigatorUI().addView("store", new StoreView());
		getNavigatorUI().navigateTo("");
		getNavigatorUI().addViewChangeListener(new ChangeListener());
	}

	@WebServlet(urlPatterns = "/*", name = "MedicoUI", asyncSupported = true)
	@VaadinServletConfiguration(ui = MedicoUI.class, productionMode = false)
	public static class LoginServlet extends VaadinServlet {
		private static final long serialVersionUID = -6974150639907912054L;
	}
}
