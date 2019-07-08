package au.com.tabcorp.data;

public class BetsByType {
	String betType;
	Long numberOfBets;

	public Long getNumberOfBets() {
		return numberOfBets;
	}

	public void setNumberOfBets(Long numberOfBets) {
		this.numberOfBets = numberOfBets;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}


	public BetsByType(String betType,Long numberOfBets) {
		super();
		this.numberOfBets = numberOfBets;
		this.betType = betType;
	}

}
