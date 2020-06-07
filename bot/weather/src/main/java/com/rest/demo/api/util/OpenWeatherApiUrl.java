package com.rest.demo.api.util;

import org.springframework.beans.factory.annotation.Value;

import lombok.Builder;

@Builder
public class OpenWeatherApiUrl {

    // openweathermap API for Onecall
    // https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}
    // http://openweathermap.org/img/w/10d.png

    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?";
    private static final String IMG_URL = "http://openweathermap.org/img/wn/";

    @Value("${openweathermap.api.key}")
    private String apiKey;

    /**
     * GET: Open Weather API URL
     * 
     * @return
     */
    public String getUrl() {
        return URL + "q=" + Region.서울시.getCity() + "&appid=" + apiKey;
    }
    
    /**
     * GET: Open Weather API URL
     * 
     * @param city
     * @return
     */
    public String getUrl(String city) {
        return URL + "q=" + city + "&appid=" + apiKey;
    }

    /**
     * GET: Open Weather API IMAGE URL
     * 
     * @param image
     * @return
     */
    public String getImageUrl(String image) {
        return IMG_URL + image + "@4x.png";
    }
    
}