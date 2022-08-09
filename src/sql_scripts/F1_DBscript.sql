-- ALL TABLES INCLUDED, no syntax errors
-- Need to add ON DELETE/UPDATE CASCADE/NO ACTION/ to the foreign key fields
-- and Constraints (didn't cover in class yet)

drop table SEASONRACE;
drop table PITCREW;
drop table DRIVEIN;
drop table EXHIBITION;
drop table LAPTIME;
drop table RESULTS;
drop table RESULTSSCORING;
drop table SPONSORSTEAM;
drop table SPONSORSEVENT;
drop table SPONSOR;
drop table PRACTICE;
drop table OPERATE;
drop table ATHLETE;
drop table EVENT;
drop table TRACK;
drop table TRACKZIPCODE;
drop table CAR;
drop table CARMODEL;
drop table TEAM;
drop table DIRECTOR;


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
    name CHAR(50),
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
    nRaces    INTEGER,
    startDate DATE,
    endDate   DATE,
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
    eventDate    DATE,
    eventName   CHAR(50),
    laps    INTEGER,
    PRIMARY KEY (eventID, trackID),
    FOREIGN KEY (trackID) REFERENCES Track
        ON DELETE CASCADE
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
    bestPitStop FLOAT,
    bestLap     FLOAT,
    PRIMARY KEY (resultID),
    FOREIGN KEY (athleteID) REFERENCES Athlete,
    FOREIGN KEY (eventID, trackID) REFERENCES Event (eventID, trackID),
    FOREIGN KEY (position) REFERENCES ResultsScoring
);

CREATE TABLE LapTime
(
    lapNumber INTEGER,
    resultID  INTEGER NOT NULL,
    time  FLOAT,
    PRIMARY KEY (lapNumber, resultID),
    FOREIGN KEY (resultID) REFERENCES Results
        ON DELETE CASCADE
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
    bestLap FLOAT,
    PRIMARY KEY (eventID, trackID),
    FOREIGN KEY (eventID, trackID) REFERENCES Event (eventID, trackID)
);

CREATE TABLE operate (
     carID INTEGER,
     athleteID INTEGER,
     eventID INTEGER,
     trackID INTEGER,
     PRIMARY KEY (carID, eventID, trackID, athleteID),
     FOREIGN KEY (carID) REFERENCES Car,
     FOREIGN KEY (athleteID) REFERENCES Athlete,
     FOREIGN KEY (eventID, trackID)  REFERENCES Event(eventID, trackID),
     UNIQUE(athleteID, eventID, trackID)
);

-- Examples a bit boring but every table has at least 5 records so far


INSERT INTO CarModel VALUES
    ('Alfa Romeo C42 Ferrari', 798, 231, 1050);
INSERT INTO CarModel VALUES
    ('Aston Martin AMR22 Mercedes', 850, 230, 1000);
INSERT INTO CarModel VALUES
    ('Ferrari F1-75', 800, 231, 1025);
INSERT INTO CarModel VALUES
    ('Mercedes F1 W13 E Performance', 798, 228, 1030);
INSERT INTO CarModel VALUES
    ('Red Bull RB18 RBPT', 860, 230, 1050);


INSERT INTO Director VALUES
    (1, 'Mike', 'O''Hearn');
INSERT INTO Director VALUES
    (2, 'Ronald', 'McDonald');
INSERT INTO Director VALUES
    (3, 'Kostya', 'Ivanov');
INSERT INTO Director VALUES
    (4, 'Rudolf', 'Hudson');
INSERT INTO Director VALUES
    (5, 'Muhammad', 'Ali');
INSERT INTO Director VALUES
    (6, 'Nicolas', 'Davison');


INSERT INTO ResultsScoring VALUES
    (0, 0);
INSERT INTO ResultsScoring VALUES
    (1, 25);
INSERT INTO ResultsScoring VALUES
    (2, 18);
INSERT INTO ResultsScoring VALUES
    (3, 15);
INSERT INTO ResultsScoring VALUES
    (4, 12);
