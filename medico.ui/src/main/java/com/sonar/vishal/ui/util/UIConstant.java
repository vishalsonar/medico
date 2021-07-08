package com.sonar.vishal.ui.util;

public class UIConstant {

	private UIConstant() {
		throw new IllegalStateException("Constant class");
	}

	public static final int PASSWORD_MIN_LENGTH = 8;
	public static final int TWENTY = 20;

	public static final String EMPTY = "";
	public static final String COMMA = ",";
	public static final String REQUIRED_MANDATORY_FIELD = "Required Field is Empty";
	public static final String INVALID_PASSWORD = "Invalid Password";
	public static final String PASSWORD_MISMATCH = "Password Mismatch";
	public static final String INVALID_REQUIRED_FIELD = "Invalid Require Field or Length Exceed";
	public static final String SHORT_PASSWORD_LENGTH = "Password length should be greater than " + PASSWORD_MIN_LENGTH + " digit";

	public static final String ADD_USER = "Add User";
	public static final String UPDATE_USER = "Update User";
	public static final String ADD_ROLE = "Add Role";
	public static final String UPDATE_ROLE = "Update Role";
	public static final String ADD_PATIENT = "Add Patient";
	public static final String UPDATE_PATIENT = "Update Patient";
	public static final String ADD_PRODUCT = "Add Product";
	public static final String UPDATE_PRODUCT = "Update Product";
	public static final String ADD_STORE = "Add Store";
	public static final String UPDATE_STORE = "Update Store";

	public static final String FIELD_LENGTH_300 = "300";
	public static final String NAME = "Name";
	public static final String USER_NAME = "User Name";
	public static final String PASSWORD = "Password";
	public static final String CONFIRM_PASSWORD = "Confirm Password";
	public static final String ROLE_OPTIONS = "Role Options";
	public static final String OPTIONS = "Options";
	public static final String ROLE_NAME = "Role Name";

	public static final String STORE_NAME = "Store Name";
	public static final String ADDRESS_LINE_1 = "Address Line 1";
	public static final String ADDRESS_LINE_2 = "Address Line 2";
	public static final String CITY = "City";
	public static final String PIN_CODE = "Pin Code";
	public static final String STATE = "State";
	public static final String PHONE_NUMBER = "Phone Number";
	public static final String DL_NUMBER = "DL Number";
	public static final String DL_EXPIRY = "DL Expiry";
	public static final String SX_DL_NUMBER = "SX DL Number";
	public static final String SX_DL_EXPIRY = "SX DL Expiry";
	public static final String PAN_NUMBER = "Pan Number";
	public static final String CIN_NUMBER = "Cin Number";
	public static final String GST = "GST";
	public static final String FASSAI = "FASSAI";

	public static final String DESCRIPTION = "Description";
	public static final String PACK = "Pack";
	public static final String HSN_CODE = "HSN Code";
	public static final String LSQ = "LSQ";
	public static final String QUANTITY = "Quantity";
	public static final String BATCH_NUMBER = "Batch Number";
	public static final String EXPIRY_DATE = "Expiry Date";
	public static final String MRP = "MRP";
	public static final String RATE = "Rate";
	public static final String AMOUNT = "Amount";
	
	public static final String DOCTOR_NAME = "Doctor Name";
	public static final String PATTIENT_NAME = "Patient Name";
	
	public static final String USER_SALT = "User Salt";
	public static final String STORE_SALT = "Store Salt";
	public static final String ROLE_SALT = "Role Salt";
	public static final String PRODUCT_SALT = "Product Salt";
	public static final String PATIENT_SALT = "Patient Salt";
}
