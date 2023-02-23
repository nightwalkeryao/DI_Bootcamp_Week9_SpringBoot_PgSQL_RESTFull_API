package ci.ada1.learnspring.controllers;

import ci.ada1.learnspring.models.User;
import ci.ada1.learnspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> index(){
        return (List<User>) userRepository.findAll();
    }

    @PostMapping
    public User store(@Validated @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable(value = "id") Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent()
                ? ResponseEntity.ok().body(user.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    User update(@PathVariable(value = "id") Integer id, @RequestBody User user) {
        return userRepository.findById(id) //
                .map(u -> {
                    u.setName(user.getName());
                    u.setEmail(user.getEmail());
                    u.setPassword(user.getPassword());
                    u.setPhoneNumber(user.getPhoneNumber());
                    return userRepository.save(u);
                }) //
                .orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        userRepository.deleteById(id);
    }
}
