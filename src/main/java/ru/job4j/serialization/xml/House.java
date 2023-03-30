package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "house")
@XmlAccessorType(XmlAccessType.FIELD)
public class House {

    @XmlAttribute
    private boolean privateHouse;
    @XmlAttribute
    private int size;
    private Address address;
    @XmlElementWrapper(name = "rooms")
    @XmlElement(name = "room")
    private String[] rooms;

    public House() {
    }

    public House(boolean privateHouse, int size, Address address, String[] rooms) {
        this.privateHouse = privateHouse;
        this.size = size;
        this.address = address;
        this.rooms = rooms;
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
