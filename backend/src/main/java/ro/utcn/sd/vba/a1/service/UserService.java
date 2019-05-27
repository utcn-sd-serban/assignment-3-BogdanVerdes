package ro.utcn.sd.vba.a1.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.model.User;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public User createUser(String username, String password, String email) throws SQLException {
        User user = new User();
        if(!repositoryFactory.createUserRepository().findByUsername(username).isPresent()) {
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            return repositoryFactory.createUserRepository().save(user);
        }
        else throw new RuntimeException("Username already exists");
    }

    @Transactional
    public User loginUser(String username, String password){
        Optional<User> user = repositoryFactory.createUserRepository().findByUsername(username);
        if(user.isPresent()&&user.get().getPassword().equals(password)){
            return user.get();
        }
        else throw new RuntimeException("Username or password invalid");
    }

    @Transactional
    public List<User> findAllUsers(){
        return repositoryFactory.createUserRepository().findAll();
    }
}
