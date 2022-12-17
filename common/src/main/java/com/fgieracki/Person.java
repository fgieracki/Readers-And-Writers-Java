package com.fgieracki;

import java.util.Objects;

import static com.fgieracki.Colors.*;

@SuppressWarnings("java:S106")
abstract class Person extends Thread {

    abstract int getRequiredSlots();
    abstract String getType();

    private volatile boolean canEnter = false;
    private Library library;
    private LibraryQueue libraryQueue;

    public void allowToEnter(){
        canEnter = true;
    }

    public void start(Library library, LibraryQueue libraryQueue){
        this.library = library;
        this.libraryQueue = libraryQueue;
        super.start();
    }

    private void enterLibrary(){
        try {
            library.semaphore.acquire(getRequiredSlots());
            libraryQueue.getPersonFromQueue();
            library.addPerson(this);
            canEnter = false;

            System.out.println(ANSI_GREEN + this.getName() + " entered the library" + ANSI_RESET);
            int sleepTime = (int) (Math.random() * 10000);
            Thread.sleep(sleepTime);
            exitLibrary();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void exitLibrary(){
        try {
            library.removePerson(this);
            library.semaphore.release(getRequiredSlots());
            System.out.println(ANSI_RED + this.getName() + " exited the library" + ANSI_RESET);
            int sleepTime = (int) (Math.random() * 10000);
            Thread.sleep(sleepTime);
            joinQueue();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

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
            e.printStackTrace();
        }
        super.run();
    }
}