package controller;

import database.DatabaseConnectionHandler;
import delegates.LoginWindowDelegate;
import ui.LoginWindow;

public class F1_Manager implements LoginWindowDelegate {
    private DatabaseConnectionHandler dbHandler = null;
    private LoginWindow loginWindow = null;

    public F1_Manager() {
        dbHandler = new DatabaseConnectionHandler();
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
