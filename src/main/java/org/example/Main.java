package org.example;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String BOT_NAME;
        String BOT_TOKEN;
        String NASA_API_KEY;
        String NASA_APOD_URL;

        try {
            Properties props = new Properties();
            props.load(new FileInputStream(new File("config.ini")));

            BOT_NAME = props.getProperty("BOT_NAME");
            BOT_TOKEN = props.getProperty("BOT_TOKEN");
            NASA_API_KEY = props.getProperty("NASA_API_KEY");
            NASA_APOD_URL = props.getProperty("NASA_APOD_URL");
        } catch (IOException e) {
            System.out.println("File config.ini not found!");
            System.out.println(e.getMessage());
            BOT_NAME = "";
            BOT_TOKEN = "";
            NASA_API_KEY = "";
            NASA_APOD_URL = "";
        }

        try {
            new NasaBot(BOT_NAME, BOT_TOKEN, NASA_API_KEY, NASA_APOD_URL);
        } catch (TelegramApiException e) {
            System.out.println("Exception TelegramApiException!");
            System.out.println(e.getMessage());
        }
    }
}