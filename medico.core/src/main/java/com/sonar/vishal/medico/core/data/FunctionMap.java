package com.sonar.vishal.medico.core.data;

import java.util.HashMap;
import java.util.Map;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.core.definition.BusinessLogic;
import com.sonar.vishal.medico.core.logic.BillLogic;
import com.sonar.vishal.medico.core.logic.LogLogic;
import com.sonar.vishal.medico.core.logic.LoginLogic;
import com.sonar.vishal.medico.core.logic.NotificationLogic;
import com.sonar.vishal.medico.core.logic.PatientLogic;
import com.sonar.vishal.medico.core.logic.ProductLogic;
import com.sonar.vishal.medico.core.logic.RoleLogic;
import com.sonar.vishal.medico.core.logic.StoreLogic;
import com.sonar.vishal.medico.core.logic.UserLogic;

public class FunctionMap {

	private static final Map<String, BusinessLogic> LOGIC_MAP = new HashMap<>();

	static {
		udpateLogList();
		updateBillList();
		updateRoleList();
		updateUserList();
		updateStoreList();
		updatePatientList();
		updateProductList();
		updateNotificationList();
		LOGIC_MAP.put(Constant.LOGIN, new LoginLogic());
	}

	private static void updateNotificationList() {
		NotificationLogic logic = new NotificationLogic();
		LOGIC_MAP.put(Constant.ADD_NOTIFICATION, logic);
		LOGIC_MAP.put(Constant.UPDATE_NOTIFICATION, logic);
		LOGIC_MAP.put(Constant.DELETE_NOTIFICATION, logic);
		LOGIC_MAP.put(Constant.GET_NOTIFICATION, logic);
		LOGIC_MAP.put(Constant.GET_NOTIFICATION_LIST, logic);
		LOGIC_MAP.put(Constant.GET_NOTIFICATION_PAGE, logic);
		LOGIC_MAP.put(Constant.SEARCH_NOTIFICATION, logic);
	}

	private static void updateProductList() {
		ProductLogic logic = new ProductLogic();
		LOGIC_MAP.put(Constant.ADD_PRODUCT, logic);
		LOGIC_MAP.put(Constant.UPDATE_PRODUCT, logic);
		LOGIC_MAP.put(Constant.DELETE_PRODUCT, logic);
		LOGIC_MAP.put(Constant.GET_PRODUCT, logic);
		LOGIC_MAP.put(Constant.GET_PRODUCT_LIST, logic);
		LOGIC_MAP.put(Constant.GET_PRODUCT_PAGE, logic);
		LOGIC_MAP.put(Constant.SEARCH_PRODUCT, logic);
	}

	private static void updatePatientList() {
		PatientLogic logic = new PatientLogic();
		LOGIC_MAP.put(Constant.ADD_PATIENT, logic);
		LOGIC_MAP.put(Constant.UPDATE_PATIENT, logic);
		LOGIC_MAP.put(Constant.DELETE_PATIENT, logic);
		LOGIC_MAP.put(Constant.GET_PATIENT, logic);
		LOGIC_MAP.put(Constant.GET_PATIENT_LIST, logic);
		LOGIC_MAP.put(Constant.GET_PATIENT_PAGE, logic);
		LOGIC_MAP.put(Constant.SEARCH_PATIENT, logic);
	}

	private static void updateStoreList() {
		StoreLogic logic = new StoreLogic();
		LOGIC_MAP.put(Constant.ADD_STORE, logic);
		LOGIC_MAP.put(Constant.UPDATE_STORE, logic);
		LOGIC_MAP.put(Constant.DELETE_STORE, logic);
		LOGIC_MAP.put(Constant.GET_STORE, logic);
		LOGIC_MAP.put(Constant.GET_STORE_LIST, logic);
		LOGIC_MAP.put(Constant.GET_STORE_PAGE, logic);
		LOGIC_MAP.put(Constant.SEARCH_STORE, logic);
	}

	private static void updateUserList() {
		UserLogic logic = new UserLogic();
		LOGIC_MAP.put(Constant.ADD_USER, logic);
		LOGIC_MAP.put(Constant.UPDATE_USER, logic);
		LOGIC_MAP.put(Constant.DELETE_USER, logic);
		LOGIC_MAP.put(Constant.GET_USER, logic);
		LOGIC_MAP.put(Constant.GET_USER_LIST, logic);
		LOGIC_MAP.put(Constant.GET_USER_PAGE, logic);
		LOGIC_MAP.put(Constant.SEARCH_USER, logic);
	}

	private static void updateRoleList() {
		RoleLogic logic = new RoleLogic();
		LOGIC_MAP.put(Constant.ADD_ROLE, logic);
		LOGIC_MAP.put(Constant.UPDATE_ROLE, logic);
		LOGIC_MAP.put(Constant.DELETE_ROLE, logic);
		LOGIC_MAP.put(Constant.GET_ROLE, logic);
		LOGIC_MAP.put(Constant.GET_ROLE_LIST, logic);
		LOGIC_MAP.put(Constant.GET_ROLE_PAGE, logic);
		LOGIC_MAP.put(Constant.SEARCH_ROLE, logic);
	}

	private static void updateBillList() {
		BillLogic logic = new BillLogic();
		LOGIC_MAP.put(Constant.ADD_BILL, logic);
		LOGIC_MAP.put(Constant.UPDATE_BILL, logic);
		LOGIC_MAP.put(Constant.DELETE_BILL, logic);
		LOGIC_MAP.put(Constant.GET_BILL, logic);
		LOGIC_MAP.put(Constant.GET_BILL_LIST, logic);
		LOGIC_MAP.put(Constant.GET_BILL_PAGE, logic);
		LOGIC_MAP.put(Constant.SEARCH_BILL, logic);
	}

	private static void udpateLogList() {
		LogLogic logic = new LogLogic();
		LOGIC_MAP.put(Constant.ADD_LOG, logic);
		LOGIC_MAP.put(Constant.GET_LOG, logic);
		LOGIC_MAP.put(Constant.GET_LOG_LIST, logic);
		LOGIC_MAP.put(Constant.GET_LOG_PAGE, logic);
		LOGIC_MAP.put(Constant.SEARCH_LOG, logic);
	}

	public static BusinessLogic getLogic(String key) {
		return LOGIC_MAP.get(key);
	}

}
