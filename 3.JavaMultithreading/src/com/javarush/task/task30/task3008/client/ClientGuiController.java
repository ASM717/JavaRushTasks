package com.javarush.task.task30.task3008.client;

public class ClientGuiController extends Client {

    // Создай и проинициализируй поле, отвечающее за модель ClientGuiModel model.
    private ClientGuiModel model = new ClientGuiModel();
    // Создай и проинициализируй поле, отвечающее за представление ClientGuiView view.
    // Подумай, что нужно передать в конструктор при инициализации объекта
    private ClientGuiView view = new ClientGuiView(this);

    public static void main(String[] args) {
        ClientGuiController guiController = new ClientGuiController();
        guiController.run();
    }

    public ClientGuiModel getModel() {
        return model;
    }

    public void setModel(ClientGuiModel model) {
        this.model = model;
    }

    @Override
    protected String getServerAddress() {
        //getServerAddress(), getServerPort(), getUserName().
        //Они должны вызывать одноименные методы из представления (view).
        return view.getServerAddress();
    }

    @Override
    protected int getServerPort() {
        //getServerAddress(), getServerPort(), getUserName().
        //Они должны вызывать одноименные методы из представления (view).
        return view.getServerPort();
    }

    @Override
    protected String getUserName() {
        //getServerAddress(), getServerPort(), getUserName().
        //Они должны вызывать одноименные методы из представления (view).
        return view.getUserName();
    }

    @Override
    protected SocketThread getSocketThread() {
        //должен создавать и возвращать объект типа GuiSocketThread.
        return new GuiSocketThread();
    }

    @Override
    public void run() {
        //должен получать объект SocketThread через метод getSocketThread() и вызывать у него метод run().
        //Разберись, почему нет необходимости вызывать метод run() в отдельном потоке,
        // как мы это делали для консольного клиента.
        SocketThread socketThread = getSocketThread();
        socketThread.run();
    }

    public class GuiSocketThread extends SocketThread {
        @Override
        protected void processIncomingMessage(String message) {
            // должен устанавливать новое сообщение у модели и
            // вызывать обновление вывода сообщений у представления.
            model.setNewMessage(message);
            view.refreshMessages();
        }

        @Override
        protected void informAboutAddingNewUser(String userName) {
            //должен добавлять нового пользователя в модель и
            // вызывать обновление вывода пользователей у отображения.
            model.addUser(userName);
            view.refreshUsers();
        }

        @Override
        protected void informAboutDeletingNewUser(String userName) {
            //должен удалять пользователя из модели и
            // вызывать обновление вывода пользователей у отображения.
            model.deleteUser(userName);
            view.refreshUsers();
        }

        @Override
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            //должен вызывать аналогичный метод у представления.
            view.notifyConnectionStatusChanged(clientConnected);
        }
    }
}
