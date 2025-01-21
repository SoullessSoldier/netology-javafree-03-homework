package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class NasaBot extends TelegramLongPollingBot {

    private final String BOT_NAME;
    private final String BOT_TOKEN;

    private final String NASA_API_KEY;
    private final String NASA_APOD_URL;

    public NasaBot(String BOT_NAME, String BOT_TOKEN, String NASA_API_KEY, String NASA_APOD_URL) throws TelegramApiException {
        this.BOT_NAME = BOT_NAME;
        this.BOT_TOKEN = BOT_TOKEN;
        this.NASA_API_KEY = NASA_API_KEY;
        this.NASA_APOD_URL = NASA_APOD_URL + "?api_key=" + NASA_API_KEY;

        TelegramBotsApi botsApplication = new TelegramBotsApi(DefaultBotSession.class);
        botsApplication.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return this.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return this.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        /*  Реализованы команды
            /start
            /help
            /image
            /date 2025-01-20
        */

        String text, imageUrl;
        Answer answer;

        long chatId = update.getMessage().getChatId();
        text = update.getMessage().getText();
        String[] splittedText = text.split(" ");

        String action = splittedText[0];



        switch (action) {
            case "/start":
                sendMessage(chatId, "Я бот, который умеет получать картинку дня из NASA", "text");
                break;
            case "/help":
                sendMessage(chatId, "Для получения картинки дня введи /image или /date ГГГГ-ММ-ДД "
                        + "для получения картинки за определенную дату", "text");
                break;
            case "/image":
                answer = Utils.getUrl(NASA_APOD_URL);
                imageUrl = answer.getUrl();
                text = Utils.makeText(answer.getTitle(), answer.getDate(), answer.getExplanation());
                sendMessage(chatId, imageUrl, "text");
                sendMessage(chatId, text, "richtext");
                break;
            case "/date":
                String date = splittedText[1];
                if (Utils.isDateValid(date)) {
                    String url = NASA_APOD_URL + "&date=" + date;
                    answer = Utils.getUrl(url);
                    imageUrl = answer.getUrl();
                    text = Utils.makeText(answer.getTitle(), answer.getDate(), answer.getExplanation());
                    sendMessage(chatId, imageUrl, "text");
                    sendMessage(chatId, text, "richtext");
                } else {
                    sendMessage(chatId, "Дата не распознана! Формат 2025-01-21", "text");
                }
                break;
            default:
                sendMessage(chatId, "Команда не распознана!", "text");

        }

    }

    private void sendMessage(long chatId, String text, String textType) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        if (textType.equals("richtext")) {
            message.setParseMode("Markdown");
        }
        message.setText(text);


        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
