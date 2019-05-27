package ro.utcn.sd.vba.a1.dto;

import lombok.Data;
import ro.utcn.sd.vba.a1.model.VoteQuestion;

@Data
public class VoteQuestionDTO {

    private int id;
    private int idQuestion;
    private String author;
    private boolean voteType;

    public static VoteQuestionDTO ofEntity(VoteQuestion voteQuestion){
        VoteQuestionDTO voteQuestionDTO = new VoteQuestionDTO();

        voteQuestionDTO.setAuthor(voteQuestion.getAuthor());
        voteQuestionDTO.setId(voteQuestion.getId());
        voteQuestionDTO.setIdQuestion(voteQuestion.getIdQuestion());
        voteQuestionDTO.setVoteType(voteQuestion.isVoteType());

        return voteQuestionDTO;
    }
}
