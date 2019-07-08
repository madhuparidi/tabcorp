package au.com.tabcorp.data.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import au.com.tabcorp.data.error.validator.BetType;

@Entity
public class Bet {

	@Id
	@GeneratedValue
	private Long id;
	@FutureOrPresent
	@JsonFormat(pattern= "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTime;
	@NotNull
	@BetType
	private String betType;
	@NotNull
	private Integer propNumber;
	@NotNull
	private Long customerID;
	@NotNull
	@DecimalMax(value="20000.00", message="Investment Amount cannot be more than $20,000")
	private BigDecimal investmentAmount;
	public Bet(LocalDateTime dateTime, String betType, Integer propNumber, Long customerID,
			BigDecimal investmentAmount) {
		super();
		this.dateTime = dateTime;
		this.betType = betType;
		this.propNumber = propNumber;
		this.customerID = customerID;
		this.investmentAmount = investmentAmount;
	}
	/**
	 * @param id
	 * @param dateTime
	 * @param betType
	 * @param propNumber
	 * @param customerID
	 * @param investmentAmount
	 */
	public Bet(Long id, String betType, Integer propNumber, Long customerID,
			BigDecimal investmentAmount) {
		super();
		this.id = id;
		this.betType = betType;
		this.propNumber = propNumber;
		this.customerID = customerID;
		this.investmentAmount = investmentAmount;
	}
	public Bet() {
		super();

	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public Integer getPropNumber() {
		return propNumber;
	}
	public void setPropNumber(Integer propNumber) {
		this.propNumber = propNumber;
	}
	public Long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}
	public BigDecimal getInvestmentAmount() {
		return investmentAmount;
	}
	public void setInvestmentAmount(BigDecimal investmentAmount) {
		this.investmentAmount = investmentAmount;
	}
}
