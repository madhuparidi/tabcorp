package au.com.tabcorp.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

import au.com.tabcorp.data.models.Bet;
import au.com.tabcorp.data.repository.BetRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ComponentScan("au.com.tabcorp")
@SpringBootApplication
public class BetApplication {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(BetApplication.class, args);
    }

    
    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(BetRepository bRepository) {
    	return args -> {
            bRepository.save(new Bet(LocalDateTime.of(2019, 12, 12, 12, 12),"WIN",23434,2342L,new BigDecimal(3455.23)));
            bRepository.save(new Bet(LocalDateTime.of(2019, 11, 11, 1, 12),"PLACE",867,45674L,new BigDecimal(99.23)));
            bRepository.save(new Bet(LocalDateTime.of(2019, 9, 11, 1, 12),"TRIFECTA",544,12343L,new BigDecimal(889.23)));
            bRepository.save(new Bet(LocalDateTime.of(2019, 9, 11, 1, 12),"DOUBLE",567,3456L,new BigDecimal(776.23)));
            bRepository.save(new Bet(LocalDateTime.of(2019, 11, 11, 1, 12),"QUADDIE",867,45674L,new BigDecimal(99.23)));
            bRepository.save(new Bet(LocalDateTime.of(2019, 10, 11, 1, 12),"PLACE",7564,45674L,new BigDecimal(99.23)));
            bRepository.save(new Bet(LocalDateTime.of(2019, 10, 11, 1, 12),"WIN",567,3456L,new BigDecimal(776.23)));
            //"PLACE", "TRIFECTA", "DOUBLE","QUADDIE"
        };
    }
}