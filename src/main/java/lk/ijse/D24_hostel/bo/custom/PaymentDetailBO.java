package lk.ijse.D24_hostel.bo.custom;

import lk.ijse.D24_hostel.bo.SuperBO;
import lk.ijse.D24_hostel.dto.CustomDTO;
import lk.ijse.D24_hostel.projection.CustomProjection;

import java.util.List;

public interface PaymentDetailBO extends SuperBO {
    public List<CustomDTO> getPaymentDetails();
}
