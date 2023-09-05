package lk.ijse.D24_hostel.dao.custom;

import lk.ijse.D24_hostel.dao.SuperDAO;
import lk.ijse.D24_hostel.projection.CustomProjection;
import org.hibernate.Session;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    void setSession(Session session);
    public List<CustomProjection> getPaymentDetails();
    public CustomProjection getStudentDetails(String id);
}