package com.sonar.vishal;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.servlet.annotation.WebServlet;

import com.sonar.vishal.logui.component.LogUIConstant;
import com.sonar.vishal.medico.common.util.Logger;
import com.sonar.vishal.medico.common.util.LoggerMessage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("mytheme")
public class MedicoLOGUI extends UI {

	private static final long serialVersionUID = 3395530198486448892L;
	
	static {
		try {
			Logger.setComponent(LoggerMessage.LOGUI);
			Logger.setIp(Inet4Address.getLocalHost().getHostAddress());
			Logger.info(LoggerMessage.MEDICOLOGUI_CLASS_NAME, LoggerMessage.LOGUI_INITIALIZE);
		} catch (UnknownHostException e) {
			Logger.setIp(LoggerMessage.EMPTY);
			Logger.error(LoggerMessage.MEDICOLOGUI_CLASS_NAME, LoggerMessage.UNKOWN_HOST_EXCEPTION);
		}
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Navigator navigator = new Navigator(this, this);
		navigator.addView(LogUIConstant.EMPTY, new LogView());
		navigator.navigateTo(LogUIConstant.EMPTY);
	}

	@WebServlet(urlPatterns = "/*", name = "MedicoLOGUI", asyncSupported = true)
	@VaadinServletConfiguration(ui = MedicoLOGUI.class, productionMode = false)
	public static class LoginServlet extends VaadinServlet {
		private static final long serialVersionUID = -6974150639907912054L;
	}
}
