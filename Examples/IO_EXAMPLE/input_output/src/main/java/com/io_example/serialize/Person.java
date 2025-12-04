package com.io_example.serialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

    // Serial version UID (optional but recommended)
    private final long serialVersionUID = 1L;
    private String name;
    private int age;
    private transient String password; // Transient == not serialized
    private Date birthDate;

    public Person(String name, int age, String password, Date birthDate) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.birthDate = birthDate;
    }

    // custom serialization (optional)
    private void writeObject(ObjectOutputStream oos) throws IOException{
        oos.defaultWriteObject(); // default serialization
        // add custom serialization here
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject(); // Default deserialization
        // Add custom deserialization here
    }

    @Override
    public String toString() {
        return "Person{" +
                "serialVersionUID=" + serialVersionUID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

}