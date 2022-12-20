package com.fgieracki;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    @Test
    public void initTest() {
        Person person1 = new Reader("Test");
        assertEquals(1, person1.getRequiredSlots());
        assertEquals("Test", person1.getName());

        Person person2 = new Writer("Test");
        assertEquals(5, person2.getRequiredSlots());
        assertEquals("Test", person2.getName());
    }

    @Test
    public void logicTest() {
        Person person1 = new Reader("Test");
        Library library = new Library(1);
        LibraryQueue libraryQueue = new LibraryQueue();
        person1.start(library);
        person1.enterLibrary();
        assertFalse(library.isFull());
        assertTrue(libraryQueue.isNotEmpty());
    }

}