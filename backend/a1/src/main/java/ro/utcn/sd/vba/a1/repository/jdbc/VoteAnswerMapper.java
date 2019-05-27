package ro.utcn.sd.vba.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vba.a1.model.VoteAnswer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteAnswerMapper implements RowMapper<VoteAnswer> {
    @Override
    public VoteAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new VoteAnswer(rs.getInt("idVoteAnswer"),
                rs.getInt("Answer_idAnswer"),
                rs.getString("User_Username"),
                rs.getBoolean("VoteType"));
    }
}
