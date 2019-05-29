package ro.utcn.sd.vba.a1.command.answer;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.a1.command.Command;
import ro.utcn.sd.vba.a1.dto.QuestionDTO;
import ro.utcn.sd.vba.a1.entity.Question;
import ro.utcn.sd.vba.a1.service.AnswerService;
import ro.utcn.sd.vba.a1.service.QuestionService;

import java.sql.SQLException;

@AllArgsConstructor
public class ListAnswersCommand implements Command {
    private AnswerService answerService;
    private QuestionService questionService;
    private Integer questionId;


    @Override
    public Object execute() throws SQLException {
        QuestionDTO qDTO = questionService.findById(questionId);
        Question question = new Question(qDTO.getTitle(),qDTO.getBody(),qDTO.getUsername());
        return answerService.findAnswersByQuestion(question);
    }
}