


Select DISTINCT course.course_id,course.title,instructor.ID,instructor.name 
    from instructor ,teaches,course 
    where instructor.ID = teaches.ID  AND instructor.dept_name = 'Comp. Sci.' AND teaches.year = 2009 AND course.dept_name = 'Civil Eng.' AND course.course_id = teaches.course_id
    order by instructor.name ASC;



with sec_enrollment as (select course_id, sec_id, count(ID) as enrollment 
    from section natural join takes 
    where year = 2009 and semester = 'Fall'   
    group by course_id, sec_id ) 
    select course_id, sec_id 
        from sec_enrollment 
        where enrollment = (select max(enrollment) from sec_enrollment) ;

Select takes.course_id,takes.sec_id 
    from takes 
    where takes.year = '2009' and takes.semester = 'Fall'  GROUP BY takes.course_id, takes.sec_id 
    ORDER BY count(ID) DESC 
    LIMIT 1;



Select course_id,sec_id 
    from takes 
    where year = '2009' and semester = 'Fall'
    group by sec_id, course_id
    order by count(ID) DESC 
    limit 1;


with selection as 
    (
        select course_id,sec_id,count(ID) as Erol 
        from takes 
            where year = '2009' and semester = 'Fall' 
            group by sec_id , course_id
            order by count(ID) desc
    )
select course_id, sec_id 
    from selection 
    where Erol = (
        select max(Erol) 
        from selection
    );