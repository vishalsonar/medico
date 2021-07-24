package com.sonar.vishal.medico.common.util;

import com.sonar.vishal.medico.common.Hibernate;
import com.sonar.vishal.medico.common.message.common.Now;
import com.sonar.vishal.medico.common.pojo.Log;

public class Logger {

	private static final Hibernate HIBERNATE;
	private static final String TRACE = "TRACE";
	private static final String DEBUG = "DEBUG";
	private static final String INFO = "INFO";
	private static final String WARN = "WARN";
	private static final String ERROR = "ERROR";
	private static final String FATAL = "FATAL";
	private static String ip;
	private static String userId;
	private static String component;

	static {
		HIBERNATE = new Hibernate();
	}

	private Logger() {
		throw new IllegalStateException("Utility class");
	}

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		Logger.ip = ip;
	}

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		Logger.userId = userId;
	}

	public static String getComponent() {
		return component;
	}

	public static void setComponent(String component) {
		Logger.component = component;
	}

	private static Log getLog() {
		Log log = new Log();
		log.setComponent(component);
		log.setIp(ip);
		log.setDateTime(Now.get());
		log.setUserId(userId);
		return log;
	}

	public static void trace(String className, String message) {
		Log log = getLog();
		log.setMessage(message);
		log.setSeverity(TRACE);
		log.setClassName(className);
		HIBERNATE.saveOrUpdate(log);
	}

	public static void debug(String className, String message) {
		Log log = getLog();
		log.setMessage(message);
		log.setSeverity(DEBUG);
		log.setClassName(className);
		HIBERNATE.saveOrUpdate(log);
	}

	public static void info(String className, String message) {
		Log log = getLog();
		log.setMessage(message);
		log.setSeverity(INFO);
		log.setClassName(className);
		HIBERNATE.saveOrUpdate(log);
	}

	public static void warn(String className, String message) {
		Log log = getLog();
		log.setMessage(message);
		log.setSeverity(WARN);
		log.setClassName(className);
		HIBERNATE.saveOrUpdate(log);
	}

	public static void error(String className, String message) {
		Log log = getLog();
		log.setMessage(message);
		log.setSeverity(ERROR);
		log.setClassName(className);
		HIBERNATE.saveOrUpdate(log);
	}

	public static void fatal(String className, String message) {
		Log log = getLog();
		log.setMessage(message);
		log.setSeverity(FATAL);
		log.setClassName(className);
		HIBERNATE.saveOrUpdate(log);
	}
}
