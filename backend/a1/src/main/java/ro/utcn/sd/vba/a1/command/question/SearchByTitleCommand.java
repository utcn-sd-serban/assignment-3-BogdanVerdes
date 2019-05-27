package ro.utcn.sd.vba.a1.command.question;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.a1.command.Command;
import ro.utcn.sd.vba.a1.service.QuestionService;

@AllArgsConstructor
public class SearchByTitleCommand implements Command{
    private QuestionService questionService;
    private String title;

    @Override
    public Object execute() {
        return questionService.findByTitle(title);
    }
}
