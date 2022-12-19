package com.fgieracki;

import static com.fgieracki.Colors.*;
import static java.lang.Thread.sleep;
@SuppressWarnings("java:S106")
public class App {
    Library library;
    LibraryQueue libraryQueue;

    int readers;
    int writers;
    int operations = 0;
    int maxOperations = 0;


    App(int libraryCapacity, int readers, int writers, int maxOperations){
        library = new Library(libraryCapacity);
        libraryQueue = new LibraryQueue();
        this.readers = readers;
        this.writers = writers;
        this.maxOperations = maxOperations;

        addPeopleToQueue();
    }

    private void addPeopleToQueue(){
        addReadersToQueue();
        addWritersToQueue();
    }

    private void addReadersToQueue(){
        for (int i = 0; i < readers; i++) {
            Reader reader = new Reader("Reader " + (i+1));
            reader.start(library, libraryQueue);
            LibraryQueue.addPersonToQueue(reader);
        }
    }

    private void addWritersToQueue(){
        for (int i = 0; i < writers; i++) {
            Writer writer = new Writer("Writer " + (i+1));
            writer.start(library, libraryQueue);
            LibraryQueue.addPersonToQueue(writer);
        }
    }


    protected void start(){
        while(library.isOpen()){
            if(libraryQueue.isNotEmpty()){
                try {
                Person person = libraryQueue.checkNextPerson();
                System.out.println(ANSI_YELLOW + person.getName() + " is first in the queue" + ANSI_RESET);
                person.allowToEnter();
                operations++;
                    sleep(1000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();

                }
            }
            else{
                try {
                    System.out.println(ANSI_PURPLE + "Library is full or queue is empty!\n" +
                            "Waiting for people" + ANSI_RESET);
                    sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if(operations == maxOperations){
                library.close();
            }
        }
    }


    public static void main(String[] args) {
        App app = new App(5, 5, 1, -1);
        app.start();
    }

}

