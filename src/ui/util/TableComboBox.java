package ui.util;

import database.DatabaseConnectionHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableComboBox extends JComboBox implements ActionListener {

    private String[] modelClasses = {"Select table", "Directors", "Athletes", "Teams",  "Cars", "Car Models", "Events", "Pit Crew", "Results",
            "Lap Times", "Tracks", "Track zip codes", "Drive In", "Results scoring", "Sponsor sponsors Team", "Sponsors",
            "Sponsor sponsors Event", "Sponsor sponsors Team", "Practices", "Season races", "Exhibitions", "Driver operates"};

    private final JComboBox comboBox;
    private final JPanel homeTopPanel;
    private final JPanel homeMiddlePanel;
    private final Table table;
    private AttributeCheckbox attributeCheckbox;
    public TableComboBox(Table table, JPanel homeTopPanel, JPanel homeMiddlePanel) {
        this.table = table;
        this.homeTopPanel = homeTopPanel;
        this.homeMiddlePanel = homeMiddlePanel;

        comboBox = new JComboBox(modelClasses);
        comboBox.setPreferredSize(new Dimension(220, 50));
        comboBox.setMaximumSize(new Dimension(220, 100));
        comboBox.addActionListener(this);
        comboBox.setMaximumRowCount(25);
        homeTopPanel.add(comboBox);

        attributeCheckbox = new AttributeCheckbox(homeMiddlePanel);
    }

    public class AttributeCheckbox extends JCheckBox implements ActionListener {
        private List<JCheckBox> checkBoxes;
        private JPanel parentPanel;

        public AttributeCheckbox(JPanel parent) {
            parentPanel = parent;
            checkBoxes = new ArrayList<>();
        }

        private void addCheckBox(String text) {
            JCheckBox checkBox = new JCheckBox(text);
            checkBox.addActionListener(this);
            checkBox.setVisible(true);
            parentPanel.add(checkBox);
            checkBoxes.add(checkBox);
        }
        public void addCheckBoxes(ArrayList<String> cols) {
            for (String attributeName: cols) {
                addCheckBox(attributeName);
            }


        }

        public void removeAllCheckBoxes() {
            for (int i=0; i< checkBoxes.size(); i++) {
                System.out.println(checkBoxes.get(i).getText());
                homeMiddlePanel.remove(checkBoxes.get(i));
            }
            checkBoxes = new ArrayList<>();
            homeMiddlePanel.repaint();
            homeMiddlePanel.revalidate();
        }


        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==comboBox) {
            if (modelClasses[0] == "Select table") {
                modelClasses = Arrays.copyOfRange(modelClasses, 1, modelClasses.length);
                comboBox.removeItem("Select table");
            }
            if (comboBox.getSelectedItem() == "Directors")
                handleDirectorView();
            else if (comboBox.getSelectedItem() == "Athletes")
                handleAthleteView();
            else if (comboBox.getSelectedItem() == "Teams")
                handleTeamView();
            else if (comboBox.getSelectedItem() == "Cars")
                handleCarView();
            else if (comboBox.getSelectedItem() == "Car Models")
                handleCarModelView();
            else if (comboBox.getSelectedItem() == "Events")
                handleEventView();

            else if (comboBox.getSelectedItem() == "Pit Crew")
                handlePitCrewView();

            else if (comboBox.getSelectedItem() == "Results")
                handleResultsView();
            else if (comboBox.getSelectedItem() == "Lap Times")
                handleLapTimeView();

            else if (comboBox.getSelectedItem() == "Tracks")
                handleTrackView();
            else if (comboBox.getSelectedItem() == "Track zip codes")
                handleTrackZipCodeView();


            else if (comboBox.getSelectedItem() == "Drive In")
                handleDriveInView();


            else if (comboBox.getSelectedItem() == "Results scoring")
                handleResultsScoringView();

            else if (comboBox.getSelectedItem() == "Sponsors")
                handleSponsorView();
            else if (comboBox.getSelectedItem() == "Sponsor sponsors Team")
                handleSponsorsTeamView();
            else if (comboBox.getSelectedItem() == "Sponsor sponsors Event")
                handleSponsorsEventView();


            else if (comboBox.getSelectedItem() == "Practices")
                handlePracticeView();
            else if (comboBox.getSelectedItem() == "Season races")
                handleSeasonRaceView();
            else if (comboBox.getSelectedItem() == "Exhibitions")
                handleExhibitionView();

            else if (comboBox.getSelectedItem() == "Driver operates")
                handleOperateView();
            else {
                System.out.println(comboBox.getSelectedItem() + " not in there");
            }
        }
    }

