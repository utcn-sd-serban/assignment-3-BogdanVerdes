package ro.utcn.sd.vba.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.User;
import ro.utcn.sd.vba.a1.repository.api.UserRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository{
    private final JdbcTemplate template;

    @Override
    public User save(User user) throws SQLException {
        if(!user.getUsername().equals("")){
            update(user);
        } else {
            String name = insert(user);
            user.setUsername(name);
        }
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> users = template.query("SELECT * FROM User WHERE Username = ?",new UserMapper(),username);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    private String insert(User user){
        SimpleJdbcInsert insert = new SimpleJdbcInsert((DataSource) template);
        insert.setTableName("User");
        insert.usingGeneratedKeyColumns("Username");
        Map<String,Object> data = new HashMap<>();
        data.put("Username",user.getUsername());
        data.put("Password",user.getPassword());
        data.put("Score",0);
        data.put("Email",user.getEmail());
        return insert.executeAndReturnKey(data).toString();

    }

    private void update(User user) throws SQLException {
        template.update("UPDATE User SET Password = ?, Score = ?, Email = ? WHERE Username  ?",
                user.getPassword(),
                user.getScore(),
                user.getEmail(),
                user.getUsername());
    }
}
