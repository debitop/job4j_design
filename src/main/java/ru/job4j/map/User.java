package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return children == user.children && name.equals(user.name) && birthday.equals(user.birthday);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, children, birthday);
//    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        User user1 = new User("Alex", 2, calendar);
        User user2 = new User("Alex", 2, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        map.forEach((key, value) -> System.out.println(key + " : " + value));
        map.forEach((key, value) -> System.out.println(key.hashCode() + " : " + value));
    }
}
