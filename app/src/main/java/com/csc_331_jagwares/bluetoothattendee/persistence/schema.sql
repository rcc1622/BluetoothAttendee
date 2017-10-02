PRAGMA foreign_keys = ON;

CREATE TABLE tblClass (
    className TEXT PRIMARY KEY
);

CREATE TABLE tblStudent (
    jagNumber TEXT PRIMARY KEY,
    firstName TEXT,
    lastName TEXT
);

CREATE TABLE tblEnrollment (
    className TEXT REFERENCES tblClass(className),
    jagNumber TEXT REFERENCES tblStudent(jagNumber)
);