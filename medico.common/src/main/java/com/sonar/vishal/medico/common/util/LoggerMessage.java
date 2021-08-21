package com.sonar.vishal.medico.common.util;

public class LoggerMessage {

	private LoggerMessage() {
		throw new IllegalStateException("Constant class");
	}

	public static final String EMPTY = "";
	public static final String CORE = "CORE";
	public static final String LOGUI = "LOGUI";
	public static final String MEDICOUI = "MEDICOUI";
	
	public static final String SECURITY_CLASS_NAME = "com.sonar.vishal.medico.common.security.Security";
	public static final String MEDICOLOGUI_CLASS_NAME = "com.sonar.vishal.MedicoLOGUI";
	public static final String MEDICOUI_CLASS_NAME = "com.sonar.vishal.MedicoUI";
	public static final String BARCODE_UTIL_CLASS_NAME = "com.sonar.vishal.ui.util.BarcodeUtil";
	public static final String HASHING_CLASS_NAME = "com.sonar.vishal.medico.common.util.Hashing";

	public static final String SERVER_INITIALIZE = "Core Server Initialized";
	public static final String SERVER_STOPPED = "Core Server Stopped";
	public static final String LOGUI_INITIALIZE = "LogUI Initialized";
	public static final String LOGUI_STOPPED = "LogUI Stopped";
	public static final String MEDICOUI_INITIALIZE = "MedicoUI Initialized";
	public static final String MEDICOUI_STOPPED = "MedicoUI Stopped";
	public static final String UNKOWN_HOST_EXCEPTION = "UnknownHostException, could not found IP";

	public static final String REQUEST_RECEIVED = "Request Recevied :: ";
	public static final String RESPONSE_GENERATED = "Response Generated :: ";
}
