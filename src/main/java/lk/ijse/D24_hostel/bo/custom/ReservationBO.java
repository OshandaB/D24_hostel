package lk.ijse.D24_hostel.bo.custom;

import lk.ijse.D24_hostel.bo.SuperBO;
import lk.ijse.D24_hostel.dto.CustomDTO;
import lk.ijse.D24_hostel.dto.ReservationDTO;
import lk.ijse.D24_hostel.dto.RoomDTO;
import lk.ijse.D24_hostel.entity.Room;
import lk.ijse.D24_hostel.projection.CustomProjection;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBO {

    boolean saveReservation(ReservationDTO reservationDTO);



    boolean updateReservation(ReservationDTO reservationDTO);

    boolean deleteReservation(ReservationDTO reservationDTO);
    public ArrayList<ReservationDTO> getAllReservation() throws SQLException;
    ArrayList<String> loadRoomIds()  throws SQLException;
    ArrayList<String> loadStudentIds()  throws SQLException;
//    int countReservation(String id);
public RoomDTO getRoomsById(String id) ;
    public CustomDTO getStudentDetails(String id);
}
