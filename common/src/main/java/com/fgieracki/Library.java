package com.fgieracki;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Represents a library
 * Has a capacity defined in the constructor
 * Has a semaphore to control the number of people in the library
 * including the number of people who want to read and write
 * Implements basic methods to control the library,
 * mainly getters and setters
 */
@SuppressWarnings("java:S106")
public class Library {
    private final int capacity;
    private final Semaphore semaphore;
    private final ArrayList<Person> peopleInside = new ArrayList<>();
    boolean isOpen = false;

    Library(int capacity){
        this.isOpen = true;
        this.capacity = capacity;
        semaphore = new Semaphore(capacity, true);
    }

    public Semaphore semaphore(){
        return semaphore;
    }

    public void close(){
        isOpen = false;
    }
    public boolean isOpen() {
        return isOpen;
    }

    public void addPerson(Person person){
        peopleInside.add(person);
    }

    public void removePerson(Person person){
        peopleInside.remove(person);
    }

    public boolean isFull(){
        return peopleInside.size() == capacity;
    }

}
