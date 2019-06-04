package ro.utcn.sd.vba.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService{
    private final RepositoryFactory repositoryFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        ro.utcn.sd.vba.a1.entity.User user = repositoryFactory.createUserRepository().findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return new User(user.getUsername(),user.getPassword(),Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
