package controller;

import database.DatabaseConnectionHandler;
import database.DirectorHandler;
import delegates.LoginWindowDelegate;
import model.Director;

import ui.HomeWindow;
import ui.LoginWindow;

import javax.xml.crypto.Data;
import java.sql.Array;

import ui.*;

import java.util.ArrayList;

public class F1_Manager implements LoginWindowDelegate {
    private DatabaseConnectionHandler dbHandler = null;
    private LoginWindow loginWindow = null;
    private InsertFrame insertFrame;
    private DeleteFrame deleteFrame;
    private UpdateFrame updateFrame;
    private FunctionsFrame functionsFrame;

    public F1_Manager() {
        dbHandler = DatabaseConnectionHandler.getHandler();
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
            insertFrame = new InsertFrame();
            deleteFrame = new DeleteFrame();
            updateFrame = new UpdateFrame();
            functionsFrame = new FunctionsFrame();
            DirectorHandler dh = dbHandler.getDirectorHandler();

            Director testDir = new Director(50, "testFname", "testLname");
            dh.insertDirector(testDir);
            Director testDir2 = new Director(51, "", "");
            //dh.deleteDirector(testDir2);
            Director testDir3 = new Director(52, "updateTest", "updateTest2");
            dh.updateDirector(testDir3);
            dbHandler.join("DIRECTOR", "PITCREW", "DIRECTOR.directorID = PITCREW.pitcrewID");
            ArrayList<String> attributes = new ArrayList<>();
            attributes.add("FIRSTNAME");
            attributes.add("LASTNAME");
            attributes.add("ATHLETEID");
            attributes.add("TEAMID");
            attributes.add("DOB");
            attributes.add("NRACES");
            attributes.add("STARTDATE");
            Object[][] array = dbHandler.project("ATHLETE", attributes);

            // System.out.println(array);
            HomeWindow hw = new HomeWindow(this);

        } else {
            loginWindow.handleLoginFailed();
        }
    }

    public DatabaseConnectionHandler getDbHandler() {
        return dbHandler;
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