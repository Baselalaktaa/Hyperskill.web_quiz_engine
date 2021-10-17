package engine.controller;



import engine.model.RegisterData;
import engine.model.User;
import engine.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/api/register")
    public ResponseEntity register(@RequestBody @Valid RegisterData registerData) {
        if (userRepo.findByEmail(registerData.getEmail()).isPresent()){
            return ResponseEntity.status(400).build();
        }
        User user = new User(registerData.getEmail() , encoder.encode(registerData.getPassword()) , "ROLE_USER");
        userRepo.save(user);
        return ResponseEntity.ok().build();
    }
}
