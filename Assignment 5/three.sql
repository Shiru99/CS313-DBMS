


Begin TRANSACTION;
Insert into course VALUES ('060', 'Weasleyology', 'Comp. Sci.', 4);
Insert into section VALUES ('060', '2', 'Fall', '1919', 'Cats', '804', 'z');
Insert into teaches VALUES ('33189', '060', '2', 'Fall', '1919');
Rollback TRANSACTION;