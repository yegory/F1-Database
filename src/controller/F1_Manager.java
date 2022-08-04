package controller;

import database.DatabaseConnectionHandler;
import delegates.LoginWindowDelegate;
import delegates.TerminalTransactionsDelegate;
import model.Entity.SponsorModel;
import ui.HomeWindow;
import ui.LoginWindow;

public class F1_Manager implements LoginWindowDelegate, TerminalTransactionsDelegate {
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

            HomeWindow hw = HomeWindow.getInstance();
//            TerminalTransactions transaction = new TerminalTransactions();
//            transaction.setupDatabase(this);
//            transaction.showMainMenu(this);

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

    @Override
    public void databaseSetup() {
        dbHandler.databaseSetup();
    }

    @Override
    public void deleteSponsor(int sponsorID) {

    }

    @Override
    public void insertSponsor(SponsorModel model) {
        dbHandler.insertSponsor(model);
    }

    @Override
    public void showSponsors() {
        SponsorModel[] models = dbHandler.getBranchInfo();

        for (int i = 0; i < models.length; i++) {
            SponsorModel model = models[i];

            // simplified output formatting; truncation may occur
            System.out.printf("%-10.10s", model.getSponsorID());
            if (model.getName() == null) {
                System.out.printf("%-20.20s", " ");
            } else {
                System.out.printf("%-20.20s", model.getName());
            }
            System.out.println();
        }

    }

    @Override
    public void updateSponsor(int sponsorID, String newName) {

    }

    @Override
    public void terminalTransactionsFinished() {
        dbHandler.close();
        dbHandler = null;

        System.exit(0);
    }
}