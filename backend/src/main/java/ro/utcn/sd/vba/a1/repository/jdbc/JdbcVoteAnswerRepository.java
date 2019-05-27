package ro.utcn.sd.vba.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.Answer;
import ro.utcn.sd.vba.a1.model.VoteAnswer;
import ro.utcn.sd.vba.a1.repository.api.VoteAnswerRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JdbcVoteAnswerRepository implements VoteAnswerRepository{
    private final JdbcTemplate template;

    @Override
    public VoteAnswer save(VoteAnswer voteAnswer) throws SQLException {
        if(voteAnswer.getId() !=0){
            update(voteAnswer);
        } else {
            int id = insert(voteAnswer);
            voteAnswer.setId(id);
        }
        return voteAnswer;
    }

    @Override
    public void remove(VoteAnswer voteAnswer) throws SQLException {
        template.update("DELETE FROM VoteAnswer WHERE idVoteAnswer = ?",voteAnswer.getId());
    }

    @Override
    public List<VoteAnswer> findAll() {
        return template.query("SELECT * FROM VoteAnswer",new VoteAnswerMapper());
    }

    @Override
    public int voteCount(Answer answer) {
        int up = template.query("SELECT * FROM VoteAnswer WHERE Answer_idAnswer = ? AND VoteType = 1",
                new VoteAnswerMapper(),answer.getId()).size();
        int down = template.query("SELECT * FROM VoteAnswer WHERE Answer_idAnswer = ? AND VoteType = 0",
                new VoteAnswerMapper(),answer.getId()).size();
        return up - down;
    }


    private Integer insert(VoteAnswer voteAnswer){
        SimpleJdbcInsert insert = new SimpleJdbcInsert((DataSource) template);
        insert.setTableName("VoteAnswer");
        insert.usingGeneratedKeyColumns("idVoteAnswer");
        Map<String,Object> data = new HashMap<>();
        data.put("idVoteAnswer",voteAnswer.getId());
        data.put("VoteType",voteAnswer.isVoteType());
        return insert.executeAndReturnKey(data).intValue();

    }

    private void update(VoteAnswer voteAnswer) throws SQLException {
        template.update("UPDATE VoteAnswer SET VoteType = ? WHERE idVoteAnswer = ?",
                voteAnswer.isVoteType(), voteAnswer.getId());
    }
}
