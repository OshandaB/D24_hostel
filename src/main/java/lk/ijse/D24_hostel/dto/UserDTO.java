package lk.ijse.D24_hostel.dto;

import lk.ijse.D24_hostel.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String userName;
    private String password;

    public User toEntity() {
        System.out.println(userId);
        User user = new User(userId,userName,password);
        return user;
    }
}
