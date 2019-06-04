package ro.utcn.sd.vba.a1.command.question;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.a1.command.Command;
import ro.utcn.sd.vba.a1.service.QuestionService;

@AllArgsConstructor
public class ListQuestionsCommand implements Command{
    private QuestionService questionService;

    @Override
    public Object execute(){
        return questionService.findAllQuestions();
    }
}
