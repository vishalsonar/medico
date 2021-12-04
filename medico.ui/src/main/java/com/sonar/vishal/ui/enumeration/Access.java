package com.sonar.vishal.ui.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.icons.VaadinIcons;

public enum Access {

	LOGIN(VaadinIcons.ARROWS_LONG_RIGHT), BILL(VaadinIcons.ALIGN_JUSTIFY), BARCODE(VaadinIcons.BARCODE),
	PRODUCT(VaadinIcons.CLIPBOARD_CROSS), PATIENT(VaadinIcons.USER_HEART), STORE(VaadinIcons.SHOP),
	USER(VaadinIcons.USER), ROLE(VaadinIcons.TASKS), ACCESS(VaadinIcons.USER_CHECK), NOTIFICATION(VaadinIcons.LIST_SELECT);

	private final VaadinIcons icon;

	Access(VaadinIcons icon) {
		this.icon = icon;
	}

	public VaadinIcons getIcon() {
		return this.icon;
	}

	public static VaadinIcons getIcon(String accessString) {
		VaadinIcons icon;
		try {
			icon = Access.valueOf(accessString.toUpperCase()).getIcon();
		} catch (IllegalArgumentException e) {
			icon = VaadinIcons.ARROWS_CROSS;
		}
		return icon;
	}

	public static boolean contains(String accessString) {
		boolean state = true;
		try {
			Access.valueOf(accessString.toUpperCase());
		} catch (IllegalArgumentException e) {
			state = false;
		}
		return state;
	}

	public static boolean isLoginAllow(String accessString) {
		List<String> accessList = Arrays.asList(accessString.split(UIConstant.COMMA));
		return accessList.stream().anyMatch(item -> item.equals(UIConstant.LOGIN.toUpperCase()));
	}

	public static String[] getAsStringArray() {
		return Arrays.stream(Access.values()).map(Enum::name).collect(Collectors.toList()).toArray(new String[8]);
	}
}
