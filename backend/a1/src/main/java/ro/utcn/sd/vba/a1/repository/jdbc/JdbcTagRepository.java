package ro.utcn.sd.vba.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.entity.Tag;
import ro.utcn.sd.vba.a1.repository.api.TagRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository{
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate template1;

    @Override
    public Tag save(Tag tag) throws SQLException {
        if(!tag.getName().equals("")){
            String name = insert(tag);
            tag.setName(name);
        }
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return template.query("SELECT * FROM Tags",new TagMapper());

    }

    private String insert(Tag tag){


        //SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        //insert.setTableName("Tags");
        //insert.setGeneratedKeyName("Name");
        Map<String,String> data = new HashMap<>();
        data.put("Name",tag.getName());
        //return insert.executeAndReturnKey(data).toString();
        template1.update("INSERT INTO `sover`.`tags` (`Name`) VALUES (:Name);",data);
        return tag.getName();

    }

}
