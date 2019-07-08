package au.com.tabcorp.data.models;

import java.sql.Timestamp;

public class BetsPerHour {

	Long numberOfBets;
	Timestamp fromDateTime;
	Timestamp toDateTime;
	public Long getNumberOfBets() {
		return numberOfBets;
	}
	public void setNumberOfBets(Long numberOfBets) {
		this.numberOfBets = numberOfBets;
	}
	public Timestamp getFromDateTime() {
		return fromDateTime;
	}
	public void setFromDateTime(Timestamp fromDateTime) {
		this.fromDateTime = fromDateTime;
	}
	public Timestamp getToDateTime() {
		return toDateTime;
	}
	public void setToDateTime(Timestamp toDateTime) {
		this.toDateTime = toDateTime;
	}
	
	public BetsPerHour(Long numberOfBets, Timestamp fromDateTime, Timestamp toDateTime) {
		super();
		this.numberOfBets = numberOfBets;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
	}
}
