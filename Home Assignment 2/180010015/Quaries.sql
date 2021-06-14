CREATE TABLE book (
    book_id varchar(255) primary key,
    title varchar(255) not null,     
    category varchar(255) not null,
    author varchar(255) not null
);

CREATE TABLE student (
    student_id varchar(255) primary key,
    name varchar(255) not null,
    dept_name varchar(255) not null,
    year int not null,
    semester int not null
);

CREATE TABLE issue (
    student_id varchar(255) references student(student_id),
    book_id varchar(255) references book(book_id),
    issue_date date not null,
    return_date date
);

INSERT INTO book VALUES ('1', 'Harry Potter 1', 'Fun', 'J K Rowlings');
INSERT INTO book VALUES ('2', 'Harry Potter 2', 'Entertainment', 'P K Rowlings');
INSERT INTO book VALUES ('3', 'Harry Potter 3', 'unknown', 'unknown');


INSERT INTO student VALUES ('1', 'Doggo 1', 'dept_fun', '2017', '7');
INSERT INTO student VALUES ('2', 'Kitty 1', 'dept_study', '2018', '5');
INSERT INTO student VALUES ('3', 'student_name', 'dept_LOL', '2019', '3');


INSERT INTO issue VALUES ('1', '1', '2017-01-04', '2017-03-04');
INSERT INTO issue VALUES ('1', '2', '2017-01-04', '2019-01-04');
INSERT INTO issue VALUES ('2', '3', '2017-01-04', '2019-01-04');
INSERT INTO issue VALUES ('2', '1', '2017-03-04', '2020-03-04');
INSERT INTO issue VALUES ('3', '3', '2019-02-05', '2020-02-05');