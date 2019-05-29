package ro.utcn.sd.vba.a1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.vba.a1.command.Invoker;
import ro.utcn.sd.vba.a1.command.answer.CreateAnswerCommand;
import ro.utcn.sd.vba.a1.command.answer.DeleteAnswerCommand;
import ro.utcn.sd.vba.a1.command.answer.EditAnswerCommand;
import ro.utcn.sd.vba.a1.command.answer.ListAnswersCommand;
import ro.utcn.sd.vba.a1.dto.AnswerDTO;
import ro.utcn.sd.vba.a1.entity.Question;
import ro.utcn.sd.vba.a1.service.AnswerService;
import ro.utcn.sd.vba.a1.service.QuestionService;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final Invoker invoker;

    @GetMapping("/question/{questionId}/answers")
    public Object answersOfQuestion(@PathVariable Integer questionId) throws SQLException {
        invoker.setCommand(new ListAnswersCommand(answerService, questionService, questionId));
        return invoker.invoke();
    }

    @PostMapping("/answers")
    public Object create(@RequestBody AnswerDTO dto) throws SQLException {
        invoker.setCommand(new CreateAnswerCommand(answerService, dto));
        return invoker.invoke();
    }

    @DeleteMapping("/answer/{answerId}")
    public Object delete(@PathVariable Integer answerId) throws SQLException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        invoker.setCommand(new DeleteAnswerCommand(answerService, answerId, name));
        return invoker.invoke();
    }

    @PutMapping("/answer/{answerId}")
    public Object edit(@RequestBody AnswerDTO dto) throws SQLException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        invoker.setCommand(new EditAnswerCommand(answerService, dto.getId(), name, dto.getText()));
        return invoker.invoke();
    }
}
