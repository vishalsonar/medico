package com.sonar.vishal.medico.common.util;

import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Now;
import com.sonar.vishal.medico.common.pojo.Log;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.structure.KeyData;
import com.sonar.vishal.medico.common.structure.LogData;

public class LoggerApi {

	private static final String TRACE = "TRACE";
	private static final String DEBUG = "DEBUG";
	private static final String INFO = "INFO";
	private static final String WARN = "WARN";
	private static final String ERROR = "ERROR";
	private static final String FATAL = "FATAL";
	private static String ip;
	private static String userId;
	private static String component;
	private static RestBackend backend;
	private static Key key;

	static {
		RestBackend backend = new RestBackend(Constant.GET_KEY);
		KeyData keyData = (KeyData) backend.doPostRespondData(KeyData.class);
		byte[] decodedKey = Base64.getDecoder().decode(keyData.getKey());
		key = new SecretKeySpec(decodedKey, 0, decodedKey.length, Constant.AES);
	}

	private LoggerApi() {
		throw new IllegalStateException("Utility class");
	}

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		LoggerApi.ip = ip;
	}

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		LoggerApi.userId = userId;
	}

	public static String getComponent() {
		return component;
	}

	public static void setComponent(String component) {
		LoggerApi.component = component;
	}

	private static LogData getLogData() {
		Log log = new Log();
		LogData logData = new LogData();
		log.setComponent(component);
		log.setIp(ip);
		log.setDateTime(Now.get());
		log.setUserId(userId);
		logData.setLog(log);
		return logData;
	}

	public static void trace(String className, String message) {
		backend = new RestBackend(Constant.ADD_LOG);
		backend.setKey(key);
		LogData logData = getLogData();
		logData.getLog().setMessage(message);
		logData.getLog().setSeverity(TRACE);
		logData.getLog().setClassName(className);
		Backend.message.setData(logData);
		backend.doPostRespondHeader();
	}

	public static void debug(String className, String message) {
		backend = new RestBackend(Constant.ADD_LOG);
		backend.setKey(key);
		LogData logData = getLogData();
		logData.getLog().setMessage(message);
		logData.getLog().setSeverity(DEBUG);
		logData.getLog().setClassName(className);
		Backend.message.setData(logData);
		backend.doPostRespondHeader();
	}

	public static void info(String className, String message) {
		backend = new RestBackend(Constant.ADD_LOG);
		backend.setKey(key);
		LogData logData = getLogData();
		logData.getLog().setMessage(message);
		logData.getLog().setSeverity(INFO);
		logData.getLog().setClassName(className);
		Backend.message.setData(logData);
		backend.doPostRespondHeader();
	}

	public static void warn(String className, String message) {
		backend = new RestBackend(Constant.ADD_LOG);
		backend.setKey(key);
		LogData logData = getLogData();
		logData.getLog().setMessage(message);
		logData.getLog().setSeverity(WARN);
		logData.getLog().setClassName(className);
		Backend.message.setData(logData);
		backend.doPostRespondHeader();
	}

	public static void error(String className, String message) {
		backend = new RestBackend(Constant.ADD_LOG);
		backend.setKey(key);
		LogData logData = getLogData();
		logData.getLog().setMessage(message);
		logData.getLog().setSeverity(ERROR);
		logData.getLog().setClassName(className);
		Backend.message.setData(logData);
		backend.doPostRespondHeader();
	}

	public static void fatal(String className, String message) {
		backend = new RestBackend(Constant.ADD_LOG);
		backend.setKey(key);
		LogData logData = getLogData();
		logData.getLog().setMessage(message);
		logData.getLog().setSeverity(FATAL);
		logData.getLog().setClassName(className);
		Backend.message.setData(logData);
		backend.doPostRespondHeader();
	}

}