//    private void print2DArray(Object[][] outputData) {
//        for (int i = 0; i < outputData.length; i++) {
//            for (int j = 0; j < outputData[0].length; j++) {
//                System.out.print(outputData[i][j].toString() + " ");
//            }
//            System.out.println("");
//        }
//    }
    private void handleTable(String table_name, ArrayList<String> columns) {
        Object[][] result = DatabaseConnectionHandler.getHandler().project(table_name, columns);
        //print2DArray(result);
        int numberOfElementsInArray = result.length; // number of rows in result
        //Object[][] columnData = Arrays.copyOfRange(result, 0, 1);
        Object[][] rowData = Arrays.copyOfRange(result, 1, numberOfElementsInArray);


        table.clearTable();
        table.addColumns(columns);
        table.addRows(rowData);
        attributeCheckbox.removeAllCheckBoxes();
        attributeCheckbox.addCheckBoxes(columns);
    }


    private void handleDirectorView() {

        ArrayList<String> cols = new ArrayList<>() {{
            add("directorID");
            add("firstName");
            add("lastName");}};
        handleTable("Director", cols);
    }
    private void handleAthleteView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("athleteID");
            add("teamID");
            add("firstName");
            add("lastName");
            add("DOB");
            add("nRaces");
            add("startDate");
            add("endDate");}};
        handleTable("Athlete", cols);
    }

    private void handleCarView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("carID");
            add("mileage");
            add("carModel");
            add("teamID");}};
        handleTable("Car", cols);
    }

    private void handleCarModelView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("carModel");
            add("weight");
            add("topSpeed");
            add("horsepower");}};
        handleTable("carModel", cols);
    }

    private void handleEventView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("eventID");
            add("trackID");
            add("eventDate");
            add("eventName");
            add("laps");}};
        handleTable("Event", cols);
    }

    private void handleTrackView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("trackID");
            add("trackName");
            add("length");
            add("addressNumber");
            add("street");
            add("zipCode");}};
        handleTable("Track", cols);
    }

    private void handlePitCrewView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("pitCrewID");
            add("role");
            add("firstName");
            add("lastName");
            add("teamID");
            add("startDate");
            add("endDate");}};
        handleTable("PitCrew", cols);
    }

    private void handleTeamView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("teamID");
            add("directorID");
            add("teamName");
            add("startDate");
            add("endDate");}};
        handleTable("Team", cols);
    }
    private void handleResultsView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("resultID");
            add("athleteID");
            add("eventID");
            add("trackID");
            add("position");
            add("bestPitStop");
            add("bestLap");}};
        handleTable("Results", cols);
    }

    private void handleLapTimeView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("lapNumber");
            add("resultID");
            add("time");}};
        handleTable("LapTime", cols);
    }
    private void handleResultsScoringView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("position");
            add("points");}};
        handleTable("ResultsScoring", cols);
    }
    private void handleTrackZipCodeView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("zipCode");
            add("city");
            add("country");
            add("province");}};
        handleTable("TrackZipCode", cols);
    }
    private void handleSponsorView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("sponsorID");
            add("name");}};
        handleTable("Sponsor", cols);
    }
    private void handlePracticeView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("eventID");
            add("trackID");
            add("bestLap");;}};
        handleTable("Practice", cols);
    }
    private void handleSeasonRaceView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("eventID");
            add("trackID");
            add("availablePoints");
            add("winner");}};
        handleTable("SeasonRace", cols);
    }
    private void handleExhibitionView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("eventID");
            add("trackID");
            add("winner");}};
        handleTable("Exhibition", cols);
    }
    private void handleSponsorsTeamView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("sponsorID");
            add("teamID");
            add("dealValue");;}};
        handleTable("SponsorsTeam", cols);
    }
    private void handleSponsorsEventView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("sponsorID");
            add("eventID");
            add("trackID");
            add("dealValue");;}};
        handleTable("SponsorsEvent", cols);
    }
    private void handleOperateView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("carID");
            add("athleteID");
            add("eventID");
            add("trackID");;}};
        handleTable("operate", cols);
    }
    private void handleDriveInView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("athleteID");
            add("eventID");
            add("trackID");}};
        handleTable("DriveIn", cols);
    }
}
