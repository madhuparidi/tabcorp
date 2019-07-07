package au.com.tabcorp.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        };
    }
}