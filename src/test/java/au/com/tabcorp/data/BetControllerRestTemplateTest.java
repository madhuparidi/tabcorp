package au.com.tabcorp.data;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import au.com.tabcorp.data.models.Bet;
import au.com.tabcorp.data.repository.BetRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@ActiveProfiles("test")
public class BetControllerRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private BetRepository mockRepository;

    /*
        {
            "timestamp":"2019-09-05T09:34:13.280+0000",
            "status":400,
            "errors":["Investment Amount cannot be more than $20,000"]
        }
     */
    @Test
    public void save_investment_greater_than_threshold_400() throws JSONException {

        String betInJson = "{ \"dateTime\": \"2019-12-12 12:12:00\"," + 
        		"        \"betType\": \"WIN\"," + 
        		"        \"propNumber\": 23434," + 
        		"        \"customerID\": 2342," + 
        		"        \"investmentAmount\": 34955.23" + 
        		"    }";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(betInJson, headers);

        // send json with POST
        ResponseEntity<String> response = restTemplate.postForEntity("/bets", entity, String.class);
        //printJSON(response);

        String expectedJson = "{\"status\":400,\"errors\":[\"Investment Amount cannot be more than $20,000\"]}";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        JSONAssert.assertEquals(expectedJson, response.getBody(), false);

        verify(mockRepository, times(0)).save(any(Bet.class));

    }

    /*
        {
            "timestamp":"2019-03-05T09:34:13.207+0000",
            "status":400,
            "errors":["Invalid BetType.BetType not allowed."]
        }
     */
    @Test
    public void save_invalidBetType_400() throws JSONException {

        String betInJson = "{ \"dateTime\": \"2019-12-12 12:12:00\"," + 
        		"        \"betType\": \"POKO\"," + 
        		"        \"propNumber\": 23434," + 
        		"        \"customerID\": 2342," + 
        		"        \"investmentAmount\": 3455.23" + 
        		"    }";;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(betInJson, headers);

        //Try exchange
        ResponseEntity<String> response = restTemplate.exchange("/bets", HttpMethod.POST, entity, String.class);

        String expectedJson = "{\"status\":400,\"errors\":[\"Invalid BetType.BetType not allowed.\"]}";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        JSONAssert.assertEquals(expectedJson, response.getBody(), false);

        verify(mockRepository, times(0)).save(any(Bet.class));

    }

    @Test
    public void save_bet_invalid_date__400() throws JSONException {

        String betInJson = "{ \"dateTime\": \"2018-12-12 12:12:00\"," + 
        		"        \"betType\": \"WIN\"," + 
        		"        \"propNumber\": 23434," + 
        		"        \"customerID\": 2342," + 
        		"        \"investmentAmount\": 3495.23" + 
        		"    }";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(betInJson, headers);

        // send json with POST
        ResponseEntity<String> response = restTemplate.postForEntity("/bets", entity, String.class);
        //printJSON(response);

        String expectedJson = "{\"status\":400,\"errors\":[\"must be a date in the present or in the future\"]}";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        JSONAssert.assertEquals(expectedJson, response.getBody(), false);

        verify(mockRepository, times(0)).save(any(Bet.class));

    }
    
    @Test
    public void save_valid_bet__201() throws JSONException {

        String betInJson = "{ \"dateTime\": \"2019-12-12 12:12:00\"," + 
        		"        \"betType\": \"WIN\"," + 
        		"        \"propNumber\": 23434," + 
        		"        \"customerID\": 2342," + 
        		"        \"investmentAmount\": 3495.23" + 
        		"    }";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(betInJson, headers);

        // send json with POST
        ResponseEntity<String> response = restTemplate.postForEntity("/bets", entity, String.class);
        //printJSON(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
      
    }
    
  

}
