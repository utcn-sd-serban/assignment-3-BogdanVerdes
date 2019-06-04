package ro.utcn.sd.vba.a1.command;

import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class Invoker {

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public Object invoke() throws SQLException {
        return command.execute();
    }
}
