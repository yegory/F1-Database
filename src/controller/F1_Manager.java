package controller;

import database.DatabaseConnectionHandler;
import database.DirectorHandler;
import delegates.LoginWindowDelegate;
import model.Director;
import ui.LoginWindow;

public class F1_Manager implements LoginWindowDelegate {
    private DatabaseConnectionHandler dbHandler = null;
    private DirectorHandler directorHandler = null;
    private LoginWindow loginWindow = null;

    public F1_Manager() {
        dbHandler = DatabaseConnectionHandler.getHandler();
        directorHandler = new DirectorHandler(dbHandler.getConnection());
    }

    @Override
    /**
     * LoginWindowDelegate Implementation
     *
     * connects to Oracle database with supplied username and password
     */
    public void login(String username, String password) {
        boolean didConnect = dbHandler.login(username, password);

        if (didConnect) {
            // Once connected, remove login window and start text transaction flow
            loginWindow.dispose();

            /*
            TerminalTransactions transaction = new TerminalTransactions();
            transaction.setupDatabase(this);
            transaction.showMainMenu(this);
             */
            Director testDir = new Director(50, "testFname", "testLname");
            directorHandler.insertDirector(testDir);
            Director testDir2 = new Director(50, "", "");
            //directorHandler.deleteDirector(testDir2);
            Director testDir3 = new Director(50, "updateTest", "updateTest2");
            directorHandler.updateDirector(testDir3);
            dbHandler.join("DIRECTOR", "PITCREW", "DIRECTOR.directorID = PITCREW.pitcrewID");

        } else {
            loginWindow.handleLoginFailed();
        }
    }

    private void start() {
        loginWindow = new LoginWindow();
        loginWindow.showFrame(this);
    }

    /**
     * Main method called at launch time
     */
    public static void main(String args[]) {
        F1_Manager f1 = new F1_Manager();
        f1.start();
    }
}