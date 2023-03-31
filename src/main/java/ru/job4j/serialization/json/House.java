package ru.job4j.serialization.json;

import java.util.Arrays;

public class House {

    private boolean privateHouse;
    private int size;
    private Address address;
    private String[] rooms;

    public House(boolean privateHouse, int size, Address address, String[] rooms) {
        this.privateHouse = privateHouse;
        this.size = size;
        this.address = address;
        this.rooms = rooms;
    }

    public boolean isPrivateHouse() {
        return privateHouse;
    }

    public int getSize() {
        return size;
    }

    public Address getAddress() {
        return address;
    }

    public String[] getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "House{"
                + "privateHouse=" + privateHouse
                + ", size=" + size
                + ", address=" + address
                + ", rooms=" + Arrays.toString(rooms)
                + '}';
    }
}
