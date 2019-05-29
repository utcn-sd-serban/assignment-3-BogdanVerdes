package ro.utcn.sd.vba.a1.dto;

import lombok.Data;
import ro.utcn.sd.vba.a1.entity.VoteAnswer;

@Data
public class VoteAnswerDTO {

    private int id;
    private int idAnswer;
    private String author;
    private boolean voteType;

    public static VoteAnswerDTO ofEntity(VoteAnswer voteAnswer){
        VoteAnswerDTO voteAnswerDTO = new VoteAnswerDTO();

        voteAnswerDTO.setAuthor(voteAnswer.getAuthor());
        voteAnswerDTO.setId(voteAnswer.getId());
        voteAnswerDTO.setIdAnswer(voteAnswer.getIdAnswer());
        voteAnswerDTO.setVoteType(voteAnswer.isVoteType());

        return voteAnswerDTO;
    }
}
