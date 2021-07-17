package com.sonar.vishal;

import java.util.List;
import java.util.Optional;

import com.sonar.vishal.converter.DateTimeToStringConverter;
import com.sonar.vishal.logui.component.Component;
import com.sonar.vishal.logui.component.LogUIConstant;
import com.sonar.vishal.logui.listener.SubmitListener;
import com.sonar.vishal.logui.logic.LogLogic;
import com.sonar.vishal.medico.common.pojo.Log;
import com.sonar.vishal.medico.common.structure.LogData;
import com.vaadin.data.Binder;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LogView extends HorizontalSplitPanel implements View {

	private static final long serialVersionUID = -6960712018063674712L;
	private Component component;
	private VerticalLayout leftLayout;
	private VerticalLayout rightLayout;
	private Grid<Log> table;
	private Log selectedLog;
	private LogLogic logLogic;
	private Binder<Log> logBinder;
	private Binder<LogData> logDataBinder;

	public LogView() {
		component = new Component();
		leftLayout = new VerticalLayout();
		rightLayout = new VerticalLayout();
		table = new Grid<Log>();
		logLogic = new LogLogic();
		logBinder = new Binder<Log>();
		logDataBinder = new Binder<LogData>();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		setLeftUI();
		setupRightUI();
		this.addComponents(leftLayout, rightLayout);
		this.setSplitPosition(20, Unit.PERCENTAGE);
		this.setLocked(true);
		UI.getCurrent().setContent(this);
	}

	private void setLeftUI() {
		Button submit = component.getFriendlyButton(LogUIConstant.SUBMIT);
		ComboBox<String> componentComboBox = component.getDropDown(LogUIConstant.SELECT_COMPONENT, LogUIConstant.SELECT_COMPONENT, LogUIConstant.COMPONENT_LIST);
		ComboBox<String> severityComboBox = component.getDropDown(LogUIConstant.SELECT_SEVERITY, LogUIConstant.SELECT_SEVERITY, LogUIConstant.SEVERITY_LIST);
		DateTimeField startDateTimeField = component.getDataTimeField(LogUIConstant.SELECT_START_DATE);
		DateTimeField endDateTimeField = component.getDataTimeField(LogUIConstant.SELECT_END_DATE);
		leftLayout.addComponent(component.getLogo(LogUIConstant.LOGUI));
		leftLayout.addComponent(component.getLabel(LogUIConstant.SELECT_FILTER_CRITERIA));
		leftLayout.addComponent(componentComboBox);
		leftLayout.addComponent(severityComboBox);
		leftLayout.addComponent(startDateTimeField);
		leftLayout.addComponent(endDateTimeField);
		leftLayout.addComponent(submit);
		logBinder.bind(componentComboBox, Log::getComponent, Log::setComponent);
		logBinder.bind(severityComboBox, Log::getSeverity, Log::setSeverity);
		logBinder.forField(startDateTimeField).withConverter(new DateTimeToStringConverter()).bind(Log::getDateTime, Log::setDateTime);
		logDataBinder.forField(endDateTimeField).withConverter(new DateTimeToStringConverter()).bind(LogData::getEndDate, LogData::setEndDate);
		submit.addClickListener(new SubmitListener(logBinder, logDataBinder, table));
	}

	private void setupRightUI() {
		table.addColumn(Log::getId).setCaption(LogUIConstant.ID);
		table.addColumn(Log::getComponent).setCaption(LogUIConstant.COMPONENT);
		table.addColumn(Log::getDateTime).setCaption(LogUIConstant.DATE_TIME);
		table.addColumn(Log::getSeverity).setCaption(LogUIConstant.SEVERITY);
		table.addColumn(Log::getMessage).setCaption(LogUIConstant.MESSAGE);
		table.addSelectionListener(new SelectionListener<Log>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectionChange(SelectionEvent<Log> event) {
				try {
					Optional<Log> optionalLog = event.getFirstSelectedItem();
					if (optionalLog.isPresent()) {
						selectedLog = optionalLog.get();
					}
				} catch (Exception e) {
					// Do Nothing.
				}
			}
		});
		List<Log> data = logLogic.getAll(50);
		if (data == null) {
			component.getServerFailureNotification(LogUIConstant.INITIALIZATION_FAILED).show(Page.getCurrent());
		} else {
			table.setItems(data);
		}
		table.setSizeFull();
		rightLayout.addComponent(table);
		rightLayout.setSizeFull();
	}

}
