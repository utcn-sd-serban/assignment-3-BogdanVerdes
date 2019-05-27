package ro.utcn.sd.vba.a1.command.answer;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.a1.command.Command;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.service.AnswerService;

@AllArgsConstructor
public class ListAnswersCommand implements Command {
    private AnswerService answerService;
    private Question question;

    @Override
    public Object execute() {
        return answerService.findAnswersByQuestion(question);
    }
}