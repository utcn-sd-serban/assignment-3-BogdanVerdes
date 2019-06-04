package ro.utcn.sd.vba.a1.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.utcn.sd.vba.a1.dto.UserDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateUserEvent extends BaseEvent{
    private final UserDTO userDTO;

    public CreateUserEvent(UserDTO user){
        super(EventType.USER_CREATED);
        this.userDTO = user;
    }
}
