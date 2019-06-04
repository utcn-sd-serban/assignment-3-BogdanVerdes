package ro.utcn.sd.vba.a1.command.question;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.a1.command.Command;
import ro.utcn.sd.vba.a1.entity.Tag;
import ro.utcn.sd.vba.a1.service.QuestionTagService;

@AllArgsConstructor
public class SearchByTagCommand implements Command{
    private QuestionTagService questionTagService;
    private Tag tag;

    @Override
    public Object execute(){
        return questionTagService.findQuestionsByTag(tag);
    }
}
