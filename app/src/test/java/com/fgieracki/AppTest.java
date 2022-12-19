package com.fgieracki;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void initTest() {
        App app = new App(10, 5, 5, 15);
        app.start();
        assertTrue(true);
    }

    @Test
    public void appTest() {
        App app = new App(10, 5, 5, 10);
        assertEquals(10, app.library.semaphore().availablePermits());
    }

    @Test
    public void queueTest() {
        App app = new App(10, 5, 5, 10);
        assertEquals("Reader", app.libraryQueue.checkNextPerson().getType());
    }
}
