package com.fgieracki;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

@SuppressWarnings("java:S106")
public class Library {
    private int capacity;
    private boolean isOpen = true;
    public Semaphore semaphore;
    private ArrayList<Person> peopleInside = new ArrayList<>();

    Library(int capacity){
        this.capacity = capacity;
        semaphore = new Semaphore(capacity);
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
