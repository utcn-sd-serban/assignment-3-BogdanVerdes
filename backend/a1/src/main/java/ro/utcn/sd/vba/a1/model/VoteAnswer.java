package ro.utcn.sd.vba.a1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "VoteAnswer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idAnswer;
    private String author;
    private boolean voteType;

    public VoteAnswer(int idAnswer,String author,boolean voteType){
        this.idAnswer = idAnswer;
        this.author = author;
        this.voteType = voteType;
    }
}
