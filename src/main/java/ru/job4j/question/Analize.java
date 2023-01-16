package ru.job4j.question;

import java.util.HashMap;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted;
        HashMap<Integer, String> previousMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user.getName());
        }
        for (User user : current) {
            if (!previousMap.containsKey(user.getId())) {
                added++;
            }
            if (previousMap.containsKey(user.getId()) && (!user.getName().equals(previousMap.get(user.getId())))) {
                changed++;
            }
        }
        deleted = previous.size() - current.size() + added;
        return new Info(added, changed, deleted);
    }
}