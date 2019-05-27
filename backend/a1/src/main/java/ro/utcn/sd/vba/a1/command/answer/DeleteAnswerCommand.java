package ro.utcn.sd.vba.a1.command.answer;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.a1.command.Command;
import ro.utcn.sd.vba.a1.service.AnswerService;

import java.sql.SQLException;

@AllArgsConstructor
public class DeleteAnswerCommand implements Command {
    private AnswerService answerService;
    private Integer answerId;
    private String name;

    @Override
    public Object execute() {
        try {
            return answerService.deleteAnswer(answerId, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
