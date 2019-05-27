package ro.utcn.sd.vba.a1.dto;

import lombok.Data;
import ro.utcn.sd.vba.a1.model.QuestionTag;

@Data
public class QuestionTagDTO {

    private int id;
    private String nameTag;
    private int idQuestion;

    public static QuestionTagDTO ofEntity(QuestionTag questionTag){
        QuestionTagDTO questionTagDTO = new QuestionTagDTO();

        questionTagDTO.setId(questionTag.getId());
        questionTagDTO.setIdQuestion(questionTag.getIdQuestion());
        questionTagDTO.setNameTag(questionTag.getNameTag());

        return questionTagDTO;
    }
}
