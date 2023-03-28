package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[]{"Worker", "Married"});

        final House house = new House(true, 400, new Address("Arbat", "Moscow"),
                new String[]{"bedroom", "kitchen", "bathroom"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Модифицируем json-строку для person*/
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);

        /* Преобразуем объект house в json-строку. */
        System.out.println(gson.toJson(house));

        /* Модифицируем json-строку для house*/
        final String houseJson =
                "{"
                        + "\"privateHouse\":false,"
                        + "\"size\":450,"
                        + "\"address\":"
                        + "{"
                        + "\"street\":\"arbat\","
                        + "\"town\":\"Moscow\""
                        + "},"
                        + "\"rooms\":"
                        + "[\"bedroom\",\"kitchen\",\"bathroom\",\"hall\"]"
                        + "}";
        final House houseMod = gson.fromJson(houseJson, House.class);
        System.out.println(houseMod);
    }
}