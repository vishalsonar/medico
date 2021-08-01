package com.sonar.vishal;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.sonar.vishal.converter.DateTimeToStringConverter;
import com.sonar.vishal.logui.component.Component;
import com.sonar.vishal.logui.component.LogUIConstant;
import com.sonar.vishal.logui.listener.ExpandLogListener;
import com.sonar.vishal.logui.listener.PaginationListener;
import com.sonar.vishal.logui.listener.ResetListener;
import com.sonar.vishal.logui.listener.SubmitListener;
import com.sonar.vishal.logui.logic.LogLogic;
import com.sonar.vishal.medico.common.pojo.Log;
import com.sonar.vishal.medico.common.structure.LogData;
import com.sonar.vishal.medico.common.util.Logger;
import com.vaadin.addon.pagination.Pagination;
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
import com.vaadin.ui.VerticalSplitPanel;

public class LogView extends HorizontalSplitPanel implements View {

	private static final long serialVersionUID = -6960712018063674712L;
	private static final String[] SEVERITY_LIST = { "TRACE", "DEBUG", "INFO", "WARN", "ERROR", "FATAL", "ALL" };
	private static final String[] COMPONENT_LIST = { "CORE", "MEDICOUI", "LOGUI" };
	private Grid<Log> table;
	private Binder<Log> logBinder;
	private Pagination pagination;
	private VerticalLayout leftLayout;
	private VerticalLayout rightLayout;
	private VerticalLayout upperLayout;
	private Binder<LogData> logDataBinder;
	private VerticalSplitPanel rightSplitPanel;
	private ExpandLogListener expandLogListener;
	private transient Component component;
	private transient LogLogic logLogic;

	public LogView() {
		component = new Component();
		leftLayout = new VerticalLayout();
		rightLayout = new VerticalLayout();
		upperLayout = new VerticalLayout();
		table = new Grid<>();
		logLogic = new LogLogic();
		logBinder = new Binder<>();
		logDataBinder = new Binder<>();
		rightSplitPanel = new VerticalSplitPanel();
		expandLogListener = new ExpandLogListener();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		setupRightUI();
		setLeftUI();
		rightSplitPanel.addComponent(upperLayout);
		rightSplitPanel.addComponent(rightLayout);
		rightSplitPanel.setLocked(true);
		rightSplitPanel.setSplitPosition(20, Unit.PERCENTAGE);
		rightSplitPanel.setSizeFull();
		this.addComponents(leftLayout, rightSplitPanel);
		this.setSplitPosition(20, Unit.PERCENTAGE);
		this.setLocked(true);
		UI.getCurrent().setContent(this);
	}

	private void setLeftUI() {
		Button submit = component.getFriendlyButton(LogUIConstant.SUBMIT);
		Button reset = component.getResetButton("Reset");
		Button expandLog = component.getExpandLogButton("Expand Log");
		ComboBox<String> componentComboBox = component.getDropDown(LogUIConstant.SELECT_COMPONENT, LogUIConstant.SELECT_COMPONENT, COMPONENT_LIST);
		ComboBox<String> severityComboBox = component.getDropDown(LogUIConstant.SELECT_SEVERITY, LogUIConstant.SELECT_SEVERITY, SEVERITY_LIST);
		DateTimeField startDateTimeField = component.getDataTimeField(LogUIConstant.SELECT_START_DATE);
		DateTimeField endDateTimeField = component.getDataTimeField(LogUIConstant.SELECT_END_DATE);
		leftLayout.addComponent(component.getLogo(LogUIConstant.LOGUI));
		leftLayout.addComponent(component.getLabel(LogUIConstant.SELECT_FILTER_CRITERIA));
		leftLayout.addComponent(componentComboBox);
		leftLayout.addComponent(severityComboBox);
		leftLayout.addComponent(startDateTimeField);
		leftLayout.addComponent(endDateTimeField);
		leftLayout.addComponent(submit);
		leftLayout.addComponent(reset);
		leftLayout.addComponent(expandLog);
		logBinder.bind(componentComboBox, Log::getComponent, Log::setComponent);
		logBinder.bind(severityComboBox, Log::getSeverity, Log::setSeverity);
		logBinder.forField(startDateTimeField).withConverter(new DateTimeToStringConverter()).bind(Log::getDateTime, Log::setDateTime);
		logDataBinder.forField(endDateTimeField).withConverter(new DateTimeToStringConverter()).bind(LogData::getEndDate, LogData::setEndDate);
		submit.addClickListener(new SubmitListener(logBinder, logDataBinder, table, pagination));
		reset.addClickListener(new ResetListener(table, pagination));
		expandLog.addClickListener(expandLogListener);
	}

	private void setupRightUI() {
		table.addColumn(Log::getId).setCaption(LogUIConstant.ID);
		table.addColumn(Log::getDateTime).setCaption(LogUIConstant.DATE_TIME);
		table.addColumn(Log::getComponent).setCaption(LogUIConstant.COMPONENT);
		table.addColumn(Log::getSeverity).setCaption(LogUIConstant.SEVERITY);
		table.addColumn(Log::getClassName).setCaption(LogUIConstant.CLASS_NAME);
		table.addColumn(Log::getMessage).setCaption(LogUIConstant.MESSAGE);
		table.addColumn(Log::getIp).setCaption(LogUIConstant.IP);
		table.addColumn(Log::getUserId).setCaption(LogUIConstant.USER_ID);
		table.addSelectionListener(new SelectionListener<Log>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectionChange(SelectionEvent<Log> event) {
				try {
					Optional<Log> optionalLog = event.getFirstSelectedItem();
					if (optionalLog.isPresent()) {
						expandLogListener.setSelectedLog(optionalLog.get());
					}
				} catch (Exception e) {
					Logger.error(getClass().getName(), e.getMessage());
				}
			}
		});
		List<Log> data = logLogic.getAll();
		if (data == null) {
			component.getServerFailureNotification(LogUIConstant.INITIALIZATION_FAILED).show(Page.getCurrent());
		} else {
			int dataCount = data.size();
			int subDataCount = dataCount <= 20 ? dataCount : 20;
			pagination = component.getPagination(Long.valueOf(dataCount));
			pagination.addPageChangeListener(new PaginationListener(table, data));
			table.setItems(data.subList(0, subDataCount));
		}
		table.setSizeFull();
		upperLayout.addComponent(pagination);
		rightLayout.addComponent(table);
		rightLayout.setMargin(component.getTableMargin());
		rightLayout.setSizeFull();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(leftLayout, logBinder, logDataBinder, rightLayout, table);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogView other = (LogView) obj;
		return Objects.equals(leftLayout, other.leftLayout) && Objects.equals(logBinder, other.logBinder)
				&& Objects.equals(logDataBinder, other.logDataBinder) && Objects.equals(rightLayout, other.rightLayout);
	}

}
