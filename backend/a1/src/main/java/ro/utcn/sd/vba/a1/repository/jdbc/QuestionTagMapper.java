package ro.utcn.sd.vba.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vba.a1.model.QuestionTag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionTagMapper implements RowMapper<QuestionTag> {
    @Override
    public QuestionTag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new QuestionTag(rs.getInt("idQuestionTag"),
                rs.getString("Tags_Name"),
                rs.getInt("Question_idQuestion")
        );
    }
}