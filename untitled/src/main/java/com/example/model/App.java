package com.example.model;

import com.example.model.Gender;
import com.example.model.User;

public class App {
    public static void main(String[] args) {
        // Создаем пользователей
        new User("Alice", 25, Gender.FEMALE);
        new User("Bob", 30, Gender.MALE);
        new User("Charlie", 35, Gender.MALE);

        // Выводим статистику
        System.out.println("All users: " + User.getAllUsers());
        System.out.println("Total users: " + User.getHowManyUsers());
        System.out.println("Female users: " + User.getHowManyUsers(Gender.FEMALE));
        System.out.println("Male users: " + User.getHowManyUsers(Gender.MALE));
        System.out.println("Average age: " + User.getAverageAgeOfAllUsers());
        System.out.println("Average age (female): " + User.getAverageAgeOfAllUsers(Gender.FEMALE));
        System.out.println("Average age (male): " + User.getAverageAgeOfAllUsers(Gender.MALE));
    }
}
