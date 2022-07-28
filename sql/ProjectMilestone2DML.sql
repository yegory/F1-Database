-- UNFINISHED 2


INSERT INTO 
    CarModel(carModel, weight, topSpeed, horsepower)
VALUES
    ("Alfa Romeo C42 Ferrari", 798, 231, 1050),
    ("Aston Martin AMR22 Mercedes", 850, 230, 1000),
    ("Ferrari F1-75", 800, 231, 1025),
    ("Mercedes F1 W13 E Performance", 798, 228, 1030),
    ("Red Bull RB18 RBPT", 860, 230, 1050);


INSERT INTO 
    Director(directorID, firstName, lastName)
VALUES
    (1, "Mike", "O'Hearn"),
    (2, "Ronald", "McDonald"),
    (3, "Kostya", "Ivanov"),
    (4, "Rudolf", "Hudson"),
    (5, "Muhammad", "Ali"),
    (6, "Nicolas", "Davison");

INSERT INTO ResultsScoring(position, points)
VALUES
    (1, 25),
    (2, 18),
    (3, 15),
    (4, 12),
    (5, 10),
    (6, 8),
    (7, 6),
    (8, 4),
    (9, 2),
    (10, 1);

INSERT INTO Sponsor(sponsorID, legalname, parentCompany, annualRevenue, website)
VALUES 
    (1, "DHL", "Deutsche Post", 90000000000, "dhl.com"),
    (2, "Crypto.com", "Foris DAX Markets, Inc.", 3400000000, "crypto.com"),
    (3, "Heineken N.V.", "Heineken Holding", 25985000000, "theheinekencompany.com"),
    (4, "Rolex Watch Co. Ltd", "N/A", 13000000000, "rolex.com"),
    (5, "Amazon Web Services, Inc.", "amazon.com", 62000000000, "aws.amazon.com");

INSERT INTO TrackZipCode(zipCode, city, country, province)
VALUES
    ("98000", "Monaco", "Monaco", "Jardin Exotique"),
    ("78617", "Del Valle", "USA", "Texas"),
    ("3206", "Melbourne, Middle Park", "Australia", "Victoria"),
    ("40026", "Imola", "Italy", "Emilia-Romagna"), 
    ("039596", "Marina Bay", "Singapore", "Downtown Core");

INSERT INTO Team(teamID, directorID, teamName, startDate, endDate, points)
VALUES
	(1, 1, "Alfa Romeo", 2014-07-07, 2020-01-01, 348),
    (2, 2, "Mercedes-AMG", 2016-06-18, 2020-02-19, 412),
    (3, 3, "Oracle Red Bull", 2010-10-07, 2015-08-04, 521),
    (4, 4, "Scuderia Ferrari", 2020-04-20, 2022-06-19, 281),
    (5, 5, "McLaren", 2004-06-19, 2014-11-29, 441);

INSERT INTO Athlete(athleteID, teamID, firstName, lastName, DOB, nRaces, startDate, endDate)
VALUES
    (1, 1, "Kevin", "Stark", 1990-07-18, 89, 2014-07-07, 2020-01-01),
    (2, 2, "Frank", "Ocean", 1994-01-23, 128, 2016-06-18, 2020-02-19),
    (3, 3, "Charles", "Oliviera", 1985-10-07, 234, 2005-10-07, 2015-02-17),
    (4, 4, "Rich", "Brian", 1993, 12, 2020-04-20, 2022-06-19),
    (5, 5, "Kolton", "Brown", 1985-08-17, 66, 2004-05-19, 2014-08-28);

INSERT INTO 
    PitCrew(pitCrewID, role, firstName, lastName, teamID, startDate, endDate) 
VALUES 
    (1, "Tyre Gunner", "John", "Smith", 1, 2021-07-07, 2022-07-07),
    (2, "Tyre Off", "Yegor", "Yeryomenko", 2, 2021-11-30, 2022-03-13),
    (3, "Tyre On", "Bob", "Marley", 3, 2019-07-7, 2020-02-07),
    (4, "Front Jack", "Fancis", "Ngannou", 4, 2020-03-18, 2022-01-07),
    (5, "Rear Jack", "Jon", "Jones", 5, 2022-06-07, 2022-07-07);



INSERT INTO Track(trackID, trackName, length, addressNumber, street, zipCode)
VALUES
	(1, "Circuit de Monaco", 3.337, 1, "Bd Albert", "98000"),
	(2, "Circuit of the Americas", 5.513, 9201, "Circuit of the Americas Blvd", "78617"),
    (3, "Albert Park Circuit", 5.278, 12, "Aughtie Dr", "3206"),
    (4, "Autodromo Enzo e Dino Ferrari", 4.909, 1, "Piazza Ayrton Senna da Silva", "40026"),
    (5, "Marina Bay Street Circuit", 5.063, 1, "Marina Bay St", "039596");

INSERT INTO Event(eventID, trackID, date, name, laps)
VALUES
    (1, 1, 2014-07-07, "French Grand Prix", 82),
    (2, 2, 2016-06-18, "German Grand Prix", 60),
    (3, 3, 2010-10-07, "Hungarian Grand Prix", 35),
    (4, 4, 2020-04-20, "Indian Grand Prix", 65),
    (5, 5, 2004-06-19, "Bahrain Grand Prix", 40),
    (6, 1, 2014-07-07, "French Practice", 15),
    (7, 2, 2016-06-18, "German Practice", 67),
    (8, 3, 2010-10-07, "Hungarian Practice", 25),
    (9, 4, 2020-04-20, "Indian Practice", 15),
    (10, 5, 2004-06-19, "Bahrain Practice", 34),
    (11, 1, 2014-07-06, "French Exhibition", 21),
    (12, 2, 2016-06-17, "German Exhibition", 25),
    (13, 3, 2010-10-06, "Hungarian Exhibition", 15),
    (14, 4, 2020-04-19, "Indian Exhibition", 35),
    (15, 5, 2004-06-18, "Bahrain Exhibition", 23);

-- INSERT INTO Results(resultID, athleteID, eventID, trackID, position, bestPitStop, bestLap)
-- VALUES

--INSERT INTO operate(carID, athleteID, eventID, trackID)
--VALUES
--    (1, 1, 1, 1),
--    (2, 2, 2, 2);


