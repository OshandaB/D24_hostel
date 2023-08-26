package dao.custom;

import dao.CrudDAO;
import entity.Reservation;

public interface ReservationDAO extends CrudDAO<Reservation,String> {
    int countReservation(String id);
}
