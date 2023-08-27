package lk.ijse.D24_hostel.bo.custom.impl;

import lk.ijse.D24_hostel.bo.custom.PaymentDetailBO;
import lk.ijse.D24_hostel.config.SessionFactoryConfig;
import lk.ijse.D24_hostel.dao.DAOFactory;
import lk.ijse.D24_hostel.dao.custom.QueryDAO;
import lk.ijse.D24_hostel.dao.custom.impl.QueryDAOImpl;
import lk.ijse.D24_hostel.dto.CustomDTO;
import lk.ijse.D24_hostel.dto.RoomDTO;
import lk.ijse.D24_hostel.entity.Room;
import lk.ijse.D24_hostel.projection.CustomProjection;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PaymentDetailBOImpl implements PaymentDetailBO {
    QueryDAO queryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QueryDAO);

    @Override
    public List<CustomDTO> getPaymentDetails() {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        queryDAO.setSession(session);
        List<CustomProjection> paymentDetails = queryDAO.getPaymentDetails();
        List<CustomDTO> customDTOS = new ArrayList<>();
        for (CustomProjection room : paymentDetails) {
            customDTOS.add(new CustomDTO(
                    room.getStudentId(),
                    room.getStudentName(),
                    room.getAddress(),
                    room.getContact(),
                    room.getDob(),
                    room.getGender(),
                    room.getResId(),
                    room.getStatus()
            ));

        }
        session.close();
        return customDTOS;
    }
}
