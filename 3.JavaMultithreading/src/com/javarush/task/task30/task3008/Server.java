package com.javarush.task.task30.task3008;

import com.javarush.task.task30.task3008.client.Client;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Server {

    private static final Map<String, Connection> connectionMap = new ConcurrentHashMap<>();


    private static class Handler extends Thread {
        private final Socket socket;

        private Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            //Метод в качестве параметра принимает соединение connection, а возвращает имя нового клиента
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();

                if (answer.getType() == MessageType.USER_NAME) {
                    if (!answer.getData().isEmpty()) {
                        if (!connectionMap.containsKey(answer.getData())) {
                            connectionMap.put(answer.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return answer.getData();
                        }
                    }
                }
            }
        }

        //Требования:
        //1. В классе Handler должен быть создан метод private void notifyUsers(Connection connection, String userName).
        //2. Метод notifyUsers() должен отправлять через connection сообщение о том, что был добавлен пользователь
        // с именем name для каждого имени из connectionMap.
        //3. Метод notifyUsers() НЕ должен отправлять сообщение о добавлении пользователя,
        // если имя пользователя совпадает со вторым параметром метода (userName).
        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                if (!entry.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
                }
            }
        }

        //1.Метод serverMainLoop() должен в бесконечном цикле получать
        // сообщения от клиента (используя метод receive() класса Connection).
        //2. Если сообщение, полученное методом serverMainLoop(), имеет тип MessageType.TEXT,
        // то должно быть отправлено новое сообщение всем участникам чата используя метод sendBroadcastMessage()
        // (форматирование сообщения описано в условии).
        //3. Если сообщение, полученное методом serverMainLoop(),
        // имеет тип отличный от MessageType.TEXT, метод sendBroadcastMessage()
        // не должен быть вызван, и должно быть выведено сообщение об ошибке.

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                //receive message of client
                Message message = connection.receive();
                //if receive msg - text
                if(message.getType() == MessageType.TEXT) {
                    String s = userName + ": " + message.getData();

                    Message formattedMessage = new Message(MessageType.TEXT, s);
                    sendBroadcastMessage(formattedMessage);
                }else {
                    ConsoleHelper.writeMessage("Ошибка!");
                }
            }
        }

        public void run() {
            //Метод run() должен выводить на экран сообщение о том,
            // что было установлено соединение с удаленным адресом (используя метод getRemoteSocketAddress()).
            ConsoleHelper.writeMessage("Было установлено соединение с удаленным адресом: " + socket.getRemoteSocketAddress());

            try (Connection connection = new Connection(socket)){
                String userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);

                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED , userName));
                ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто.");
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");
            }
        }
    }

    public static void sendBroadcastMessage(Message message) {
        //который должен отправлять сообщение message всем соединениям из connectionMap.
        for (String name : connectionMap.keySet()) {
            try {
                connectionMap.get(name).send(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {

        //Запрос порт сервера, используя ConsoleHelper.
        //Создаем серверный сокет java.net.ServerSocket, используя порт из предыдущего пункта.
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            System.out.println("Cервер запущен!"); //вывод сообщения о запуске сервера
            while (true) {
                //В бесконечном цикле слушать и принимать входящие сокетные соединения
                //только что созданного серверного сокета.
                Socket socket = serverSocket.accept();
                //Создавать и запускать новый поток Handler,
                //передавая в конструктор сокет из предыдущего пункта.
                Handler handler = new Handler(socket);
                //После создания потока обработчика Handler переходить на новый шаг цикла.
                handler.start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
