package engine.service;

import engine.model.User;
import engine.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@Service
public class UserServices {

    private final UserRepository userRepository;


    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserById (Integer id) {
        return userRepository.findById(id);
    }

    public User save (User toSave) {

        Iterable<User> users = userRepository.findAll();
        for (User user : users){
            if (user.getEmail().equals(toSave.getEmail())){
                throw new ResponseStatusException(
                        HttpStatus.valueOf(400), "forbidden"
                );
            }
        }
        return userRepository.save(toSave);
    }

}
