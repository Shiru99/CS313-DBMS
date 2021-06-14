Select student.ID, student.name, takes.grade, takes.course_id
	FROM student,takes
	WHERE student.ID=takes.ID AND takes.semester='Fall' AND takes.year= 2009 AND takes.grade='A+';
