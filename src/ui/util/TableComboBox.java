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

    public final JComboBox comboBox;
    private final JPanel homeTopPanel;
    private final JPanel homeMiddlePanel;
    private final Table table;
    private AttributeCheckbox attributeCheckbox;
    public ArrayList<String> selectedColumns;
    private ArrayList<String> AllColumns; //all checkbox columns even if user checked off
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

        attributeCheckbox = new AttributeCheckbox();
        selectedColumns = new ArrayList<>();
        AllColumns = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==comboBox) {
            if (modelClasses[0] == "Select table") {
                modelClasses = Arrays.copyOfRange(modelClasses, 1, modelClasses.length);
                comboBox.removeItem("Select table");
            }
            SwitchCombo(comboBox.getSelectedItem().toString());
        }
    }

    public class AttributeCheckbox extends JCheckBox implements ActionListener {
        private List<JCheckBox> checkBoxes;
        public AttributeCheckbox() {
            checkBoxes = new ArrayList<>();
        }

        private void addCheckBox(String text) {
            JCheckBox checkBox = new JCheckBox(text);
            checkBox.addActionListener(this);
            checkBox.setVisible(true);
            if (inArrayHelper(selectedColumns, text) != -1)
                checkBox.setSelected(true);
            homeMiddlePanel.add(checkBox);
            checkBoxes.add(checkBox);
        }
        public void addCheckBoxes(ArrayList<String> cols) {
            for (String attributeName: cols)
                addCheckBox(attributeName);
        }

        public void removeAllCheckBoxes() {
            for (int i=0; i< checkBoxes.size(); i++) {
                homeMiddlePanel.remove(checkBoxes.get(i));
            }
            checkBoxes = new ArrayList<>();
            homeMiddlePanel.repaint();
            homeMiddlePanel.revalidate();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String checkBoxPressed = e.getActionCommand();
            int index = inArrayHelper(selectedColumns, checkBoxPressed);

            if (index == -1) { // checkbox column name was not in selectedColumn
                selectedColumns.add(checkBoxPressed);
            } else {
                selectedColumns.remove(index);
            }

            handleTable(DetermineTable(comboBox.getSelectedItem().toString()), selectedColumns, "");
        }
    }

    // returns index of String in array if found, else -1
    private int inArrayHelper(ArrayList<String> arr, String text) {
        for (int i=0; i<arr.size(); i++) {
            if (arr.get(i) == text) {
                return i;
            }
        }
        return -1;
    }

    public void handleTable(String table_name, ArrayList<String> columns, String criteria) {
        if (columns.size() == 0) System.out.println("ERROR");

        Object[][] result = DatabaseConnectionHandler.getHandler().select(table_name, columns, criteria);
        int numberOfElementsInArray = result.length; // number of rows in result
        Object[][] rowData = Arrays.copyOfRange(result, 1, numberOfElementsInArray);

        table.clearTable();
        table.addColumns(columns);
        table.addRows(rowData);

        attributeCheckbox.removeAllCheckBoxes();
        attributeCheckbox.addCheckBoxes(AllColumns);
    }

    private void handleDirectorView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("directorID");
            add("firstName");
            add("lastName");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("Director", cols, "");
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
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("Athlete", cols, "");
    }

    private void handleCarView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("carID");
            add("mileage");
            add("carModel");
            add("teamID");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("Car", cols, "");
    }

    private void handleCarModelView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("carModel");
            add("weight");
            add("topSpeed");
            add("horsepower");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("carModel", cols, "");
    }

    private void handleEventView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("eventID");
            add("trackID");
            add("eventDate");
            add("eventName");
            add("laps");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("Event", cols, "");
    }

    private void handleTrackView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("trackID");
            add("trackName");
            add("length");
            add("addressNumber");
            add("street");
            add("zipCode");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("Track", cols, "");
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
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("PitCrew", cols, "");
    }

    private void handleTeamView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("teamID");
            add("directorID");
            add("teamName");
            add("startDate");
            add("endDate");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("Team", cols, "");
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
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("Results", cols, "");
    }

    private void handleLapTimeView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("lapNumber");
            add("resultID");
            add("time");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("LapTime", cols, "");
    }
    private void handleResultsScoringView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("position");
            add("points");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("ResultsScoring", cols, "");
    }
    private void handleTrackZipCodeView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("zipCode");
            add("city");
            add("country");
            add("province");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("TrackZipCode", cols, "");
    }
    private void handleSponsorView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("sponsorID");
            add("name");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("Sponsor", cols, "");
    }
    private void handlePracticeView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("eventID");
            add("trackID");
            add("bestLap");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("Practice", cols, "");
    }
    private void handleSeasonRaceView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("eventID");
            add("trackID");
            add("availablePoints");
            add("winner");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("SeasonRace", cols, "");
    }
    private void handleExhibitionView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("eventID");
            add("trackID");
            add("winner");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("Exhibition", cols, "");
    }
    private void handleSponsorsTeamView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("sponsorID");
            add("teamID");
            add("dealValue");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("SponsorsTeam", cols, "");
    }
    private void handleSponsorsEventView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("sponsorID");
            add("eventID");
            add("trackID");
            add("dealValue");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("SponsorsEvent", cols, "");
    }
    private void handleOperateView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("carID");
            add("athleteID");
            add("eventID");
            add("trackID");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("operate", cols, "");
    }
    private void handleDriveInView() {
        ArrayList<String> cols = new ArrayList<>() {{
            add("athleteID");
            add("eventID");
            add("trackID");}};
        AllColumns = new ArrayList(cols);
        selectedColumns = cols;
        handleTable("DriveIn", cols, "");
    }

    private void SwitchCombo(String text) {
        if (comboBox.getSelectedItem() == "Directors") handleDirectorView();
        else if (comboBox.getSelectedItem() == "Athletes") handleAthleteView();
        else if (comboBox.getSelectedItem() == "Teams") handleTeamView();
        else if (comboBox.getSelectedItem() == "Cars") handleCarView();
        else if (comboBox.getSelectedItem() == "Car Models") handleCarModelView();
        else if (comboBox.getSelectedItem() == "Events") handleEventView();
        else if (comboBox.getSelectedItem() == "Pit Crew") handlePitCrewView();
        else if (comboBox.getSelectedItem() == "Results") handleResultsView();
        else if (comboBox.getSelectedItem() == "Lap Times") handleLapTimeView();
        else if (comboBox.getSelectedItem() == "Tracks") handleTrackView();
        else if (comboBox.getSelectedItem() == "Track zip codes") handleTrackZipCodeView();
        else if (comboBox.getSelectedItem() == "Drive In") handleDriveInView();
        else if (comboBox.getSelectedItem() == "Results scoring") handleResultsScoringView();
        else if (comboBox.getSelectedItem() == "Sponsors") handleSponsorView();
        else if (comboBox.getSelectedItem() == "Sponsor sponsors Team") handleSponsorsTeamView();
        else if (comboBox.getSelectedItem() == "Sponsor sponsors Event") handleSponsorsEventView();
        else if (comboBox.getSelectedItem() == "Practices") handlePracticeView();
        else if (comboBox.getSelectedItem() == "Season races") handleSeasonRaceView();
        else if (comboBox.getSelectedItem() == "Exhibitions") handleExhibitionView();
        else if (comboBox.getSelectedItem() == "Driver operates") handleOperateView();
        else System.out.println(comboBox.getSelectedItem() + " not in there");
    }

    public String DetermineTable(String text) {
        if (comboBox.getSelectedItem() == "Directors") return "DIRECTOR";
        else if (comboBox.getSelectedItem() == "Athletes") return "ATHLETE";
        else if (comboBox.getSelectedItem() == "Teams") return "TEAM";
        else if (comboBox.getSelectedItem() == "Cars") return "CAR";
        else if (comboBox.getSelectedItem() == "Car Models") return "CARMODEL";
        else if (comboBox.getSelectedItem() == "Events") return "EVENT";
        else if (comboBox.getSelectedItem() == "Pit Crew") return "PITCREW";
        else if (comboBox.getSelectedItem() == "Results") return "RESULTS";
        else if (comboBox.getSelectedItem() == "Lap Times") return "LAPTIME";
        else if (comboBox.getSelectedItem() == "Tracks") return "TRACK";
        else if (comboBox.getSelectedItem() == "Track zip codes") return "TRACKZIPCODE";
        else if (comboBox.getSelectedItem() == "Drive In") return "DRIVEIN";
        else if (comboBox.getSelectedItem() == "Results scoring") return "RESULTSSCORING";
        else if (comboBox.getSelectedItem() == "Sponsors") return "SPONSOR";
        else if (comboBox.getSelectedItem() == "Sponsor sponsors Team") return "SPONSORSTEAM";
        else if (comboBox.getSelectedItem() == "Sponsor sponsors Event") return "SPONSORSEVENT";
        else if (comboBox.getSelectedItem() == "Practices") return "PRACTICE";
        else if (comboBox.getSelectedItem() == "Season races") return "SEASONRACE";
        else if (comboBox.getSelectedItem() == "Exhibitions") return "EXHIBITION";
        else if (comboBox.getSelectedItem() == "Driver operates") return "OPERATE";
        else return comboBox.getSelectedItem() + " not in there";
    }
}
