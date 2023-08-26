package dao.custom.impl;

import dao.custom.RoomDAO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    private Session session;

    @Override
    public void save(Room entity) throws SQLException {
        session.save(entity);

    }

    @Override
    public ArrayList<Room> getAll() throws SQLException {
        try {
            List<Room> roomList = session.createNativeQuery("Select * from Room", Room.class).getResultList();
            return (ArrayList<Room>) roomList;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Room entity) throws SQLException {
        session.delete(entity);
    }

    @Override
    public void update(Room entity) throws SQLException {
        session.update(entity);
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException {
        String sql = "SELECT R.roomId FROM Room AS R";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return (ArrayList<String>) list;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    public Room getCustomerById(String id) {
        String sql = "SELECT R FROM Room AS R WHERE R.roomId = :room_type_id";
        Query namedQuery = session.createQuery(sql);
        namedQuery.setParameter("room_type_id", id);
        Room room = (Room) namedQuery.getSingleResult();
//        session.close();
        return room;
    }
}
