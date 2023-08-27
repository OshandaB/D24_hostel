package lk.ijse.D24_hostel.dao.custom;

import lk.ijse.D24_hostel.projection.CustomProjection;
import org.hibernate.Session;

import java.util.List;

public interface QueryDAO {
    void setSession(Session session);
    public List<CustomProjection> getPaymentDetails();
}