package au.com.tabcorp.data;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import au.com.tabcorp.data.error.BetTypeNotFoundException;

@RestController
public class BetController {

	 @Autowired
	    private BetRepository repository;
	

	    // Find
	    @GetMapping("/bets")
	    List<Bet> findAll() {
	        return repository.findAll();
	    }
	    
	    // Save
	    @PostMapping("/bets")
	    @ResponseStatus(HttpStatus.CREATED)
	    Bet newBet(@Valid @RequestBody Bet newBet) {
	        return repository.save(newBet);
	    }

	    // Find
	    @GetMapping("/bets/{id}")
	    Bet findOne(@PathVariable @Min(1) Long id) {
	        return repository.findById(id)
	                .orElseThrow(() -> new BetTypeNotFoundException(id));
	    }

	    // Save or update
	    @PutMapping("/bets/{id}")
	    Bet saveOrUpdate(@RequestBody Bet newBet, @PathVariable Long id) {

	        return repository.findById(id)
	                .map(x -> {
	                    x.setBetType(newBet.getBetType());
	                    x.setDateTime(newBet.getDateTime());
	                    x.setInvestmentAmount(newBet.getInvestmentAmount());
	                    x.setPropNumber(newBet.getPropNumber());
	                    return repository.save(x);
	                })
	                .orElseGet(() -> {
	                    newBet.setId(id);
	                    return repository.save(newBet);
	                });
	    }

	    @DeleteMapping("/bets/{id}")
	    void deleteBet(@PathVariable Long id) {
	        repository.deleteById(id);
	    }
	    
	    @GetMapping("/bets/reports/investmentByType")
	    List<InvestmentByType> reportInvestmentByType(){
	    	return repository.findInvestmentByType();
	    }
	    
	    @GetMapping("/bets/reports/investmentByCustomer")
	    List<InvestmentByCustomer> reportInvestmentByCustomer(){
	    	return repository.findInvestmentByCustomer();
	    }
	    
	    @GetMapping("/bets/reports/betsByType")
	    List<BetsByType> reportBetsByType(){
	    	return repository.findBetsByType();
	    }
}
