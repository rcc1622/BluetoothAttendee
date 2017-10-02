CREATE TABLE tblClass (
    className VARCHAR(50) PRIMARY KEY,
)

CREATE TABLE tblStudent (
    jagNumber VARCHAR(9) PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
)

CREATE TABLE tblEnrollment (
    className VARCHAR(50) REFERENCES tblClass(className),
    className VARCHAR(50) REFERENCES tblStudent(jagNumber),
)