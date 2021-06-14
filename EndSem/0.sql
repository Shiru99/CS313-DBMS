select * from student s join takes t using(ID) 
order by name desc;

select * from student
	where name like '_h%';

select * from student
	where tot_cred between 100 And 120;

select * from student,takes
	where (student.ID,grade,dept_name)=(takes.ID,"A",'Comp. Sci.')	;



Select * from student natural join takes
	where semester='Spring'
UNION                             -- intersect,except
Select * from student natural join takes
	where semester='Summer'
;


Select avg(tot_cred)
	from student natural join takes
		where semester='Fall'
;


Select count( Distinct semester)
	from student natural join takes
;



Select semester,avg(tot_cred)
	from student natural join takes
		group by semester
;



Select dept_name, avg(tot_cred) as av
	from student natural join takes
		group by dept_name
		having av > 50
;


Select * from section
	where semester='Spring' AND  section.year='2018' and course_id in (
		Select course_id from section where section.year='2017'	
	)
;


Select * from section
	where time_slot_id > some (
	  select time_slot_id from section where course_id like 'CS-%'
	)
;



Select * from section
	where time_slot_id > all (
	  select time_slot_id from section where course_id like 'CS-%'
	)
;







select course_id,title from (

	select course.course_id,title,count(course.course_id) as c from course,takes

	where course.course_id = takes.course_id and takes.year='2017' and semester='Spring'

	group by course.course_id

	ORDER BY c DESC, title  ASC
)

limit 1
	
;