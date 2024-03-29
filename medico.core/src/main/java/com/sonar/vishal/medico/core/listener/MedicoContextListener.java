package com.sonar.vishal.medico.core.listener;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.sonar.vishal.medico.common.util.DataBaseInitializer;
import com.sonar.vishal.medico.common.util.Logger;
import com.sonar.vishal.medico.common.util.LoggerMessage;

@WebListener
public class MedicoContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			Logger.setComponent(LoggerMessage.CORE);
			Logger.setIp(InetAddress.getLocalHost().getHostAddress());
			Logger.info(getClass().getName(), LoggerMessage.SERVER_INITIALIZE);
			DataBaseInitializer.insertSuperUser();
		} catch (UnknownHostException e) {
			Logger.setIp(LoggerMessage.EMPTY);
			Logger.error(getClass().getName(), LoggerMessage.UNKOWN_HOST_EXCEPTION);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		Logger.info(getClass().getName(), LoggerMessage.SERVER_STOPPED);
	}
}
