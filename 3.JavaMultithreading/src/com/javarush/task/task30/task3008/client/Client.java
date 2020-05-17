package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {

    protected Connection connection;
    private volatile boolean clientConnected;

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.run();
    }

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Введите адрес сервера!");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Введите порт сервера!");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите имя пользователя!");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
        //Этот метод может быть переопределен,
        // если мы будем писать какой-нибудь другой клиент,
        // унаследованный от нашего,
        // который не должен отправлять введенный в консоль текст.
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Соединение с клиентом невозможно. " + e.getMessage());
            clientConnected = false;
        }
    }

     public void run() {

         SocketThread socketThread = getSocketThread();
         socketThread.setDaemon(true);
         //это нужно для того, чтобы при выходе из программы вспомогательный поток прервался автоматически.
         socketThread.start();
         synchronized (this) {
             try {
                 this.wait();
             } catch (InterruptedException e) {
                 ConsoleHelper.writeMessage("Соединение приостановлено!");
                 System.exit(1);
             }
             if (clientConnected = true) {
                 ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
             } else {
                 ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
             }
             while (clientConnected) {
                 String text = ConsoleHelper.readString();
                 // Если будет введена команда ‘exit‘, то выйди из цикла.
                 if (text.toLowerCase().equals("exit")) {
                     break;
                 }
                 // После каждого считывания, если метод shouldSendTextFromConsole() возвращает true,
                 // отправь считанный текст с помощью метода sendTextMessage().
                 if (shouldSendTextFromConsole()) {
                     sendTextMessage(text);
                 }
             }
         }
    }

    public class SocketThread extends Thread {

         protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник с именем " + userName +" присоединился к чату");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник с именем " + userName +" покинул чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
             Client.this.clientConnected = clientConnected;
             synchronized (Client.this) {
                 Client.this.notify();
             }
        }
        //Этот метод будет представлять клиента серверу
        protected void clientHandshake() throws IOException, ClassNotFoundException {

            while (true) {
                //Этот метод будет представлять клиента серверу
                Message message = connection.receive();

                if (message.getType() == MessageType.NAME_REQUEST) {

                    connection.send(new Message(MessageType.USER_NAME, getUserName()));

                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    return;
                } else {
                    throw new IOException("Unexpected MessageType");
                }

            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                //Этот метод будет реализовывать главный цикл обработки сообщений сервера
                Message message = connection.receive();

                if (message.getType() == MessageType.TEXT) {
                    processIncomingMessage(message.getData());
                } else if (message.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(message.getData());
                } else if (message.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(message.getData());
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }


        }

        public void run() {
            //Socket socket = null (так не прокатило)
            try {
                //новый объект класса java.net.Socket c запросом сервера и порта
                Socket socket = new Socket(getServerAddress(), getServerPort());
                //установлено и сохранено в поле connection соединение с сервером
                Client.this.connection = new Connection(socket);

                //Вызови метод, реализующий "рукопожатие" клиента с сервером
                clientHandshake();
                //Вызови метод, реализующий основной цикл обработки сообщений сервера
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Соединение с клиентом приостановлено " + e.getMessage());
                notifyConnectionStatusChanged(false);
            }
        }
    }
}

