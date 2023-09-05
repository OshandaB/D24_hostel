package lk.ijse.D24_hostel.bo.custom;

import lk.ijse.D24_hostel.bo.SuperBO;
import lk.ijse.D24_hostel.dto.RoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomBO extends SuperBO {
    boolean saveRooms(RoomDTO roomDTO);



    boolean updateRooms(RoomDTO roomDTO);

    boolean deleteRooms(RoomDTO roomDTO);
    public ArrayList<RoomDTO> getAllRooms() throws SQLException;
}
