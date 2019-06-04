package ro.utcn.sd.vba.a1.command.answer;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.a1.command.Command;
import ro.utcn.sd.vba.a1.service.AnswerService;

import java.sql.SQLException;

@AllArgsConstructor
public class EditAnswerCommand implements Command{
    private AnswerService answerService;
    private Integer answerId;
    private String name;
    private String newText;

    @Override
    public Object execute() {
        try {
            return answerService.editAnswer(answerId, name, newText);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
