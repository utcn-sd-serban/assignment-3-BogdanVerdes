package ro.utcn.sd.vba.a1.command.user;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.a1.command.Command;
import ro.utcn.sd.vba.a1.service.UserService;

@AllArgsConstructor
public class GetAllUsersCommand implements Command{
    private UserService userService;

    @Override
    public Object execute(){
        return userService.findAllUsers();
    }
}
