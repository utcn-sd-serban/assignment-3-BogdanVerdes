package ro.utcn.sd.vba.a1.command;

import org.springframework.stereotype.Component;

@Component
public class Invoker {

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public Object invoke(){
        return command.execute();
    }
}
