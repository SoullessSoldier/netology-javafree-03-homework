# Регистрируем бота ТГ
Пишем бота для запроса изображения с Nasa Api из прошлого занятия

Java lib - TelegramBots

https://rubenlagus.github.io/TelegramBotsDocumentation/getting-started.html#receive-updates

**Лектор брал код от версии 6.8:**  
https://github.com/rubenlagus/TelegramBots/blob/aad139de980ae25ee7a4b06bbe7644c6077421ce/TelegramBots.wiki/Getting-Started.md


В классе NasaBot есть конструктор, принимающий секреты бота и API Nasa и выполняющий регистрацию.

В боте реализованы команды:
- "/start"
- "/help"
- "/image"
- "/date 2025-01-20", для даты в классе Util есть валидатор 

Реализовал загрузку параметров бота и Nasa Api Key из INI-файла config.ini.
Оставил для примера файл config.ini.sample
Добавил к картинке вторым сообщением названием, дату и описание, посмотрел, как работать с разметкой текста в сообщении.



