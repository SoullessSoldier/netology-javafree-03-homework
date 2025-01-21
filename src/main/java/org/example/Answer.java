package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс ответа Nasa со свойствами <b>url</b>, <b>date</b>, <b>title</b> и <b>explanation</b>.
 * @author SoullessSoldier
 * @version 0.1
 */
public class Answer {
    /** Поле адреса изображения NASA */
    private String url;

    /** Поле названия изображения NASA */
    private String title;
    /** Поле даты изображения NASA */
    private String date;
    /** Поле описания изображения NASA */
    private String explanation;

    /**
     * Конструктор - создание нового объекта с сообщением об ошибке
     * @param text - сообщение
     * @see Answer#Answer(String)
     */
    public Answer(@JsonProperty("url") String text) {
        this.url = text;
        this.date = "";
        this.title = "";
        this.explanation = "";
    }

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param url - адрес изображения
     * @param date - дата изображения
     * @param title - название изображения
     * @param explanation - описание изображения
     * @see Answer#Answer(String, String, String, String)
     */
    public Answer(@JsonProperty("url") String url,
                  @JsonProperty("date") String date,
                  @JsonProperty("title") String title,
                  @JsonProperty("explanation") String explanation) {
        this.url = url;
        this.date = date;
        this.title = title;
        this.explanation = explanation;
    }

    /**
     * Функция получения значения поля {@link Answer#url}
     * @return возвращает адрес изображения (или текст)
     */
    public String getUrl() {
        return url;
    }

    /**
     * Функция получения значения поля {@link Answer#date}
     * @return возвращает дату изображения (или пустой текст)
     */
    public String getDate() {
        return date;
    }

    /**
     * Функция получения значения поля {@link Answer#explanation}
     * @return возвращает описание изображения (или пустой текст)
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * Функция получения значения поля {@link Answer#title}
     * @return возвращает название изображения (или пустой текст)
     */
    public String getTitle() {
        return title;
    }
}
