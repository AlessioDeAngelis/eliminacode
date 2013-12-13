package it.alessio.eliminacode.common.model;

import java.util.Date;

public class HistoryLine {
	private Date date;
	private int id;
	private int serviceId;
	private int machineId;

	public HistoryLine() {
		super();
		this.id = 0;
		this.serviceId = 0;
		this.machineId = 0;
		this.date = new Date();
	}
}
