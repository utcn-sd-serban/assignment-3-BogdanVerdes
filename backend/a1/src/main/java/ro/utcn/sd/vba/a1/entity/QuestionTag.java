package ro.utcn.sd.vba.a1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Tags_has_Question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameTag;
    private int idQuestion;

    public QuestionTag(String nameTag, int idQ) {
        id = 0;
        this.nameTag = nameTag;
        this.idQuestion = idQ;
    }
}
