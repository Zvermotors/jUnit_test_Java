package com.example; // Определяет пакет, к которому принадлежит тестовый класс

import com.example.model.Gender; // Импортирует перечисление Gender из пакета модели
import com.example.model.User; // Импортирует класс User из пакета модели
import org.junit.jupiter.api.BeforeEach; // Импортирует аннотацию BeforeEach для настройки перед каждым тестом
import org.junit.jupiter.api.Test; // Импортирует аннотацию Test для обозначения тестовых методов
import static org.junit.jupiter.api.Assertions.*; // Импортирует статические методы для утверждений (assertEquals и др)

class UserTest { // Объявление тестового класса для User
    private User user1, user2, user3; // Объявление переменных для тестовых пользователей

    @BeforeEach // Перед каждым тестом выполнять этот метод
    void setUp() {
        User.clearAllUsers(); // Очищает всех пользователей перед каждым тестом
        user1 = new User("Alice", 25, Gender.FEMALE); // Создаёт пользователя Alice
        user2 = new User("Bob", 30, Gender.MALE); // Создаёт пользователя Bob
        user3 = new User("Charlie", 35, Gender.MALE); // Создаёт пользователя Charlie
    }

    @Test // Обозначает метод как тестовый
    void testUserCreation() {
        assertEquals(3, User.getHowManyUsers()); // Проверяет, что создано 3 пользователя
    }

    @Test
    void testGetAllUsers() {
        assertEquals(3, User.getAllUsers().size()); // Проверяет, что список всех пользователей содержит 3 элемента
    }

    @Test
    void testGetAllUsersByGender() {
        assertEquals(1, User.getAllUsers(Gender.FEMALE).size()); // Проверяет, что одна девушка (Alice)
        assertEquals(2, User.getAllUsers(Gender.MALE).size()); // Проверяет, что два мужчины (Bob и Charlie)
    }

    @Test
    void testGetHowManyUsers() {
        assertEquals(3, User.getHowManyUsers()); // Проверяет подсчет общего количества пользователей
    }

    @Test
    void testGetHowManyUsersByGender() {
        assertEquals(1, User.getHowManyUsers(Gender.FEMALE)); // Проверяет количество девушек
        assertEquals(2, User.getHowManyUsers(Gender.MALE)); // Проверяет количество мужчин
    }

    @Test
    void testGetAllAgeUsers() {
        assertEquals(90, User.getAllAgeUsers()); // Проверяет сумму возрастов всех пользователей (25+30+35=90)
    }

    @Test
    void testGetAllAgeUsersByGender() {
        assertEquals(25, User.getAllAgeUsers(Gender.FEMALE)); // Сумма возрастов девушек (только Alice - 25)
        assertEquals(65, User.getAllAgeUsers(Gender.MALE)); // Сумма возрастов мужчин (30+35=65)
    }

    @Test
    void testGetAverageAgeOfAllUsers() {
        assertEquals(30.0, User.getAverageAgeOfAllUsers()); // Средний возраст всех пользователей (90/3=30)
    }

    @Test
    void testGetAverageAgeOfAllUsersByGender() {
        assertEquals(25.0, User.getAverageAgeOfAllUsers(Gender.FEMALE)); // Средний возраст девушек
        assertEquals(32.5, User.getAverageAgeOfAllUsers(Gender.MALE)); // Средний возраст мужчин (65/2=32.5)
    }

    @Test
    void testDuplicateUserNotAdded() {
        new User("Alice", 25, Gender.FEMALE); // Создает пользователя с именем Alice повторно
        assertEquals(3, User.getHowManyUsers()); // Проверяет, что число пользователей не увеличилось (дубликаты не добавляются)
    }
}