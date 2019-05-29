package ro.utcn.sd.vba.a1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.vba.a1.command.Invoker;
import ro.utcn.sd.vba.a1.command.question.CreateQuestionCommand;
import ro.utcn.sd.vba.a1.command.question.ListQuestionsCommand;
import ro.utcn.sd.vba.a1.command.question.SearchByTagCommand;
import ro.utcn.sd.vba.a1.command.question.SearchByTitleCommand;
import ro.utcn.sd.vba.a1.dto.QuestionDTO;
import ro.utcn.sd.vba.a1.entity.Tag;
import ro.utcn.sd.vba.a1.service.QuestionService;
import ro.utcn.sd.vba.a1.service.QuestionTagService;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionTagService questionTagService;
    private final Invoker invoker;

    @GetMapping("/questions")
    public Object listAll() throws SQLException {
        invoker.setCommand(new ListQuestionsCommand(questionService));
        return invoker.invoke();
    }

    @PostMapping("/questions")
    public Object create(@RequestBody QuestionDTO questionDTO) throws SQLException {
        invoker.setCommand(new CreateQuestionCommand(questionService, questionDTO));
        return invoker.invoke();
    }

    @GetMapping("/questions/filterTitle={title}")
    public Object searchTitle(@PathVariable String title) throws SQLException {
        invoker.setCommand(new SearchByTitleCommand(questionService, title));
        return invoker.invoke();
    }

    @GetMapping("/questions/filterTag={tag}")
    public Object searchTag(@PathVariable Tag tag) throws SQLException {
        invoker.setCommand(new SearchByTagCommand(questionTagService, tag));
        return invoker.invoke();
    }

}