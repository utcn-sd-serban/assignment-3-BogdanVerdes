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
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.service.AnswerService;

@RestController
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final Invoker invoker;

    @GetMapping("/answersForQuestion/{questionId}")
    public Object answersOfQuestion(@PathVariable Question question) {
        invoker.setCommand(new ListAnswersCommand(answerService, question));
        return invoker.invoke();
    }

    @PostMapping("/answers")
    public Object create(@RequestBody AnswerDTO dto) {
        invoker.setCommand(new CreateAnswerCommand(answerService, dto));
        return invoker.invoke();
    }

    @DeleteMapping("/answer/{answerId}")
    public Object delete(@PathVariable Integer answerId) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        invoker.setCommand(new DeleteAnswerCommand(answerService, answerId, name));
        return invoker.invoke();
    }

    @PutMapping("/answer/{answerId}")
    public Object edit(@RequestBody AnswerDTO dto) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        invoker.setCommand(new EditAnswerCommand(answerService, dto.getId(), name, dto.getText()));
        return invoker.invoke();
    }
}
