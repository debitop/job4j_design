package ru.job4j.serialization.json;

public class Address {
    private String street;
    private String town;

    public Address(String street, String town) {
        this.street = street;
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public String getTown() {
        return town;
    }

    @Override
    public String toString() {
        return "Address{"
                + "street='" + street + '\''
                + ", town='" + town + '\''
                + '}';
    }
}
