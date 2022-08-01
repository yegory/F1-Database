-- ALL TABLES INCLUDED, no syntax errors
-- Need to add ON DELETE/UPDATE CASCADE/NO ACTION/ to the foreign key fields
-- and Constraints (didn't cover in class yet)

CREATE TABLE CarModel
(
    carModel   CHAR(50),
    weight     FLOAT,
    topSpeed   FLOAT,
    horsepower INTEGER,
    PRIMARY KEY (carModel)
);

CREATE TABLE Director
(
    directorID INTEGER,
    firstName  CHAR(50),
    lastName   CHAR(50),
    PRIMARY KEY (directorID)
);

CREATE TABLE ResultsScoring
(
    position INTEGER,
    points   INTEGER,
    PRIMARY KEY (position)
);

CREATE TABLE Sponsor
(
    sponsorID INTEGER,
    name      CHAR(50),
    PRIMARY KEY (sponsorID)
);

CREATE TABLE TrackZipCode
(
    zipCode  CHAR(50),
    city     CHAR(50),
    country  CHAR(56),
    province CHAR(30),
    PRIMARY KEY (zipCode)
);

CREATE TABLE Team
(
    teamID     INTEGER,
    directorID INTEGER NOT NULL,
    teamName   CHAR(50),
    startDate  DATE,
    endDate    DATE,
    points     INTEGER,
    PRIMARY KEY (teamID),
    FOREIGN KEY (directorID) REFERENCES Director,
    UNIQUE (directorID)
);

CREATE TABLE Athlete
(
    athleteID INTEGER,
    teamID    INTEGER,
    firstName CHAR(50),
    lastName  CHAR(50),
    DOB       DATE,
    startDate DATE,
    endDate   DATE,
    nRaces    INTEGER,
    PRIMARY KEY (athleteID),
    FOREIGN KEY (teamID) REFERENCES Team
);

CREATE TABLE Track
(
    trackID       INTEGER,
    trackName     CHAR(80),
    length        FLOAT,
    addressNumber INTEGER,
    street        CHAR(80),
    zipCode       CHAR(50) NOT NULL,
    PRIMARY KEY (trackID),
    FOREIGN KEY (zipCode) REFERENCES TrackZipCode
);

CREATE TABLE Event
(
    eventID INTEGER,
    trackID INTEGER NOT NULL,
    date    DATE,
    name    CHAR(50),
    laps    INTEGER,
    PRIMARY KEY (eventID, trackID),
    FOREIGN KEY (trackID) REFERENCES Track
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE SeasonRace
(
    eventID         INTEGER,
    trackID         INTEGER,
    availablePoints FLOAT,
    winner          CHAR(50),
    PRIMARY KEY (eventID, trackID),
    FOREIGN KEY (eventID, trackID) REFERENCES Event
);

CREATE TABLE Car
(
    carID    INTEGER,
    mileage  INTEGER,
    carModel CHAR(50) NOT NULL,
    teamID   INTEGER,
    PRIMARY KEY (carID),
    FOREIGN KEY (carModel) REFERENCES CarModel,
    FOREIGN KEY (teamID) REFERENCES Team
);

CREATE TABLE PitCrew
(
    pitCrewID INTEGER,
    role      CHAR(20),
    firstName CHAR(50),
    lastName  CHAR(50),
    teamID    INTEGER,
    startDate DATE,
    endDate   DATE,
    PRIMARY KEY (pitCrewID),
    FOREIGN KEY (teamID) REFERENCES Team (teamID)
);

CREATE TABLE DriveIn
(
    athleteID INTEGER,
    eventID   INTEGER,
    trackID   INTEGER,
    PRIMARY KEY (athleteID, eventID, trackID),
    FOREIGN KEY (athleteID) REFERENCES Athlete,
    FOREIGN KEY (eventID, trackId) REFERENCES Event (eventID, trackId)
);

CREATE TABLE Exhibition
(
    eventID INTEGER,
    trackID INTEGER,
    winner  CHAR(50),
    PRIMARY KEY (eventID, trackID),
    FOREIGN KEY (eventID, trackID) REFERENCES Event
);

CREATE TABLE Results
(
    resultID    INTEGER,
    athleteID   INTEGER NOT NULL,
    eventID     INTEGER NOT NULL,
    trackID     INTEGER NOT NULL,
    position    INTEGER,
    bestPitStop TIME,
    bestLap     TIME,
    PRIMARY KEY (resultID),
    FOREIGN KEY (athleteID) REFERENCES Athlete,
    FOREIGN KEY (eventID, trackID) REFERENCES Event (eventID, trackID),
    FOREIGN KEY (position) REFERENCES ResultsScoring
);

CREATE TABLE LapTime
(
    lapNumber INTEGER,
    resultID  INTEGER NOT NULL,
    time      TIME,
    PRIMARY KEY (lapNumber, resultID),
    FOREIGN KEY (resultID) REFERENCES Results
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE SponsorsTeam
(
    sponsorID INTEGER,
    teamID    INTEGER,
    dealValue INTEGER,
    PRIMARY KEY (sponsorID, teamID),
    FOREIGN KEY (sponsorID) REFERENCES Sponsor,
    FOREIGN KEY (teamID) REFERENCES Team
);

CREATE TABLE SponsorsEvent
(
    sponsorID INTEGER,
    eventID   INTEGER,
    trackID   INTEGER,
    dealValue INTEGER,
    PRIMARY KEY (sponsorID, eventID, trackID),
    FOREIGN KEY (sponsorID) REFERENCES Sponsor,
    FOREIGN KEY (eventID, trackID) REFERENCES Event (eventID, trackID)
);

CREATE TABLE Practice
(
    eventID INTEGER,
    trackID INTEGER,
    bestLap TIME,
    PRIMARY KEY (eventID, trackID),
    FOREIGN KEY (eventID, trackID) REFERENCES Event (eventID, trackID)
);

CREATE TABLE operate
(
    carID     INTEGER,
    eventID   INTEGER,
    trackID   INTEGER,
    athleteID INTEGER,
    PRIMARY KEY (carID, eventID, trackID, athleteID),
    FOREIGN KEY (carID) REFERENCES Car,
    FOREIGN KEY (athleteID) REFERENCES Athlete,
    FOREIGN KEY (eventID, trackID) REFERENCES Event (eventID, trackID),
    UNIQUE (athleteID, eventID, trackID)
);