package com.javarush.task.task30.task3008.client;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClientGuiModel {

    // Добавь в него множество(set) строк в качестве константного поля allUserNames
    private final Set<String> allUserNames = new HashSet<>();
    // Добавь поле String newMessage, в котором будет храниться новое сообщение, которое получил клиент
    private String newMessage;
    // Добавь метод void addUser(String newUserName), который должен добавлять имя участника во множество, хранящее всех участников
    public void addUser(String newUserName) {
        allUserNames.add(newUserName);
    }

    // Добавь метод void deleteUser(String userName), который будет удалять имя участника из множества
    public void deleteUser(String userName) {
        allUserNames.remove(userName);
    }

    // Добавь геттер для allUserNames, запретив модифицировать возвращенное множество.
    // Разберись, как это можно сделать с помощью метода класса Collections
    public Set<String> getAllUserNames() {
        return Collections.unmodifiableSet(allUserNames);
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }
}

/*
Чат (20)
Консольный клиент мы уже реализовали, чат бота тоже сделали, почему бы не сделать
клиента с графическим интерфейсом? Он будет так же работать с нашим сервером, но иметь графическое окно, кнопки и т.д.

Итак, приступим. При написании графического клиента будет очень уместно воспользоваться паттерном MVC (Model-View-Controller).
Ты уже должен был с ним сталкиваться, если необходимо, освежи свои знания про MVC с помощью Интернет.
В нашей задаче самая простая реализация будет у класса, отвечающего за модель (Model).

Давай напишем его:
1) Создай класс ClientGuiModel в пакете client.
Все классы клиента должны быть созданы в этом пакете.
2) Добавь в него множество(set) строк в качестве final поля allUserNames.
В нем будет храниться список всех участников чата. Проинициализируй его.
3) Добавь поле String newMessage, в котором будет храниться новое сообщение, которое получил клиент.
4) Добавь геттер для allUserNames, запретив модифицировать возвращенное множество.
Разберись, как это можно сделать с помощью метода класса Collections.
5) Добавь сеттер и геттер для поля newMessage.
6) Добавь метод void addUser(String newUserName), который должен добавлять имя участника во множество, хранящее всех участников.
7) Добавь метод void deleteUser(String userName), который будет удалять имя участника из множества.


Требования:
1. Класс ClientGuiModel должен быть создан в пакете client.
2. Множество строк allUserNames должно быть инициализировано и объявлено с модификаторами private final.
3. Приватное поле newMessage должно быть типа String.
4. В классе ClientGuiModel должен быть создан корректный геттер для поля allUserNames.
5. В классе ClientGuiModel должны быть созданы корректные геттер и сеттер для поля newMessage.
6. Метод addUser() должен добавлять новое имя пользователя в множество allUserNames.
7. Метод deleteUser() должен удалять полученное имя пользователя из множества allUserNames.
*/
