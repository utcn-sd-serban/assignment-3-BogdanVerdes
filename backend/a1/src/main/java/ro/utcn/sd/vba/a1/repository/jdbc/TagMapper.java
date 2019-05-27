package ro.utcn.sd.vba.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vba.a1.model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Tag(rs.getString("Name")
        );
    }
}