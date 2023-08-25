package bo.custom;

import dto.ReservationDTO;
import dto.RoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO {

    boolean saveReservation(ReservationDTO reservationDTO);



    boolean updateReservation(ReservationDTO reservationDTO);

    boolean deleteReservation(ReservationDTO reservationDTO);
    public ArrayList<ReservationDTO> getAllReservation() throws SQLException;
    ArrayList<String> loadRoomIds()  throws SQLException;
}
