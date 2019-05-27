package ro.utcn.sd.vba.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vba.a1.model.VoteQuestion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteQuestionMapper implements RowMapper<VoteQuestion> {
    @Override
    public VoteQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new VoteQuestion(rs.getInt("idVoteQuestion"),
                rs.getInt("Question_idQuestion"),
                rs.getString("User_Username"),
                rs.getBoolean("VoteType"));
    }
}
