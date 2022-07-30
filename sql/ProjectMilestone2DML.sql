-- Examples a bit boring but every table has at least 5 records so far


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
    (0, 0),
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

INSERT INTO Sponsor(sponsorID, name)
VALUES 
    (1, "DHL"),
    (2, "Crypto.com"),
    (3, "Heineken N.V."),
    (4, "Rolex Watch Co. Ltd"),
    (5, "Amazon Web Services, Inc.");


INSERT INTO TrackZipCode(zipCode, city, country, province)
VALUES
    ("98000", "Monaco", "Monaco", "Jardin Exotique"),
    ("78617", "Del Valle", "USA", "Texas"),
    ("3206", "Melbourne, Middle Park", "Australia", "Victoria"),
    ("40026", "Imola", "Italy", "Emilia-Romagna"), 
    ("039596", "Marina Bay", "Singapore", "Downtown Core");


INSERT INTO Team(teamID, directorID, teamName, startDate, endDate, points)
VALUES
	(1, 1, "Alfa Romeo",        '2014-07-07', '2020-01-01', 348),
    (2, 2, "Mercedes-AMG",      '2016-06-18', '2020-02-19', 412),
    (3, 3, "Oracle Red Bull",   '2010-10-07', '2015-08-04', 521),
    (4, 4, "Scuderia Ferrari",  '2020-04-20', '2022-06-19', 281),
    (5, 5, "McLaren",           '2004-06-19', '2014-11-29', 441);


INSERT INTO Athlete(athleteID, teamID, firstName, lastName, DOB, nRaces, startDate, endDate)
VALUES
    (1, 1, "Kevin",     "Stark",    '1990-07-18',  89, '2014-07-07', '2020-01-01'),
    (2, 2, "Frank",     "Ocean",    '1994-01-23', 128, '2016-06-18', '2020-02-19'),
    (3, 3, "Charles",   "Oliviera", '1985-10-07', 234, '2005-10-07', '2015-02-17'),
    (4, 4, "Rich",      "Brian",    '1993-03-03',  12, '2020-04-20', '2022-06-19'),
    (5, 5, "Kolton",    "Brown",    '1985-08-17',  66, '2004-05-19', '2014-08-28');


INSERT INTO 
    PitCrew(pitCrewID, role, firstName, lastName, teamID, startDate, endDate) 
VALUES 
    (1, "Tyre Gunner",  "John",     "Smith",        1, '2021-07-07', '2022-07-07'),
    (2, "Tyre Off",     "Yegor",    "Yeryomenko",   2, '2021-11-30', '2022-03-13'),
    (3, "Tyre On",      "Bob",      "Marley",       3, '2019-07-07', '2020-02-07'),
    (4, "Front Jack",   "Fancis",   "Ngannou",      4, '2020-03-18', '2022-01-07'),
    (5, "Rear Jack",    "Jon",      "Jones",        5, '2022-06-07', '2022-07-07');


INSERT INTO Track(trackID, trackName, length, addressNumber, street, zipCode)
VALUES
	(1, "Circuit de Monaco",                3.337, 1,    "Bd Albert",                    "98000"),
	(2, "Circuit of the Americas",          5.513, 9201, "Circuit of the Americas Blvd", "78617"),
    (3, "Albert Park Circuit",              5.278, 12,   "Aughtie Dr",                    "3206"),
    (4, "Autodromo Enzo e Dino Ferrari",    4.909, 1,    "Piazza Ayrton Senna da Silva", "40026"),
    (5, "Marina Bay Street Circuit",        5.063, 1,    "Marina Bay St",               "039596");


INSERT INTO Event(eventID, trackID, date, name, laps)
VALUES
    (1,  1, 2014-07-07, "French Grand Prix",    82),
    (2,  2, 2016-06-18, "German Grand Prix",    60),
    (3,  3, 2010-10-07, "Hungarian Grand Prix", 35),
    (4,  4, 2020-04-20, "Indian Grand Prix",    65),
    (5,  5, 2004-06-19, "Bahrain Grand Prix",   40),
    (6,  1, 2014-07-07, "French Practice",      15),
    (7,  2, 2016-06-18, "German Practice",      67),
    (8,  3, 2010-10-07, "Hungarian Practice",   25),
    (9,  4, 2020-04-20, "Indian Practice",      15),
    (10, 5, 2004-06-19, "Bahrain Practice",     34),
    (11, 1, 2014-07-06, "French Exhibition",    21),
    (12, 2, 2016-06-17, "German Exhibition",    25),
    (13, 3, 2010-10-06, "Hungarian Exhibition", 15),
    (14, 4, 2020-04-19, "Indian Exhibition",    35),
    (15, 5, 2004-06-18, "Bahrain Exhibition",   23);


