package ro.utcn.sd.vba.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vba.a1.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Question(rs.getInt("idQuestion"),
                rs.getString("Title"),
                rs.getString("Content"),
                rs.getDate("Creation"),
                rs.getString("Author"),
                rs.getInt("Score"));
    }
}
