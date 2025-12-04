package com.io_example.serialize;

import java.io.*;
import java.util.Date;

public class SerializationExample {

    public static void main(String[] args) {
        String filename = "person.ser";

        // create and serialize person
        serializePerson(filename);

        // deserialize person
        deserializePerson(filename);
    }

    private static void serializePerson(String filename) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){

            Person person = new Person("John Doe", 30, "secret123", new Date());

            // Serialize Object
            oos.writeObject(person);

            System.out.println("Serialized: " + person);

            // Serialize Multiple objects

            Person person2 = new Person("Jane Smith", 25, "password456", new Date());
            oos.writeObject(person2);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void deserializePerson(String filename) {

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){

            // Read first object
            Person person1 = (Person) ois.readObject();
            System.out.println("Deserialized 1: " + person1);

            // Read second object
            Person person2 = (Person) ois.readObject();
            System.out.println("Deserialized 2: " + person2);

        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}