package au.com.tabcorp.data;

import java.math.BigDecimal;

public class InvestmentByCustomer {

	Long customerId;
	BigDecimal totalInvestment;
	
	public InvestmentByCustomer(Long customerId,BigDecimal totalInvestment) {
		super();
		this.totalInvestment = totalInvestment;
		this.customerId = customerId;
	}
	public BigDecimal getTotalInvestment() {
		return totalInvestment;
	}
	public void setTotalInvestment(BigDecimal totalInvestment) {
		this.totalInvestment = totalInvestment;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
}
