package ro.utcn.sd.vba.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.entity.User;
import ro.utcn.sd.vba.a1.repository.api.UserRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository{
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate template1;

    @Override
    public User save(User user) throws SQLException {

            String name = insert(user);
            user.setUsername(name);
            return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> users = template.query("SELECT * FROM User WHERE Username = ?",new UserMapper(),username);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public List<User> findAll() {
        return template.query("SELECT * FROM User",new UserMapper());
    }

    private String insert(User user){
        //SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        //insert.setTableName("User");
        //insert.usingGeneratedKeyColumns("Username");
        Map<String,Object> data = new HashMap<>();
        data.put("Username",user.getUsername());
        data.put("Password",user.getPassword());
        data.put("Score",0);
        data.put("Email",user.getEmail());
        template1.update("INSERT INTO `sover`.`user` (`Username`,`Password`,`Score`,`Email`) VALUES (:Username,:Password,:Score,:Email);",data);
        return user.getUsername();

    }

}