INSERT INTO Results(resultID, athleteID, eventID, trackID, position, bestPitStop, bestLap)
VALUES
    (1,  1,  1, 1, 1, "00:00:02.923", "00:01:38.230"),
    (2,  2,  2, 2, 2, "00:00:02.521", "00:01:37.960"),
    (3,  3,  3, 3, 3, "00:00:03.054", "00:01:32.623"),
    (4,  4,  4, 4, 4, "00:00:02.545", "00:01:31.891"),
    (5,  5,  5, 5, 5, "00:00:02.285", "00:01:34.924"),
    (6,  1,  6, 1, 0, "00:00:02.922", "00:01:36.238"),
    (7,  2,  7, 2, 0, "00:00:04.927", "00:01:37.964"),
    (8,  3,  8, 3, 0, "00:00:02.450", "00:01:32.651"),
    (9,  4,  9, 4, 0, "00:00:02.548", "00:01:31.445"),
    (10, 5, 10, 5, 0, "00:00:03.286", "00:01:34.924"),
    (11, 1, 11, 1, 0, "00:00:02.923", "00:01:36.236"),
    (12, 2, 12, 2, 0, "00:00:03.227", "00:01:33.767"),
    (13, 3, 13, 3, 0, "00:00:02.453", "00:01:32.194"),
    (14, 4, 14, 4, 0, "00:00:02.543", "00:01:31.225"),
    (15, 5, 15, 5, 0, "00:00:02.291", "00:01:34.530");


INSERT INTO Car(carID, mileage, carModel, teamID)
VALUES
    (1, 10200, "Alfa Romeo C42 Ferrari", 1),
    (2, 20000, "Aston Martin AMR22 Mercedes", 2),
    (3,  1000, "Ferrari F1-75", 3),
    (4,  5000, "Mercedes F1 W13 E Performance", 4),
    (5, 60000, "Red Bull RB18 RBPT", 5);


INSERT INTO operate(carID, athleteID, eventID, trackID)
VALUES
    (1, 1, 1, 1),
    (2, 2, 2, 2),
    (3, 3, 3, 3),
    (4, 4, 4, 4),
    (5, 5, 5, 5),
    (1, 1, 6, 1),
    (2, 2, 7, 2),
    (3, 3, 8, 3),
    (4, 4, 9, 4),
    (5, 5, 10, 5),
    (1, 1, 11, 1),
    (2, 2, 12, 2),
    (3, 3, 13, 3),
    (4, 4, 14, 4),
    (5, 5, 15, 5);

INSERT INTO driveIn(athleteID, eventID, trackID)
VALUES
    (1, 1, 1),
    (2, 2, 2),
    (3, 3, 3),
    (4, 4, 4),
    (5, 5, 5);


INSERT INTO LapTime(lapNumber, resultID, time)
VALUES
    (1, 1, "00:01:38.377"),
    (2, 2, "00:01:39.717"),
    (3, 3, "00:01:38.644"),
    (4, 4, "00:01:37.222"),
    (5, 5, "00:01:37.289");


INSERT INTO SeasonRace(eventID, trackID, availablePoints, winner)
VALUES
    (1, 1, 100, "Rich Brian"  ),
    (2, 2, 115, "Frank Ocean" ),
    (3, 3, 100, "Kolton Brown"),
    (4, 4,  95, "Frank Ocean" ),
    (5, 5, 100, "Rich Brian"  );


INSERT INTO Practice(eventID, trackID, bestLap)
VALUES
    (6,  1, 100),
    (7,  2, 50 ),
    (8,  3, 78 ),
    (9,  4, 68 ),
    (10, 5, 112);


INSERT INTO Exhibition(eventID, trackID, winner)
VALUES
    (11, 1, "Mike O’Hearn"),
    (12, 2, "Ronald McDonald"),
    (13, 3, "Kostya Ivanov"),
    (14, 4, "Rudolf Hudson"),
    (15, 5, "Muhammad Ali");


INSERT INTO SponsorsEvent(sponsorID, eventID, trackID, dealValue)
VALUES
    (1, 1, 1,  200000),
    (2, 2, 2,  500000),
    (3, 3, 3,   90000),
    (4, 4, 4, 2200000),
    (5, 5, 5,  400000);


INSERT INTO SponsorsTeam(sponsorID, teamID, dealValue)
VALUES
    (1, 1, 40000),
    (2, 2,  5000),
    (3, 3, 25000),
    (4, 4, 12500),
    (5, 5, 30000);