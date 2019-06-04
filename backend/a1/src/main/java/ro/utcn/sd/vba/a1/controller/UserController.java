package ro.utcn.sd.vba.a1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.utcn.sd.vba.a1.command.Invoker;
import ro.utcn.sd.vba.a1.command.user.GetAllUsersCommand;
import ro.utcn.sd.vba.a1.command.user.LoginUserCommand;
import ro.utcn.sd.vba.a1.command.user.RegisterUserCommand;
import ro.utcn.sd.vba.a1.dto.UserDTO;
import ro.utcn.sd.vba.a1.event.BaseEvent;
import ro.utcn.sd.vba.a1.service.UserService;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SimpMessagingTemplate messagingTemplate;
    private final Invoker invoker;

    @PostMapping("/register")
    public Object create(@RequestBody UserDTO userDTO) throws SQLException {
        invoker.setCommand(new RegisterUserCommand(userService, userDTO.getUsername(),userDTO.getPassword(),userDTO.getEmail()));
        return invoker.invoke();
    }

    @PostMapping("/login")
    public Object readOne(@RequestBody UserDTO userDTO) throws SQLException {
        invoker.setCommand(new LoginUserCommand(userService, userDTO.getUsername(),userDTO.getPassword()));
        return invoker.invoke();
    }

    @GetMapping("/users")
    public Object readAll() throws SQLException {
        invoker.setCommand(new GetAllUsersCommand(userService));
        return invoker.invoke();
    }

    @EventListener(BaseEvent.class)
    public void handleEvent(BaseEvent event) {
        System.out.println("GOT AN EVENT " + event.toString());
        messagingTemplate.convertAndSend("/topic/events", event);
    }
}
