package ro.utcn.sd.vba.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.dto.UserDTO;
import ro.utcn.sd.vba.a1.event.CreateUserEvent;
import ro.utcn.sd.vba.a1.entity.User;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RepositoryFactory repositoryFactory;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public UserDTO createUser(String username, String password, String email) throws SQLException {
        User user = new User();
        if (!repositoryFactory.createUserRepository().findByUsername(username).isPresent()) {
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            UserDTO userDTO = UserDTO.ofEntity(repositoryFactory.createUserRepository().save(user));
            applicationEventPublisher.publishEvent(new CreateUserEvent(userDTO));
            return userDTO;
        } else throw new RuntimeException("Username already exists");
    }

    @Transactional
    public UserDTO loginUser(String username, String password) {
        Optional<User> user = repositoryFactory.createUserRepository().findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return UserDTO.ofEntity(user.get());
        } else throw new RuntimeException("Username or password invalid");
    }

    @Transactional
    public List<User> findAllUsers() {
        return repositoryFactory.createUserRepository().findAll();
    }
}
