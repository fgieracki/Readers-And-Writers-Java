package com.fgieracki;

import java.util.LinkedList;
import java.util.Queue;

import static com.fgieracki.Colors.*;

/**
 * Represents a queue of people waiting to enter the library
 */
@SuppressWarnings("java:S106")
public class LibraryQueue {
    private static final Queue<Person> queue = new LinkedList<>();

    public static void addPersonToQueue(Person person){
        queue.add(person);
        System.out.println(ANSI_BLUE + person.getName() + " joined the queue, queue status: " + queue.size() +  ANSI_RESET);
    }

    public Person checkNextPerson(){
        return queue.peek();
    }

    public void getPersonFromQueue(){
        queue.poll();
    }

    public boolean isNotEmpty(){
        return !queue.isEmpty();
    }

    public int size(){
        return queue.size();
    }
}
