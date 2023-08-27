package lk.ijse.D24_hostel.dao;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO {
    public  void save(T entity) throws SQLException;
    public ArrayList<T> getAll() throws SQLException ;
    public  void delete(T entity) throws SQLException;
    public  void update(T entity) throws SQLException;
    public  ArrayList<ID> loadIds() throws SQLException ;

    void setSession(Session session);
}
