package ro.utcn.sd.vba.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.model.VoteQuestion;
import ro.utcn.sd.vba.a1.repository.api.VoteQuestionRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JdbcVoteQuestionRepository implements VoteQuestionRepository{
    private final JdbcTemplate template;

    @Override
    public VoteQuestion save(VoteQuestion voteQuestion) throws SQLException {
        if(voteQuestion.getId() !=0){
            update(voteQuestion);
        } else {
            int id = insert(voteQuestion);
            voteQuestion.setId(id);
        }
        return voteQuestion;
    }

    @Override
    public List<VoteQuestion> findAll() {
        return template.query("SELECT * FROM VoteQuestion",new VoteQuestionMapper());
    }

    @Override
    public void remove(VoteQuestion voteQuestion) throws SQLException {
        template.update("DELETE FROM VoteQuestion WHERE idVoteQuestion = ?",voteQuestion.getId());
    }

    @Override
    public int voteCount(Question Question) {
        int up = template.query("SELECT * FROM VoteQuestion WHERE Question_idQuestion = ? AND VoteType = 1",
                new VoteQuestionMapper(),Question.getId()).size();
        int down = template.query("SELECT * FROM VoteQuestion WHERE Question_idQuestion = ? AND VoteType = 0",
                new VoteQuestionMapper(),Question.getId()).size();
        return up - down;
    }


    private Integer insert(VoteQuestion voteQuestion){
        SimpleJdbcInsert insert = new SimpleJdbcInsert((DataSource) template);
        insert.setTableName("VoteQuestion");
        insert.usingGeneratedKeyColumns("idVoteQuestion");
        Map<String,Object> data = new HashMap<>();
        data.put("idVoteQuestion",voteQuestion.getId());
        data.put("VoteType",voteQuestion.isVoteType());
        return insert.executeAndReturnKey(data).intValue();

    }

    private void update(VoteQuestion voteQuestion) throws SQLException {
        template.update("UPDATE VoteQuestion SET VoteType = ? WHERE idVoteQuestion = ?",
                voteQuestion.isVoteType(), voteQuestion.getId());
    }
}
