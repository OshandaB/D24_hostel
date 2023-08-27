package lk.ijse.D24_hostel.bo.custom.impl;

import lk.ijse.D24_hostel.bo.custom.StudentBO;
import lk.ijse.D24_hostel.config.SessionFactoryConfig;
import lk.ijse.D24_hostel.dao.custom.StudentDAO;
import lk.ijse.D24_hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.D24_hostel.dto.StudentDTO;
import lk.ijse.D24_hostel.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = new StudentDAOImpl();
    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentDAO.setSession(session);
            studentDAO.save(studentDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentDAO.setSession(session);
            studentDAO.update(studentDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentDAO.setSession(session);
            studentDAO.delete(studentDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws SQLException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        studentDAO.setSession(session);
        ArrayList<Student> students = studentDAO.getAll();
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            studentDTOS.add(new StudentDTO(
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getAddress(),
                    student.getContact(),
                    student.getDob(),
                    student.getGender()
            ));

        }
        session.close();
        return studentDTOS;
    }
}
