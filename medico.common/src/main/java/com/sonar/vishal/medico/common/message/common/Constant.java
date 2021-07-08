package com.sonar.vishal.medico.common.message.common;

public class Constant {

	private Constant() {
		throw new IllegalStateException("Utility class");
	}

	public static final String SUCCESS = "Success";
	public static final String ERROR = "Error";
	public static final String ADDRESS = "address";
	public static final String DATA = "Data";
	public static final String EXCEPTION = "Exception";
	public static final String ROLE = "role";
	public static final String LOGIN = "Login";
	public static final String REQUEST = "Request";
	public static final String RESPONSE = "Response";
	public static final String NULL = "null";
	public static final String PATIENT = "patient";
	public static final String STORE = "store";
	public static final String MAC = "MAC";
	public static final String HEADER = "Header";
	public static final String LIST = "List";

	public static final String GET_STORE_LIST = "GetStoreList";
	public static final String GET_STORE = "GetStore";
	public static final String ADD_STORE = "AddStore";
	public static final String UPDATE_STORE = "UpdateStore";
	public static final String DELETE_STORE = "DeleteStore";

	public static final String GET_PRODUCT_LIST = "GetProductList";
	public static final String GET_PRODUCT = "GetProduct";
	public static final String ADD_PRODUCT = "AddProduct";
	public static final String UPDATE_PRODUCT = "UpdateProduct";
	public static final String DELETE_PRODUCT = "DeleteProduct";

	public static final String GET_BILL_LIST = "GetBillList";
	public static final String GET_BILL = "GetBill";
	public static final String ADD_BILL = "AddBill";
	public static final String UPDATE_BILL = "UpdateBill";
	public static final String DELETE_BILL = "DeleteBill";

	public static final String GET_USER_LIST = "GetUserList";
	public static final String GET_USER = "GetUser";
	public static final String ADD_USER = "AddUser";
	public static final String UPDATE_USER = "UpdateUser";
	public static final String DELETE_USER = "DeleteUser";

	public static final String GET_ROLE_LIST = "GetRoleList";
	public static final String GET_ROLE = "GetRole";
	public static final String ADD_ROLE = "AddRole";
	public static final String UPDATE_ROLE = "UpdateRole";
	public static final String DELETE_ROLE = "DeleteRole";

	public static final String GET_PATIENT_LIST = "GetPatientList";
	public static final String GET_PATIENT = "GetPatient";
	public static final String ADD_PATIENT = "AddPatient";
	public static final String UPDATE_PATIENT = "UpdatePatient";
	public static final String DELETE_PATIENT = "DeletePatient";
	
	public static final String GET_KEY = "GetKey";

	public static final String URL = "http://localhost:8080/medico.core/rest";
	public static final String KEY_URL = URL + "/api/process/key";
	public static final String REQUEST_URL = URL + "/api/process/request";
	public static final String APPLICATION_JSON = "application/json";

	public static final String LOGIN_FAILURE_MESSAGE = "Invalid Credentials";
	public static final String GENERAL_ERROR_MESSAGE = "Operation Failed";
	public static final String VALIDATION_EXCEPTION = "Please Enter Required Feilds";
	public static final String SELECT_ROW_TO_UPDATE = "Please Select row to Update";
	public static final String SELECT_ROW_TO_DELETE = "Please Select row to Delete";

	public static final String ADD_ROLE_SUCCESS_MESSAGE = "Role Added";
	public static final String UPDATE_ROLE_SUCCESS_MESSAGE = "Role Updated";
	public static final String DELETE_ROLE_SUCESS_MESSAGE = "Role Deleted";
	
	public static final String ADD_USER_SUCCESS_MESSAGE = "User Added";
	public static final String UPDATE_USER_SUCCESS_MESSAGE = "User Updated";
	public static final String DELETE_USER_SUCESS_MESSAGE = "User Deleted";
	
	public static final String ADD_STORE_SUCCESS_MESSAGE = "Store Added";
	public static final String UPDATE_STORE_SUCCESS_MESSAGE = "Store Updated";
	public static final String DELETE_STORE_SUCESS_MESSAGE = "Store Deleted";
	
	public static final String ADD_PRODUCT_SUCCESS_MESSAGE = "Product Added";
	public static final String UPDATE_PRODUCT_SUCCESS_MESSAGE = "Product Updated";
	public static final String DELETE_PRODUCT_SUCESS_MESSAGE = "Product Deleted";
	
	public static final String ADD_PATIENT_SUCCESS_MESSAGE = "Patient Added";
	public static final String UPDATE_PATIENT_SUCCESS_MESSAGE = "Patient Updated";
	public static final String DELETE_PATIENT_SUCCESS_MESSAGE = "Patient Deleted";
}
