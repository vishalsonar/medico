package com.sonar.vishal.ui.definition;

import com.sonar.vishal.ui.component.Component;
import com.vaadin.server.Page;

public interface Structure {

	public static final Component COMPONENT = Component.getInstance();
	public static final int BROWSER_WIDTH = Page.getCurrent().getBrowserWindowWidth() / 2;
	public static final int BROWSER_HEIGHT = Page.getCurrent().getBrowserWindowHeight() / 2;
	
	public Object get();
}
