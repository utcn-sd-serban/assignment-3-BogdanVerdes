package ro.utcn.sd.vba.a1.command;

import java.sql.SQLException;

public interface Command {
    Object execute() throws SQLException;
}
