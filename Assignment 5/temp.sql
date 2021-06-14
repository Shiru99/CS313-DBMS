
select s.ID,s.name,s.dept_name,sum(course.credits) 
    from takes, course, student as s 
    where takes.course_id=course.course_id and takes.ID=s.ID 
    GROUP BY s.ID,s.name,s.dept_name order by sum(course.credits) desc LIMIT 5;


select st.ID,st.name,st.dept_name,sum(course.credits) 
    from  student as st, course, takes
    where takes.course_id=course.course_id and takes.ID=st.ID 
    GROUP BY st.ID,st.name,st.dept_name 
    order by sum(course.credits) desc LIMIT 5;


Select st.name,st.dept_name 
    From  student as st, course, takes
    Where takes.course_id=course.course_id and takes.ID=st.ID 
    Group by st.ID,st.name,st.dept_name 
    Order by sum(course.credits) DESC LIMIT 5;


select student.name,student.dept_name,sum(course.credits) 
from student,takes,course 
where student.id=takes.id and course.course_id=takes.course_id and takes.grade<>'F' and takes.grade is not null 
group by student.name,student.dept_name order by sum(course.credits) desc Limit 5;



    select DISTINCT takes.grade 
    from takes 
    Order by grade ASC; 


SELECT grade
FROM takes
group by id;


SELECT sum(course.credits), takes.id,course.credits
FROM takes,course
WHERE takes.course_id = course.course_id;
GROUP BY id;


