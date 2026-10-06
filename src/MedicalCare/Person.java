package MedicalCare;

import java.util.Scanner;

public abstract class Person {
    // Переменные
    private String name;
    private int age;

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        String trimmed = name.trim();
        if (trimmed.length() < 3 || trimmed.length() > 20) {
            throw new IllegalArgumentException("Имя должно быть от 3 до 20 символов");
        }
        if (trimmed.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Имя не должно содержать цифр");
        }
        this.name = trimmed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Некорректный возраст: " + age);
        }
        this.age = age;
    }

    // Абстрактный метод
    public abstract void DisplayInfo();

    // Конструктор по умолчанию — ввод из консоли с проверкой
    public Person() {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Введите имя (3-20 символов, без цифр): ");
                setName(sc.nextLine());

                System.out.print("Введите возраст (0-150): ");
                setAge(Integer.parseInt(sc.nextLine().trim()));

                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Попробуйте снова.");
            }
        }
    }

    // Параметризованный конструктор
    public Person(int age, String name) {
        setName(name);
        setAge(age);
    }
}