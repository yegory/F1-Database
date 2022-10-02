# CPSC 304 Project
## *GROUP 17*

> ### **Members**
> - Yegor Yeryomenko
> - [member name left out]
> - [member name left out]

[Source Repository](https://github.students.cs.ubc.ca/CPSC304-2022S-T2/project_j6y0m_t8h7w_u7o3p/tree/main/src)

No extra information.


### Project Description What is the domain of the application? ###
- Sports and Entertainment (use case for betting)
### What aspects of the domain are modeled by the database? ###
- The athletes and their respective teams as well as their track times and race history.

### Database Specification What benefit does the database provide to the application? ###
Without storing information in a database it would be very hard and annoying to actually find the necessary information about an athlete. Having a database will allow users to easily look up different athletes and their information and compare them so that they can make an educated decision on whom to bet on.

### What functionality does the database provide? ###
Our project was a relational database setup in Oracle to track information related to formula 1 racing, including car information, sponsorships, and race results. We created a GUI using Java Swing to allow a user to query the database to view the data as well as insert, update, and delete tuples.

### What is your application technology stack? ###
- Java (JDBC)/ Oracle

### ER Diagram ###

![ER diagram image](https://github.com/yegory/F1-Database/blob/main/CPSC_304-ER-Diagram-Main.png?raw=true)

### SQL Script ###
The database creation and data population sql script [F1_DBscript.sql](https://github.com/yegory/F1-Database/blob/main/src/sql_scripts/F1_DBscript.sql) works by itself if run on the student servers as well as from within IntelliJ IDEA Ultimate.

### How did Schema change throughout the implementation process ###
Our schema did not differ in terms of arity and relationships between entities from milestone 2, however, we did make other types of changes.
- Types - time was changed from TIME to FLOAT as TIME was not a type in Oracle.
- We also added some constraints that were not present (deleting from one entity might delete
from another by foreign key reference).
- We also needed to remove some of the total participation requirements from entities that could
not be completed with NOT NULL and required assertions as Oracle does not support this feature.

### Supported Queries

[Link to PDF](https://github.com/yegory/F1-Database/blob/main/Implemented%20Queries.pdf)
