package engine.service;


import engine.model.User;
import engine.model.UserDetails;
import engine.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepo;

    public UserDetailService (@Autowired UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(username);
        if (user.isPresent()){
            return new UserDetails(user.get());
        }
        else throw new UsernameNotFoundException("Not Found");
    }
}
