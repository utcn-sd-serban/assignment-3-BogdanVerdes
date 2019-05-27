package ro.utcn.sd.vba.a1.dto;

import lombok.Data;
import ro.utcn.sd.vba.a1.model.Question;

import java.sql.Date;

@Data
public class QuestionDTO {

    private int id;
    private String title;
    private String body;
    private Date creation;
    private String username;
    private int score;
    private String tags;

    public static QuestionDTO ofEntity(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setBody(question.getBody());
        questionDTO.setTitle(question.getTitle());
        questionDTO.setCreation(question.getCreation());
        questionDTO.setUsername(question.getUsername());
        questionDTO.setScore(question.getScore());
        return questionDTO;
    }
}
