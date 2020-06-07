package com.rest.demo;

import static org.junit.Assert.assertTrue;

import com.rest.demo.api.util.OpenWeatherApiUrl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherBotApplicationTests {

	@Test
	public void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();

        // Call API
        OpenWeatherApiUrl openWeatherApiUrl = OpenWeatherApiUrl.builder().build();
		ResponseEntity<String> response = restTemplate.getForEntity(openWeatherApiUrl.getUrl(), String.class);
		
		assertTrue("response.getStatusCodeValue() is not 200", response.getStatusCodeValue() == 200);
	}

}
