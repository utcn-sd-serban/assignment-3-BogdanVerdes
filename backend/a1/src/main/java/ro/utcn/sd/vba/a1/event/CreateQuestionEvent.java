package ro.utcn.sd.vba.a1.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.utcn.sd.vba.a1.dto.QuestionDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateQuestionEvent extends BaseEvent{
    private final QuestionDTO question;

    public CreateQuestionEvent(QuestionDTO question) {
        super(EventType.QUESTION_CREATED);
        this.question = question;
    }
}
