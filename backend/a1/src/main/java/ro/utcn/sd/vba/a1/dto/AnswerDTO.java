package ro.utcn.sd.vba.a1.dto;

import lombok.Data;
import ro.utcn.sd.vba.a1.model.Answer;

import java.sql.Date;

@Data
public class AnswerDTO {
    private int id;
    private String text;
    private Date creation;
    private int idq;
    private String author;
    private Integer score;

    public static AnswerDTO ofEntity(Answer answer){
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAuthor(answer.getAuthor());
        answerDTO.setCreation(answer.getCreation());
        answerDTO.setId(answer.getId());
        answerDTO.setIdq(answer.getIdq());
        answerDTO.setScore(answer.getScore());
        answerDTO.setText(answer.getText());

        return answerDTO;
    }
}
