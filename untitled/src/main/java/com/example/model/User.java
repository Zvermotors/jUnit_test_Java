package com.example.model; // Определяет пакет, к которому принадлежит класс User

import java.util.*; // Импортирует все классы из пакета java.util (например, List, Map, ArrayList)
import java.util.Objects; // Импортирует класс Objects для удобных методов сравнения и хеширования

public class User { // Объявление публичного класса User
    private int id; // Уникальный идентификатор пользователя
    private String name; // Имя пользователя
    private int age; // Возраст пользователя
    private Gender gender; // Пол пользователя (предположительно, это перечисление)

    private static Map<Integer, User> allUsers = new HashMap<>(); // Статическая карта для хранения всех пользователей по id
    private static int countId = 0; // Статическая переменная для подсчета последнего присвоенного id

    // Конструктор класса User
    public User(String name, int age, Gender gender) {
        this.name = name; // Устанавливает имя пользователя
        this.age = age; // Устанавливает возраст пользователя
        this.gender = gender; // Устанавливает пол пользователя

        if (!hasUser()) { // Проверяет, есть ли уже такой пользователь
            countId++; // Увеличивает счетчик id
            this.id = countId; // Назначает id новому пользователю
            allUsers.put(id, this); // Добавляет пользователя в карту всех пользователей
        }
    }

    // Геттер для получения id
    public int getId() { return id; }
    // Геттер для получения имени
    public String getName() { return name; }
    // Геттер для получения возраста
    public int getAge() { return age; }
    // Геттер для получения пола
    public Gender getGender() { return gender; }

    // Переопределение метода equals для сравнения пользователей
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Если сравниваем с самим собой, вернуть true
        if (o == null || getClass() != o.getClass()) return false; // Если объект null или другого класса, вернуть false
        User user = (User) o; // Приводим объект к типу User
        return age == user.age && // Сравниваем возраст
                Objects.equals(name, user.name) && // Сравниваем имена (учитывая null)
                gender == user.gender; // Сравниваем полы (перечисление)
    }

    // Переопределение метода hashCode
    @Override
    public int hashCode() {
        return Objects.hash(name, age, gender); // Генерирует хеш-код на основе имени, возраста и пола
    }

    // Проверка, есть ли уже такой пользователь в allUsers
    private boolean hasUser() {
        return allUsers.containsValue(this); // Проверка наличия значения в карте
    }

    // Переопределение метода toString для удобного отображения
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}'; // Возвращает строковое представление пользователя
    }

    // Статический метод для получения списка всех пользователей
    public static List<User> getAllUsers() {
        return new ArrayList<>(allUsers.values()); // Возвращает новый список всех значений из allUsers
    }

    // Статический метод для получения списка пользователей по полу
    public static List<User> getAllUsers(Gender gender) {
        List<User> result = new ArrayList<>(); // Создает новый список для результирующих пользователей
        for (User user : allUsers.values()) { // Итерирует по всем пользователям
            if (user.gender == gender) { // Если пол совпадает с переданным
                result.add(user); // Добавляет пользователя в результат
            }
        }
        return result; // Возвращает список пользователей указанного пола
    }

    // Статический метод для подсчета общего количества пользователей
    public static int getHowManyUsers() {
        return allUsers.size(); // Возвращает размер карты allUsers
    }

    // Статический метод для подсчета количества пользователей указанного пола
    public static int getHowManyUsers(Gender gender) {
        return getAllUsers(gender).size(); // Возвращает размер списка пользователей определенного пола
    }

    // Статический метод для суммы возрастов всех пользователей
    public static int getAllAgeUsers() {
        // Использует стримы для суммирования возрастов
        return allUsers.values().stream().mapToInt(User::getAge).sum();
    }

    // Аналогичный метод для пользователей определенного пола
    public static int getAllAgeUsers(Gender gender) {
        return getAllUsers(gender).stream().mapToInt(User::getAge).sum(); // Суммирует возраст тех, кто определен пол
    }

    // Статический метод для вычисления среднего возраста всех пользователей
    public static double getAverageAgeOfAllUsers() {
        return allUsers.isEmpty() ? 0 : (double) getAllAgeUsers() / getHowManyUsers(); // Делит сумму возрастов на количество
    }

    // Аналогичный метод для пользователей определенного пола
    public static double getAverageAgeOfAllUsers(Gender gender) {
        return getHowManyUsers(gender) == 0 ? 0 : // Проверка деления на ноль
                (double) getAllAgeUsers(gender) / getHowManyUsers(gender); // Средний возраст выбранной группы
    }

    // Статический метод для очистки списка всех пользователей
    public static void clearAllUsers() {
        allUsers.clear(); // Очищает  всех пользователей
        countId = 0; // Обнуляет счетчик ID
    }
}