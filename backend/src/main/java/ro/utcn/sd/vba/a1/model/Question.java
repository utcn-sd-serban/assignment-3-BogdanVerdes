package ro.utcn.sd.vba.a1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity(name = "Question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String body;
    private Date creation;
    private String username;
    private int score;

    public Question(String title, String body, String user){
        this.title = title;
        this.body = body;
        this.username = user;
        this.creation = new Date(System.currentTimeMillis());
        this.score = 0;
    }
}
