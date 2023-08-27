package lk.ijse.D24_hostel.bo.custom;

import lk.ijse.D24_hostel.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO {

    boolean saveReservation(ReservationDTO reservationDTO);



    boolean updateReservation(ReservationDTO reservationDTO);

    boolean deleteReservation(ReservationDTO reservationDTO);
    public ArrayList<ReservationDTO> getAllReservation() throws SQLException;
    ArrayList<String> loadRoomIds()  throws SQLException;
    ArrayList<String> loadStudentIds()  throws SQLException;
//    int countReservation(String id);
}
