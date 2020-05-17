package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client {

    public static void main(String[] args) throws IOException {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public class BotSocketThread extends Client.SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. " +
                    "Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String senderName = "";
            String senderMessageText;

            if (message.contains(": ")) {
                senderName = message.substring(0, message.indexOf(": "));
                senderMessageText = message.substring(message.indexOf(": ") + 2);
            } else {
                senderMessageText = message;
            }

            SimpleDateFormat format = null;
            //Отправляем ответ в зависти от отправленного текста
            //equalsIgnoreCase() — сравнивает данную строку с другой строкой,
            // игнорируя регистр. Две строки считаются равными, если они имеют одинаковую длину и
            // соответствующие символы у двух строк равны, игнорируя регистр букв.
            if ("дата".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("d.MM.YYYY");
            } else if ("день".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("d");
            } else if ("месяц".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("MMMM");
            } else if ("год".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("YYYY");
            } else if ("время".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("H:mm:ss");
            } else if ("час".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("H");
            } else if ("минуты".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("m");
            } else if ("секунды".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("s");
            }

            if (format != null)
            {
                sendTextMessage("Информация для " + senderName + ": " +
                        format.format(Calendar.getInstance().getTime()));
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        //метод должен создавать и возвращать объект класса BotSocketThread
        return new BotSocketThread();
    }

    @Override
    //Он должен всегда возвращать false.
    //Мы не хотим, чтобы бот отправлял текст введенный в консоль.
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        //getUserName(), метод должен генерировать новое имя бота,
        // например: date_bot_X, где X - любое число от 0 до 99.
        // Для генерации X используй метод Math.random().
        int randomNum = (int) (Math.random() * 100);
        return "date_bot_" + randomNum;
    }
}



