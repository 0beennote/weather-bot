package com.rest.demo.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class WeatherDto {
    private String id;
    private String main;
    private String description;
    private String icon;
    private String imageUrl;
}