package ro.utcn.sd.vba.a1.command.answer;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.a1.command.Command;
import ro.utcn.sd.vba.a1.dto.AnswerDTO;
import ro.utcn.sd.vba.a1.service.AnswerService;

@AllArgsConstructor
public class CreateAnswerCommand implements Command {
    private AnswerService answerService;
    private AnswerDTO answerDTO;

    @Override
    public Object execute() {
        try {
            return answerService.saveAnswer(answerDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
