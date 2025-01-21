package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
    public static Answer getUrl(String nasaUrl) {
        String url = nasaUrl;

        CloseableHttpClient client = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        HttpGet request = new HttpGet(url);
        try {
            CloseableHttpResponse response = client.execute(request);
            if (response.getCode() == 200) {
                NasaAnswer nasaAnswer = mapper.readValue(response.getEntity().getContent(), NasaAnswer.class);
                return new Answer(nasaAnswer.url, nasaAnswer.date, nasaAnswer.title, nasaAnswer.explanation);
            }
            return new Answer("Ошибка получения изображения");
        }
        catch (Exception e) {
            System.out.println("Ошибка отправки запроса к ресурсу NASA");
            e.printStackTrace();
            return new Answer("Ошибка отправки запроса к ресурсу NASA");
        }
    }

    public static Boolean isDateValid(String date) {
        String DATE_FORMAT = "yyyy-MM-dd";
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String makeText(String title, String date, String explanation) {
        if ((date == null || date.isEmpty()) && (explanation == null || explanation.isEmpty())) {
            return title;
        }
        return "*" + title + "*" + "\n\n" + date + "\n\n" + explanation;
    }
}
