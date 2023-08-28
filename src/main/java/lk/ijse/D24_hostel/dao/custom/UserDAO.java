package lk.ijse.D24_hostel.dao.custom;

import lk.ijse.D24_hostel.dao.CrudDAO;
import lk.ijse.D24_hostel.entity.User;
import org.hibernate.query.Query;

public interface UserDAO extends CrudDAO<User,String> {
    public User getUserById(String id);
    String generateNextId();
}
