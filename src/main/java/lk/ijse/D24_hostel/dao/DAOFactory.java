package lk.ijse.D24_hostel.dao;

import lk.ijse.D24_hostel.dao.custom.impl.QueryDAOImpl;
import lk.ijse.D24_hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.D24_hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.D24_hostel.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public  static  DAOFactory getDaoFactory(){
        return (daoFactory==null) ? daoFactory=new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
       StudentDAO,RoomDAO,ReservationDAO,QueryDAO
    }
    public <T extends SuperDAO>T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case StudentDAO:
                return (T) new StudentDAOImpl();
            case RoomDAO:
                return (T) new RoomDAOImpl();
            case ReservationDAO:
                return (T) new ReservationDAOImpl();
            case QueryDAO:
                return (T) new QueryDAOImpl();

        }
        return null;
    }
}
