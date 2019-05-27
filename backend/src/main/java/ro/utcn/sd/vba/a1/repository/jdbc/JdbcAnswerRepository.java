package ro.utcn.sd.vba.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.Answer;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.repository.api.AnswerRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcAnswerRepository implements AnswerRepository{
    private final JdbcTemplate template;

    @Override
    public Answer save(Answer answer) throws SQLException {
        if(answer.getId() !=0){
            update(answer);
        } else {
            int id = insert(answer);
            answer.setId(id);
        }
        return answer;
    }

    @Override
    public Optional<Answer> findById(int id) {
        List<Answer> answers = template.query("SELECT * FROM Answer WHERE idAnswer = ?",new AnswerMapper(),id);
        return answers.isEmpty() ? Optional.empty() : Optional.of(answers.get(0));
    }

    @Override
    public List<Answer> findAll() {
        return template.query("SELECT * FROM Auestion",new AnswerMapper());
    }

    @Override
    public void remove(Answer answer) throws SQLException {
        template.update("DELETE FROM Answer WHERE idAnswer = ?",answer.getId());
    }

    @Override
    public List<Answer> findAllFromQuestion(Question question) {
        return template.query("SELECT * FROM Answer WHERE idQuestion = ?",new AnswerMapper(),question.getId());
    }

    private Integer insert(Answer answer){
        SimpleJdbcInsert insert = new SimpleJdbcInsert((DataSource) template);
        insert.setTableName("Answer");
        insert.usingGeneratedKeyColumns("idAnswer");
        Map<String,Object> data = new HashMap<>();
        data.put("idAnswer",answer.getId());
        data.put("Text",answer.getText());
        return insert.executeAndReturnKey(data).intValue();

    }

    private void update(Answer answer) throws SQLException {
        template.update("UPDATE Answer SET Text = ? WHERE idAnswer = ?",
                answer.getText(), answer.getId());
    }
}
