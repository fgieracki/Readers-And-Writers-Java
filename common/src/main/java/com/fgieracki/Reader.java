package com.fgieracki;

/**
 * Subclass of Person
 * Represents a person who wants to read a book
 * Requires 1 slot in the library
 */
public class Reader extends Person {

    public Reader(String name) {
        this.setName(name);
    }

    @Override
    public String getType() {
        return "Reader";
    }

    @Override
    public int getRequiredSlots(){
        return 1;
    }
}
