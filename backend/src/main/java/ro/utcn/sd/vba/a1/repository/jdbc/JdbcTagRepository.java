package ro.utcn.sd.vba.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.Tag;
import ro.utcn.sd.vba.a1.repository.api.TagRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository{
    private final JdbcTemplate template;

    @Override
    public Tag save(Tag tag) throws SQLException {
        if(!tag.getName().equals("")){
            update(tag);
        } else {
            String name = insert(tag);
            tag.setName(name);
        }
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return template.query("SELECT * FROM Tag",new TagMapper());

    }

    private String insert(Tag tag){
        SimpleJdbcInsert insert = new SimpleJdbcInsert((DataSource) template);
        insert.setTableName("Tag");
        insert.usingGeneratedKeyColumns("Name");
        Map<String,String> data = new HashMap<>();
        data.put("Name",tag.getName());
        return insert.executeAndReturnKey(data).toString();

    }

    private void update(Tag tag) throws SQLException {
        template.update("UPDATE Tag SET Name = ?",
                tag.getName());
    }
}
