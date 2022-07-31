package controller;

import database.DatabaseConnectionHandler;

public class F1_Manager {
    private DatabaseConnectionHandler dbHandler = null;

    public F1_Manager() {
        dbHandler = new DatabaseConnectionHandler();
    }

    /**
     * Main method called at launch time
     */
    public static void main(String args[]) {
        F1_Manager f1 = new F1_Manager();
    }
}
