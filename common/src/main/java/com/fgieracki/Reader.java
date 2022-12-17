package com.fgieracki;

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
