package ro.utcn.sd.vba.a1.dto;

import lombok.Data;
import ro.utcn.sd.vba.a1.entity.User;

@Data
public class UserDTO {

    private String username;
    private String password;
    private int score;
    private String email;

    public static UserDTO ofEntity(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setScore(user.getScore());

        return userDTO;
    }
}