INSERT INTO ResultsScoring VALUES
    (5, 10);
INSERT INTO ResultsScoring VALUES
    (6, 8);
INSERT INTO ResultsScoring VALUES
    (7, 6);
INSERT INTO ResultsScoring VALUES
    (8, 4);
INSERT INTO ResultsScoring VALUES
    (9, 2);
INSERT INTO ResultsScoring VALUES
    (10, 1);


INSERT INTO Sponsor VALUES
    (1, 'DHL');
INSERT INTO Sponsor VALUES
    (2, 'Crypto.com');
INSERT INTO Sponsor VALUES
    (3, 'Heineken N.V.');
INSERT INTO Sponsor VALUES
    (4, 'Rolex Watch Co. Ltd');
INSERT INTO Sponsor VALUES
    (5, 'Amazon Web Services, Inc.');


INSERT INTO TrackZipCode VALUES
    ('98000', 'Monaco', 'Monaco', 'Jardin Exotique');
INSERT INTO TrackZipCode VALUES
    ('78617', 'Del Valle', 'USA', 'Texas');
INSERT INTO TrackZipCode VALUES
    ('3206', 'Melbourne, Middle Park', 'Australia', 'Victoria');
INSERT INTO TrackZipCode VALUES
    ('40026', 'Imola', 'Italy', 'Emilia-Romagna');
INSERT INTO TrackZipCode VALUES
    ('039596', 'Marina Bay', 'Singapore', 'Downtown Core');
INSERT INTO TrackZipCode VALUES
    ('8500', 'Portimao', 'Portugal', 'Algarve');
INSERT INTO Sponsor VALUES
    (6, 'The University of British Columbia');
INSERT INTO Sponsor VALUES
    (7, 'Microsoft');
INSERT INTO Sponsor VALUES
    (8, 'Samsung');
INSERT INTO Sponsor VALUES
    (9, 'Bridgestone Corporation');
INSERT INTO Sponsor VALUES
    (10, 'NGK Spark Plug Co. Ltd');


INSERT INTO Team VALUES
    (1, 1, 'Alfa Romeo',        TO_DATE('2014-07-07','YYYY-MM-DD'), TO_DATE('2020-01-01','YYYY-MM-DD'), 348);
INSERT INTO Team VALUES
    (2, 2, 'Mercedes-AMG',      TO_DATE('2016-06-18','YYYY-MM-DD'), TO_DATE('2020-02-19','YYYY-MM-DD'), 412);
INSERT INTO Team VALUES
    (3, 3, 'Oracle Red Bull',   TO_DATE('2010-10-07','YYYY-MM-DD'), TO_DATE('2015-08-04','YYYY-MM-DD'), 521);
INSERT INTO Team VALUES
    (4, 4, 'Scuderia Ferrari',  TO_DATE('2020-04-20','YYYY-MM-DD'), TO_DATE('2022-06-19','YYYY-MM-DD'), 281);
INSERT INTO Team VALUES
    (5, 5, 'McLaren',           TO_DATE('2004-06-19','YYYY-MM-DD'), TO_DATE('2014-11-29','YYYY-MM-DD'), 441);


INSERT INTO Athlete VALUES
    (1, 1, 'Kevin',     'Stark',    TO_DATE('1990-07-18','YYYY-MM-DD'),  89, TO_DATE('2014-07-07','YYYY-MM-DD'), TO_DATE('2020-01-01','YYYY-MM-DD'));
INSERT INTO Athlete VALUES
    (2, 2, 'Frank',     'Ocean',    TO_DATE('1994-01-23','YYYY-MM-DD'), 128, TO_DATE('2016-06-18','YYYY-MM-DD'), TO_DATE('2020-02-19','YYYY-MM-DD'));
INSERT INTO Athlete VALUES
    (3, 3, 'Charles',   'Oliviera', TO_DATE('1985-10-07','YYYY-MM-DD'), 234, TO_DATE('2005-10-07','YYYY-MM-DD'), TO_DATE('2015-02-17','YYYY-MM-DD'));
