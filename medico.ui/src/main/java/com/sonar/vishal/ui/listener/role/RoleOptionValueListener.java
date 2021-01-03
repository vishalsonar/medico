package com.sonar.vishal.ui.listener.role;

import java.util.Set;

import com.mysql.cj.util.StringUtils;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;

public class RoleOptionValueListener implements ValueChangeListener<Set<String>> {

	private static final long serialVersionUID = 1594109095821703650L;
	private static String selectedOption;

	public static String getSelectedOption() {
		return selectedOption;
	}

	public static void setSelectedOption(String selectedOption) {
		if (!StringUtils.isEmptyOrWhitespaceOnly(selectedOption)) {
			RoleOptionValueListener.selectedOption = selectedOption;
		}
	}

	@Override
	public void valueChange(ValueChangeEvent<Set<String>> event) {
		setSelectedOption(String.join(",", event.getValue()));
	}

}
