package com.rest.demo.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.demo.api.dto.ResponseDto;
import com.rest.demo.api.dto.WeatherDto;
import com.rest.demo.api.util.OpenWeatherApiUrl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiService {

    /**
     * GET Current Weather Info from Open Weather API
     * 
     * @return
     */
    public WeatherDto getWeatherInfo() {

        // Get Response from Opewn Weather API
        OpenWeatherApiUrl openWeatherApiUrl = OpenWeatherApiUrl.builder().build();
        ResponseDto responseDto = this.getResponseFromWeatherApi(openWeatherApiUrl);
        
        // Set Weather Info
        WeatherDto weather = new WeatherDto();

        if(responseDto.getWeather().size() > 0) {
            weather = responseDto.getWeather().get(0);
            weather.setImageUrl(openWeatherApiUrl.getImageUrl(weather.getIcon()));
        }

        return weather;
    }

    /**
     * GET Current Weather Info from Open Weather API
     * 
     * @param openWeatherApiUrl
     * @return
     */
    private ResponseDto getResponseFromWeatherApi(OpenWeatherApiUrl openWeatherApiUrl) {
        RestTemplate restTemplate = new RestTemplate();

        // Call API
        ResponseEntity<String> response = restTemplate.getForEntity(openWeatherApiUrl.getUrl(), String.class);

        // Json to POJO
        ObjectMapper mapper = new ObjectMapper();
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto = mapper.readValue(response.getBody(), ResponseDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return responseDto;
    }

}