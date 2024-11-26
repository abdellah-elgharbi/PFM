package com.example.backend_pfm.controller;

import com.example.backend_pfm.beans.User;
import com.example.backend_pfm.repository.UserRespository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
@Autowired
    UserRespository userRespository;
@PostMapping
    public User createUser(@RequestBody User user) {
    return userRespository.save(user);
}
@GetMapping
List<User> getAllUsers() {
    return userRespository.findAll();
}

@GetMapping("/{id}")
ResponseEntity<User> getUserById(@RequestParam int id) {
    Optional<User> user = userRespository.findById(id);
    return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

}
@PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
    Optional<User> userOptional = userRespository.findById(id);
    if (userOptional.isPresent()) {
        User updatedUser = userOptional.get();
        user.setNom(user.getNom());
        user.setEmail(user.getEmail());
        user.setHashedPassword(user.getHashedPassword());
        User updateUser = userRespository.save(user);
        return ResponseEntity.ok(updateUser);
    }
    return ResponseEntity.notFound().build();
}
@DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
    Optional<User> userOptional = userRespository.findById(id);
    if (userOptional.isPresent()) {
        userRespository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
}
}
