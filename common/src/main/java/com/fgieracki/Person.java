package com.fgieracki;

import static com.fgieracki.Colors.*;

/**
 *  This class represents a person in the library.
 *  It is an abstract class, so it cannot be instantiated.
 *  It has two subclasses: Reader and Writer.
 *  It implements Runnable interface, so it can be run in a thread.
 */
@SuppressWarnings({"java:S106", "java:S2140"})
abstract class Person extends Thread {

    abstract int getRequiredSlots();
    abstract String getType();
    private volatile boolean canEnter = false;
    private Library library;
    private LibraryQueue libraryQueue;

    /**
     *  This method is called to allow person to enter the library.
     *  It is synchronized, so only one person can enter at a time.
     *  It calls the method that is specific to the type of person.
     */
    public void allowToEnter(){
        canEnter = true;
    }

    /** This method is called to wake up the thread. */
    public void start(Library library, LibraryQueue libraryQueue){
        this.library = library;
        this.libraryQueue = libraryQueue;
        super.start();
    }

    /**
     * This method is called when a person is allowed to enter the library.
     * It is synchronized, so only one person can enter at a time.
     * It simulates the time that a person spends in the library.
     */
    protected void enterLibrary(){
        try {
            library.semaphore().acquire(getRequiredSlots());
            libraryQueue.getPersonFromQueue();
            library.addPerson(this);
            canEnter = false;

            System.out.println(ANSI_GREEN + this.getName() + " entered the library, queue status: " + libraryQueue.size() + ANSI_RESET);
            int sleepTime = (int) (Math.random() * 2000 + 1000);
            Thread.sleep(sleepTime);
            exitLibrary();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * This method is called when a person leaves the library.
     * It is synchronized, so only one person can leave at a time.
     * It simulates the time that a person needs to rejoin the queue
     * after leaving the library.
     */
    private void exitLibrary(){
        try {
            library.removePerson(this);
            library.semaphore().release(getRequiredSlots());
            int sleepTime = (int) (Math.random() * 2000 + 1000);
            System.out.println(ANSI_RED + this.getName() + " exited the library, queue status: " + libraryQueue.size() + ANSI_RESET);
            Thread.sleep(sleepTime);
            joinQueue();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** method to join the queue */
    private void joinQueue(){
        LibraryQueue.addPersonToQueue(this);
    }

    @Override
    public void run() {
        try {
            while(library.isOpen()){
                if(canEnter){
                    enterLibrary();
                }
            }
        } catch (Exception e) {
            System.out.println("exception: Library is closed.");
        }
        super.run();
    }
}