package com.fgieracki;

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
