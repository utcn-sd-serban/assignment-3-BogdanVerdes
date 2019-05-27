package ro.utcn.sd.vba.a1.command.question;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.a1.command.Command;
import ro.utcn.sd.vba.a1.dto.QuestionDTO;
import ro.utcn.sd.vba.a1.service.QuestionService;

import java.sql.SQLException;

@AllArgsConstructor
public class CreateQuestionCommand implements Command{
    private QuestionService questionService;
    private QuestionDTO questionDTO;

    @Override
    public Object execute() {
        try {
            return questionService.saveQuestion(questionDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
