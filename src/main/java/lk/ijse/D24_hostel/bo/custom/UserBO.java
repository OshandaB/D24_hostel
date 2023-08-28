package lk.ijse.D24_hostel.bo.custom;

import lk.ijse.D24_hostel.dto.StudentDTO;
import lk.ijse.D24_hostel.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO {
    boolean saveUsers(UserDTO userDTO);
    boolean updateUsers(UserDTO userDTO);
    UserDTO searchUsers(String id);
    ArrayList<String> loadUserIds()  throws SQLException;
    String generateNextUserId();
}
