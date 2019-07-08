package au.com.tabcorp.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BetRepository extends JpaRepository<Bet, Long> {

	@Query("SELECT " +
	           " new au.com.tabcorp.data.InvestmentByType( b.betType, SUM(b.investmentAmount)) " +
	           "FROM " +
	           "Bet AS b "
	           + "GROUP BY b.betType" )
	List<InvestmentByType>findInvestmentByType();
	
	@Query("SELECT " +
	           " new au.com.tabcorp.data.InvestmentByCustomer( b.customerID, SUM(b.investmentAmount)) " +
	           "FROM " +
	           "Bet AS b "
	           + "GROUP BY b.customerID" )
	List<InvestmentByCustomer>findInvestmentByCustomer();
	
	@Query("SELECT " +
	           " new au.com.tabcorp.data.BetsByType( b.betType, count(1)) " +
	           "FROM " +
	           "Bet AS b "
	           + "GROUP BY b.customerID" )
	List<BetsByType>findBetsByType();
	
	
}
