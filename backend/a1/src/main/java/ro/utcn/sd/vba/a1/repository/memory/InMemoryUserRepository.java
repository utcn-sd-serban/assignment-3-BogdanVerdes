package ro.utcn.sd.vba.a1.repository.memory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.User;
import ro.utcn.sd.vba.a1.repository.api.UserRepository;

import java.util.*;

@Component
public class InMemoryUserRepository implements UserRepository{
    private int currentId = 1;
    private final Map<String,User> data = new HashMap<>();

    @Override
    public synchronized User save(User user) {
        if(!user.getUsername().equals("")){
            data.put(user.getUsername(),user);
        }
        return user;
    }

    @Override
    public Optional<User> findByUsername(String name) {
        return Optional.ofNullable(data.get(name));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data.values());
    }

}
