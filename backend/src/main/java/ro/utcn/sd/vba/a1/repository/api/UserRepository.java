package ro.utcn.sd.vba.a1.repository.api;

import ro.utcn.sd.vba.a1.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user) throws SQLException;

    Optional<User> findByUsername(String username);

    List<User> findAll();
}
