package ro.utcn.sd.vba.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vba.a1.model.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<Answer> {
    @Override
    public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Answer(rs.getInt("idAnswer"),
                rs.getString("Text"),
                rs.getDate("Creation"),
                rs.getInt("idQuestion"),
                rs.getString("Author"),
                rs.getInt("Score"));
    }
}