INSERT INTO Athlete VALUES
    (4, 4, 'Rich',      'Brian',    TO_DATE('1993-03-03','YYYY-MM-DD'),  12, TO_DATE('2020-04-20','YYYY-MM-DD'), TO_DATE('2022-06-19','YYYY-MM-DD'));
INSERT INTO Athlete VALUES
    (5, 5, 'Kolton',    'Brown',    TO_DATE('1985-08-17','YYYY-MM-DD'),  66, TO_DATE('2004-05-19','YYYY-MM-DD'), TO_DATE('2014-08-28','YYYY-MM-DD'));


INSERT INTO PitCrew VALUES
    (1, 'Tyre Gunner',  'John',     'Smith',        1, TO_DATE('2021-07-07','YYYY-MM-DD'), TO_DATE('2022-07-07','YYYY-MM-DD'));
INSERT INTO PitCrew VALUES
    (2, 'Tyre Off',     'Yegor',    'Yeryomenko',   2, TO_DATE('2021-11-30','YYYY-MM-DD'), TO_DATE('2022-03-13','YYYY-MM-DD'));
INSERT INTO PitCrew VALUES
    (3, 'Tyre On',      'Bob',      'Marley',       3, TO_DATE('2019-07-07','YYYY-MM-DD'), TO_DATE('2020-02-07','YYYY-MM-DD'));
INSERT INTO PitCrew VALUES
    (4, 'Front Jack',   'Fancis',   'Ngannou',      4, TO_DATE('2020-03-18','YYYY-MM-DD'), TO_DATE('2022-01-07','YYYY-MM-DD'));
INSERT INTO PitCrew VALUES
    (5, 'Rear Jack',    'Jon',      'Jones',        5, TO_DATE('2022-06-07','YYYY-MM-DD'), TO_DATE('2022-07-07','YYYY-MM-DD'));


INSERT INTO Track VALUES
    (1, 'Circuit de Monaco', 3.337, 1, 'Bd Albert', '98000');
INSERT INTO Track VALUES
    (2, 'Circuit of the Americas', 5.513, 9201, 'Circuit of the Americas Blvd', '78617');
INSERT INTO Track VALUES
    (3, 'Albert Park Circuit', 5.278, 12, 'Aughtie Dr', '3206');
INSERT INTO Track VALUES
    (4, 'Autodromo Enzo e Dino Ferrari', 4.909, 1, 'Piazza Ayrton Senna da Silva', '40026');
INSERT INTO Track VALUES
    (5, 'Marina Bay Street Circuit', 5.063, 1, 'Marina Bay St', '039596');
INSERT INTO Track VALUES
    (6, 'Algarve International Circuit', 4.653, 25, 'Portimao St', '8500');


INSERT INTO Event VALUES
    (1,  1, TO_DATE('2014-07-07','YYYY-MM-DD'), 'French Grand Prix',    82);
INSERT INTO Event VALUES
    (2,  2, TO_DATE('2016-06-18','YYYY-MM-DD'), 'German Grand Prix',    60);
INSERT INTO Event VALUES
    (3,  3, TO_DATE('2010-10-07','YYYY-MM-DD'), 'Hungarian Grand Prix', 35);
INSERT INTO Event VALUES
    (4,  4, TO_DATE('2020-04-20','YYYY-MM-DD'), 'Indian Grand Prix',    65);
INSERT INTO Event VALUES
    (5,  5, TO_DATE('2004-06-19','YYYY-MM-DD'), 'Bahrain Grand Prix',   40);
INSERT INTO Event VALUES
    (6,  1, TO_DATE('2014-07-07','YYYY-MM-DD'), 'French Practice',      15);
INSERT INTO Event VALUES
    (7,  2, TO_DATE('2016-06-18','YYYY-MM-DD'), 'German Practice',      67);
INSERT INTO Event VALUES
    (8,  3, TO_DATE('2010-10-07','YYYY-MM-DD'), 'Hungarian Practice',   25);
