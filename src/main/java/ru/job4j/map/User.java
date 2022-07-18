package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        User user1 = new User("Alex", 2, calendar);
        User user2 = new User("Alex", 2, calendar);
        User user3 = new User("Alex", 2, calendar);
        User user4 = new User("Alex", 2, calendar);
        User user5 = new User("Alex", 2, calendar);
        User user6 = new User("Alex", 2, calendar);
        User user7 = new User("Alex", 2, calendar);
        User user8 = new User("Alex", 2, calendar);
        User user9 = new User("Alex", 2, calendar);
        User user10 = new User("Alex", 2, calendar);
        User user11 = new User("Alex", 2, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        map.put(user3, new Object());
        map.put(user4, new Object());
        map.put(user5, new Object());
        map.put(user6, new Object());
        map.put(user7, new Object());
        map.put(user8, new Object());
        map.put(user9, new Object());
        map.put(user10, new Object());
        map.put(user11, new Object());
        map.forEach((key, value) -> System.out.println(key + " : " + value));
        map.forEach((key, value) -> System.out.println(key.hashCode() + " : " + value));
        //map.forEach((key, value) -> System.out.println(key.hashCode() & (15) + " : " + value));
        int num1 = 1989780873 & 15;
        int num2 = 931919113 & 15;
        int num3 = 1828972342 & 15;
        int num4 = 1480010240 & 15;
        int num5 = 999966131 & 15;
        int num6 = 2074407503 & 15;
        int num7 = 764977973 & 15;
        int num8 = 81628611 & 15;
        int num9 = 1607521710 & 15;
        int num10 = 1452126962 & 15;
        int num11 = 381259350 & 15;
        int num12 = user1.hashCode() & 15;
        System.out.println(user1.hashCode() + " " + num12);
        System.out.println(" ");
        System.out.println("339046442 " + num1);
        System.out.println("999966131 " + num2);
        System.out.println("2074407503 " + num3);
        System.out.println("2074407503 " + num4);
        System.out.println("2074407503 " + num5);
        System.out.println("2074407503 " + num6);
        System.out.println("2074407503 " + num7);
        System.out.println("2074407503 " + num8);
        System.out.println("2074407503 " + num9);
        System.out.println("2074407503 " + num10);
        System.out.println("2074407503 " + num11);
    }
}
