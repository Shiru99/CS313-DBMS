select *
from student s join takes t  using(ID)
order by name desc;

select *
from student
where name like '_h%';
-- bhaaaaag

select *
from student
where tot_cred between 100 And 120;

CREATE TABLE table_name
(
    ID INT PRIMARY KEY NOT NULL,
    NAME TEXT NOT NULL,
    AGE INT NOT NULL,
    ADDRESS CHAR(50),
    SALARY REAL,
    CHECK(AGE >10)
);


select *
from student, takes
where (student.ID,grade,dept_name)=(takes.ID,"A",'Comp. Sci.')
;

Find course_id and title of courses from the CompSci department which Has been taken by the students of both the Music department and Mechanical department in year 2015 and semester Spring.

SELECT course_id, title
FROM course, takes
WHERE course.course_id = takes.course_id AND dept_name = 'Comp. Sci.' AND takes.semester = 'Spring' AND takes.year = '2015'

SELECT course_id, title
FROM course
where 
    dept_name in ('Mechanical','Music')
    AND
    course_id in (
            SELECT DISTINCT course_id
    FROM takes
    WHERE takes.year = '2015' AND
        takes.semester = 'Spring' AND
        takes.ID in (
                        SELECT DISTINCT ID
        from student
        where dept_name = 'Comp. Sci.'
                    )
        )


SELECT DISTINCT course_id
FROM takes Natural Join student 
WHERE takes.year = '2015' AND takes.semester = 'Spring' AND student.dept_name = 'Comp. Sci.'





    Select *
    from student natural join takes 
    where semester='Spring'
UNION
    -- intersect,except
    Select *
    from student natural join takes 
    where semester='Summer'
;


Select avg(tot_cred)
from student natural join takes 
where semester='Fall'
;


Select count( Distinct semester)
from student natural join takes 
;



Select semester, avg(tot_cred)
from student natural join takes 
group by semester
;



Select dept_name, avg(tot_cred) as av
from student natural join takes 
group by dept_name
having av > 50
;


Select *
from section
where semester='Spring' AND section.year='2018' and course_id in (
		Select course_id
    from section
    where section.year='2017'	
	)
;


Select *
from section
where time_slot_id > some (
	  select time_slot_id
from section
where course_id like 'CS-%'
	)
;



Select *
from section
where time_slot_id > all (
	  select time_slot_id
from section
where course_id like 'CS-%'
	)
;







select course_id, title
from (

	select course.course_id, title, count(course.course_id) as c
    from course, takes

    where course.course_id = takes.course_id and takes.year='2017' and semester='Spring'

    group by course.course_id

    ORDER BY c DESC, title  ASC
)

limit
1
	
;


create view faculty
as
    select ID, name, dept_name
    from instructor
;

-- Privileges :

grant select/
UPDATE/
DELETE/ALL Privileges on instructor
to user1;

REVOKE select/
UPDATE/
DELETE/ALL Privileges on instructor
to user1/PUBLIC;

create role TA
grant TA to ravi;

-- tranfer of privileges :

grant select on dept_name to amit with grant OPTION;