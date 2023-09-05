package lk.ijse.D24_hostel.dao.custom.impl;

import lk.ijse.D24_hostel.dao.custom.QueryDAO;
import lk.ijse.D24_hostel.entity.Room;
import lk.ijse.D24_hostel.projection.CustomProjection;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    private Session session;

    @Override
    public List<CustomProjection> getPaymentDetails() {
        String hql = "SELECT new lk.ijse.D24_hostel.projection.CustomProjection(" + "S.studentId,S.studentName,S.address,S.contact,S.dob,S.gender,R.resId,R.status) FROM Student AS S INNER JOIN Reservation R on S.studentId = R.reservationDetailsPK.studentId WHERE R.status='pending'";
        Query query = session.createQuery(hql);
        List list = query.list();
        System.out.println(list);
        session.close();
        return list;
    }

    @Override
    public CustomProjection getStudentDetails(String id) {
        String hql = "SELECT new lk.ijse.D24_hostel.projection.CustomProjection(" + "S.studentId,S.studentName,S.address,S.contact,S.dob,S.gender) FROM Student AS S INNER JOIN Reservation R on S.studentId = R.reservationDetailsPK.studentId WHERE R.resId=:res_id";
        Query query = session.createQuery(hql);
        query.setParameter("res_id", id);
        CustomProjection CustomProjection = (CustomProjection) query.getSingleResult();
        return CustomProjection;

    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
