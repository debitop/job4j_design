package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlAttribute
    private String street;
    @XmlAttribute
    private String town;

    public Address() {
    }

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
