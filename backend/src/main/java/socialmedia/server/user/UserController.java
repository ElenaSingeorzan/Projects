package socialmedia.server.user;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import socialmedia.server.JwtTokenUtil;
import java.util.Optional;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userService.checkPassword(user, loginRequest.getPassword())) {
                // Generează token-ul
                String token = jwtTokenUtil.createToken(user.getEmail(), user.getId());
                System.out.println("logat:  " +token);
                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.status(401).body("Invalid email or password.");
    }
}