INSERT INTO Event VALUES
    (9,  4, TO_DATE('2020-04-20','YYYY-MM-DD'), 'Indian Practice',      15);
INSERT INTO Event VALUES
    (10, 5, TO_DATE('2004-06-19','YYYY-MM-DD'), 'Bahrain Practice',     34);
INSERT INTO Event VALUES
    (11, 1, TO_DATE('2014-07-06','YYYY-MM-DD'), 'French Exhibition',    21);
INSERT INTO Event VALUES
    (12, 2, TO_DATE('2016-06-17','YYYY-MM-DD'), 'German Exhibition',    25);
INSERT INTO Event VALUES
    (13, 3, TO_DATE('2010-10-06','YYYY-MM-DD'), 'Hungarian Exhibition', 15);
INSERT INTO Event VALUES
    (14, 4, TO_DATE('2020-04-19','YYYY-MM-DD'), 'Indian Exhibition',    35);
INSERT INTO Event VALUES
    (15, 5, TO_DATE('2004-06-18','YYYY-MM-DD'), 'Bahrain Exhibition',   23);


INSERT INTO Results VALUES
    (1,  1,  1, 1, 1, 2.923, 98.230);
INSERT INTO Results VALUES
    (2,  2,  2, 2, 2, 2.521, 97.960);
INSERT INTO Results VALUES
    (3,  3,  3, 3, 3, 3.054, 92.623);
INSERT INTO Results VALUES
    (4,  4,  4, 4, 4, 2.545, 91.891);
INSERT INTO Results VALUES
    (5,  5,  5, 5, 5, 2.285, 94.924);
INSERT INTO Results VALUES
    (6,  1,  6, 1, 0, 2.922, 96.238);
INSERT INTO Results VALUES
    (7,  2,  7, 2, 0, 4.927, 96.964);
INSERT INTO Results VALUES
    (8,  3,  8, 3, 0, 2.450, 92.651);
INSERT INTO Results VALUES
    (9,  4,  9, 4, 0, 2.548, 91.445);
INSERT INTO Results VALUES
    (10, 5, 10, 5, 0, 3.286, 94.924);
INSERT INTO Results VALUES
    (11, 1, 11, 1, 0, 2.923, 96.236);
INSERT INTO Results VALUES
    (12, 2, 12, 2, 0, 3.227, 93.767);
INSERT INTO Results VALUES
    (13, 3, 13, 3, 0, 2.453, 92.194);
INSERT INTO Results VALUES
    (14, 4, 14, 4, 0, 2.543, 91.225);
INSERT INTO Results VALUES
    (15, 5, 15, 5, 0, 2.291, 94.530);


INSERT INTO Car VALUES
    (1, 10200, 'Alfa Romeo C42 Ferrari', 1);
INSERT INTO Car VALUES
    (2, 20000, 'Aston Martin AMR22 Mercedes', 2);
INSERT INTO Car VALUES
    (3,  1000, 'Ferrari F1-75', 3);
INSERT INTO Car VALUES
    (4,  5000, 'Mercedes F1 W13 E Performance', 4);
INSERT INTO Car VALUES
    (5, 60000, 'Red Bull RB18 RBPT', 5);


INSERT INTO operate VALUES
    (1, 1, 1, 1);
INSERT INTO operate VALUES
    (2, 2, 2, 2);
INSERT INTO operate VALUES
    (3, 3, 3, 3);
INSERT INTO operate VALUES
    (4, 4, 4, 4);
INSERT INTO operate VALUES
    (5, 5, 5, 5);
INSERT INTO operate VALUES
    (1, 1, 6, 1);
INSERT INTO operate VALUES
    (2, 2, 7, 2);
INSERT INTO operate VALUES
    (3, 3, 8, 3);
INSERT INTO operate VALUES
    (4, 4, 9, 4);
INSERT INTO operate VALUES
    (5, 5, 10, 5);
INSERT INTO operate VALUES
    (1, 1, 11, 1);
INSERT INTO operate VALUES
    (2, 2, 12, 2);
INSERT INTO operate VALUES
    (3, 3, 13, 3);
