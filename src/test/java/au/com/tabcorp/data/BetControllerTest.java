package au.com.tabcorp.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BetRepository mockRepository;

    /*
        {
            "timestamp":"2019-03-05T09:34:13.280+0000",
            "status":400,
            "errors":["Invalid BetType.BetType not allowed.","must be a date in the present or in the future","Investment Amount cannot be more than $20,000"]
        }
     */
    @Test
    public void save_emptyAuthor_emptyPrice_400() throws Exception {

    	 String betInJson = "{ \"dateTime\": \"2018-12-12 12:12:00\"," + 
         		"        \"betType\": \"LOTTO\"," + 
         		"        \"propNumber\": 23434," + 
         		"        \"customerID\": 2342," + 
         		"        \"investmentAmount\": 34955.23" + 
         		"    }";

        mockMvc.perform(post("/bets")
                .content(betInJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(3)))
                .andExpect(jsonPath("$.errors", hasItem("Invalid BetType.BetType not allowed.")))
                .andExpect(jsonPath("$.errors", hasItem("must be a date in the present or in the future")))
                .andExpect(jsonPath("$.errors", hasItem("Investment Amount cannot be more than $20,000")));

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
    public void save_invalidBetType_400() throws Exception {

    	String betInJson = "{ \"dateTime\": \"2019-12-12 12:12:00\"," + 
         		"        \"betType\": \"LOTTO\"," + 
         		"        \"propNumber\": 23434," + 
         		"        \"customerID\": 2342," + 
         		"        \"investmentAmount\": 3455.23" + 
         		"    }";
        mockMvc.perform(post("/bets")
                .content(betInJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors", hasItem("Invalid BetType.BetType not allowed.")));

        verify(mockRepository, times(0)).save(any(Bet.class));

    }

}
