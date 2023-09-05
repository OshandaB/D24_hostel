package lk.ijse.D24_hostel.bo;

import lk.ijse.D24_hostel.bo.custom.impl.*;
import lk.ijse.D24_hostel.dao.DAOFactory;
import lk.ijse.D24_hostel.dao.SuperDAO;
import lk.ijse.D24_hostel.dao.custom.impl.QueryDAOImpl;
import lk.ijse.D24_hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.D24_hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.D24_hostel.dao.custom.impl.StudentDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}
    public  static  BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory=new BOFactory() : boFactory;
    }
    public enum BOTypes{
        StudentBO,RoomBO,ReservationBO,PaymentDetailsBO,HomeBO,UserBO
    }
    public <T extends SuperBO>T getBo(BOFactory.BOTypes boTypes){
        switch (boTypes){
            case StudentBO:
                return (T) new StudentBOImpl();
            case RoomBO:
                return (T) new RoomBOImpl();
            case ReservationBO:
                return (T) new ReservationBOImpl();
            case PaymentDetailsBO:
                return (T) new PaymentDetailBOImpl();
            case HomeBO:
                return (T) new HomeBOImpl();
            case UserBO:
                return (T) new UserBOImpl();


        }
        return null;
    }
}
