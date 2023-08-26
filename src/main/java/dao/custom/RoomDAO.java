package dao.custom;

import dao.CrudDAO;
import entity.Room;
import org.hibernate.query.Query;

public interface RoomDAO  extends CrudDAO<Room,String> {
    public Room getCustomerById(String id) ;
}
