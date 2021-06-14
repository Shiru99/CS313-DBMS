

Begin TRANSACTION;
Insert into course VALUES ('058', 'Grangerology', 'Comp. Sci.', 4);
Insert into section VALUES ('058', '2', 'Fall', '1919', 'Cats', '804', 'z');
Rollback TRANSACTION;
Insert into teaches VALUES ('33189', '058', '2', 'Fall', '1919');
Commit TRANSACTION;