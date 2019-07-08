package au.com.tabcorp.data.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class InvestmentByType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String betType;
	BigDecimal totalInvestment;
	public BigDecimal getTotalInvestment() {
		return totalInvestment;
	}
	public void setTotalInvestment(BigDecimal totalInvestment) {
		this.totalInvestment = totalInvestment;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}

	public InvestmentByType(String betType, BigDecimal totalInvestment ) {
		super();
		this.totalInvestment = totalInvestment;
		this.betType = betType;
	}
	public InvestmentByType() {
		super();

	}

}
