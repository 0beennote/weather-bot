package com.rest.demo;

import com.rest.demo.api.service.WeatherApiService;
import com.rest.demo.telegram.bot.WeatherAlarmBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class WeatherBotApplication {

	public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new WeatherAlarmBot(new WeatherApiService()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        SpringApplication.run(WeatherBotApplication.class, args);
	}

}
