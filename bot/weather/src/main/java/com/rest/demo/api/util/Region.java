package com.rest.demo.api.util;

import lombok.Getter;

@Getter
public enum Region {

    서울시("seoul"),
    안산시("ansan");

    private String city;

    Region(String city) {
        this.city = city;
    }
    
}