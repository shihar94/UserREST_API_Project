package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.exceptionHandler.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired //this does dependancy injection
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user)
    {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id)
    {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found " + id)));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id , @RequestBody User user)
    {
        User userData = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found " + id));
        userData.setEmail(user.getEmail());
        userData.setName(user.getName());
        return userRepository.save(userData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id)
    {
        User userData = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found " + id));
        userRepository.delete(userData);
        return ResponseEntity.ok().build();
    }


}
