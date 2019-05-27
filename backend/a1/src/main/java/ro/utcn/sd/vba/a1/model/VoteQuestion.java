package ro.utcn.sd.vba.a1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "VoteQuestion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idQuestion;
    private String author;
    private boolean voteType;

    public VoteQuestion(int idQuestion,String author,boolean voteType){
        this.idQuestion = idQuestion;
        this.author = author;
        this.voteType = voteType;
    }
}
