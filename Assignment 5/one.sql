
Begin TRANSACTION;
Insert into course VALUES ('089', 'Potterology', 'Comp. Sci.', 4);
Insert into section VALUES ('089', '2', 'Fall', '1919', 'Cats', '804', 'z');
Insert into teaches VALUES ('38199', '089', '2', 'Fall', '1919');
Commit TRANSACTION;