INSERT INTO operate VALUES
    (4, 4, 14, 4);
INSERT INTO operate VALUES
    (5, 5, 15, 5);

INSERT INTO driveIn VALUES
    (1, 1, 1);
INSERT INTO driveIn VALUES
    (2, 2, 2);
INSERT INTO driveIn VALUES
    (3, 3, 3);
INSERT INTO driveIn VALUES
    (4, 4, 4);
INSERT INTO driveIn VALUES
    (5, 5, 5);


INSERT INTO LapTime VALUES
    (1, 1, 98.377);
INSERT INTO LapTime VALUES
    (2, 2, 99.717);
INSERT INTO LapTime VALUES
    (3, 3, 98.644);
INSERT INTO LapTime VALUES
    (4, 4, 97.222);
INSERT INTO LapTime VALUES
    (5, 5, 97.289);


INSERT INTO SeasonRace VALUES
    (1, 1, 100, 'Rich Brian');
INSERT INTO SeasonRace VALUES
    (2, 2, 115, 'Frank Ocean');
INSERT INTO SeasonRace VALUES
    (3, 3, 100, 'Kolton Brown');
INSERT INTO SeasonRace VALUES
    (4, 4,  95, 'Frank Ocean');
INSERT INTO SeasonRace VALUES
    (5, 5, 100, 'Rich Brian');


INSERT INTO Practice VALUES
    (6,  1, 100);
INSERT INTO Practice VALUES
    (7,  2, 50 );
INSERT INTO Practice VALUES
    (8,  3, 78 );
INSERT INTO Practice VALUES
    (9,  4, 68 );
INSERT INTO Practice VALUES
    (10, 5, 112);


INSERT INTO Exhibition VALUES
    (11, 1, 'Mike O’Hearn');
INSERT INTO Exhibition VALUES
    (12, 2, 'Ronald McDonald');
INSERT INTO Exhibition VALUES
    (13, 3, 'Kostya Ivanov');
INSERT INTO Exhibition VALUES
    (14, 4, 'Rudolf Hudson');
INSERT INTO Exhibition VALUES
    (15, 5, 'Muhammad Ali');


INSERT INTO SponsorsEvent VALUES
    (1, 1, 1, 200000);
INSERT INTO SponsorsEvent VALUES
    (2, 2, 2, 500000);
INSERT INTO SponsorsEvent VALUES
    (3, 3, 3, 90000);
INSERT INTO SponsorsEvent VALUES
    (4, 4, 4, 2200000);
INSERT INTO SponsorsEvent VALUES
    (5, 5, 5, 400000);


INSERT INTO SponsorsTeam VALUES
    (1, 1, 40000);
INSERT INTO SponsorsTeam VALUES
    (2, 2,  5000);
INSERT INTO SponsorsTeam VALUES
    (3, 3, 25000);
INSERT INTO SponsorsTeam VALUES
    (4, 4, 12500);
INSERT INTO SponsorsTeam VALUES
    (5, 5, 30000);
INSERT INTO SponsorsTeam VALUES
    (1, 2, 38000);
INSERT INTO SponsorsTeam VALUES
    (1, 3, 15000);
INSERT INTO SponsorsTeam VALUES
    (1, 4, 18000);
INSERT INTO SponsorsTeam VALUES
    (1, 5, 90000);
INSERT INTO SponsorsTeam VALUES
    (5, 1, 87000);
INSERT INTO SponsorsTeam VALUES
    (5, 2, 10000);
INSERT INTO SponsorsTeam VALUES
    (5, 3, 45000);
INSERT INTO SponsorsTeam VALUES
    (5, 4, 25000);
INSERT INTO SponsorsTeam VALUES
    (7, 1, 68000);
INSERT INTO SponsorsTeam VALUES
    (7, 2, 70000);
INSERT INTO SponsorsTeam VALUES
    (7, 3, 15000);
INSERT INTO SponsorsTeam VALUES
    (7, 4, 33000);
INSERT INTO SponsorsTeam VALUES
    (7, 5, 28000);