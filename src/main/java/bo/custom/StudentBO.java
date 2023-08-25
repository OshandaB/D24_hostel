package bo.custom;

import dto.StudentDTO;

public interface StudentBO {
    boolean saveStudent(StudentDTO studentDTO);



    boolean updateStudent(StudentDTO studentDTO);

    boolean deleteStudent(StudentDTO studentDTO);
}
