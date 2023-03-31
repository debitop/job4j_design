package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainJsonPojoHouse {
    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonAddress = new JSONObject("{\"street\":\"Arbat\",\"town\":\"Moscow\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("bedroom");
        list.add("kitchen");
        list.add("bathroom");
        JSONArray jsonRooms = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final House house = new House(false, 450, new Address("Arbat", "Moscow"),
                new String[]{"bedroom", "kitchen", "bathroom"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("privateHouse", house.isPrivateHouse());
        jsonObject.put("size", house.getSize());
        jsonObject.put("address", jsonAddress);
        jsonObject.put("rooms", jsonRooms);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(house));
    }
}
