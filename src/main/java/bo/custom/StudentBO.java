package bo.custom;

import dto.RoomDTO;
import dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO {
    boolean saveStudent(StudentDTO studentDTO);



    boolean updateStudent(StudentDTO studentDTO);

    boolean deleteStudent(StudentDTO studentDTO);
    public ArrayList<StudentDTO> getAllStudents() throws SQLException;
}
