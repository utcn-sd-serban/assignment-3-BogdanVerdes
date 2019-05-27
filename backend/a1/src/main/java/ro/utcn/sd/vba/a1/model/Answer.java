package ro.utcn.sd.vba.a1.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity(name = "Answer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private Date creation;
    private int idq;
    private String author;
    private Integer score;

    public Answer(String text,String author,int idq){
        this.author = author;
        this.text = text;
        this.idq = idq;
        this.creation = new Date(System.currentTimeMillis());
        this.score = 0;
    }

}
