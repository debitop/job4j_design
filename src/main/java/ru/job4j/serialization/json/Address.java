package ru.job4j.serialization.json;

public class Address {
    private final String street;
    private final String town;

    public Address(String street, String town) {
        this.street = street;
        this.town = town;
    }

    @Override
    public String toString() {
        return "Address{"
                + "street='" + street + '\''
                + ", town='" + town + '\''
                + '}';
    }
}
