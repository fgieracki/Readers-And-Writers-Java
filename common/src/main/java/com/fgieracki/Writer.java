package com.fgieracki;

/**
 * Subclass of Person
 * Represents a person who wants to write a book
 * Requires 5 slots in the library
 */
public class Writer extends Person {
    public Writer(String name) {
        this.setName(name);
    }

    @Override
    public String getType() {
        return "Writer";
    }

    @Override
    public int getRequiredSlots(){
        return 5;
    }

}
