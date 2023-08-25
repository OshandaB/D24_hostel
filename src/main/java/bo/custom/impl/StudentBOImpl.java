package bo.custom.impl;

import bo.custom.StudentBO;
import config.SessionFactoryConfig;
import dao.custom.StudentDAO;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
