package ro.utcn.sd.vba.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.repository.api.QuestionRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcQuestionRepository implements QuestionRepository{
    private final JdbcTemplate template;

    @Override
    public Question save(Question question) throws SQLException {
        if(question.getId() !=0){
            update(question);
        } else {
            int id = insert(question);
            question.setId(id);
        }
        return question;
    }

    @Override
    public Optional<Question> findById(int id) {
        List<Question> questions = template.query("SELECT * FROM Question WHERE idQuestion = ?",new QuestionMapper(),id);
        return questions.isEmpty() ? Optional.empty() : Optional.of(questions.get(0));
    }

    @Override
    public List<Question> findAll() {
        return template.query("SELECT * FROM Question",new QuestionMapper());
    }

    @Override
    public void remove(Question question) throws SQLException {
        template.update("DELETE FROM Question WHERE idQuestion = ?",question.getId());
    }

    private Integer insert(Question question){
        SimpleJdbcInsert insert = new SimpleJdbcInsert((DataSource) template);
        insert.setTableName("Question");
        insert.usingGeneratedKeyColumns("idQuestion");
        Map<String,Object> data = new HashMap<>();
        data.put("idQuestion",question.getId());
        data.put("Content",question.getBody());
        return insert.executeAndReturnKey(data).intValue();

    }

    private void update(Question question) throws SQLException {
        template.update("UPDATE Question SET Title = ?, Content = ? WHERE idQuestion = ?",
                question.getTitle(), question.getBody(), question.getId());
    }
}
