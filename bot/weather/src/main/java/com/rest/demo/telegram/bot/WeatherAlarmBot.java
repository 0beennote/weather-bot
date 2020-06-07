package com.rest.demo.telegram.bot;

import com.rest.demo.api.dto.WeatherDto;
import com.rest.demo.api.service.WeatherApiService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class WeatherAlarmBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    private WeatherApiService weatherApiService;

    public WeatherAlarmBot(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if(update.getMessage().getText().contains("날씨")) {
                WeatherDto weather = weatherApiService.getWeatherInfo();
    
                SendPhoto message = new SendPhoto()
                    .setChatId(update.getMessage().getChatId())
                    .setCaption("Current Weather is " + weather.getDescription())
                    .setPhoto(weather.getImageUrl());

                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
            } else {
                SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("What? *-* ['날씨'라고 쳐보세요.]");

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}