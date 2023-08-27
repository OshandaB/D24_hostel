package lk.ijse.D24_hostel.dao.custom;

import lk.ijse.D24_hostel.dao.CrudDAO;
import lk.ijse.D24_hostel.entity.Room;

public interface RoomDAO  extends CrudDAO<Room,String> {
    public Room getCustomerById(String id) ;
}
