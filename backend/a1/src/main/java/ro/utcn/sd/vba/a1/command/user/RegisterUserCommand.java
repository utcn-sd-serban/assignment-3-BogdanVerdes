package ro.utcn.sd.vba.a1.command.user;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.a1.command.Command;
import ro.utcn.sd.vba.a1.service.UserService;

import java.sql.SQLException;

@AllArgsConstructor
public class RegisterUserCommand implements Command{

    private UserService userService;
    private String username;
    private String password;
    private String email;

    @Override
    public Object execute(){
        try {
            return userService.createUser(username,password,email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
