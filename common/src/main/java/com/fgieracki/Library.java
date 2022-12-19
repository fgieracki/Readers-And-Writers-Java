package com.fgieracki;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

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
