package com.Terminator.taskmanager.service;

import com.Terminator.taskmanager.entity.User;
import com.Terminator.taskmanager.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    public User register(User user)
    {
        if(userRepository.existsByEmail(user.getEmail()))
        {
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User getUser(Long id)
    {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

     public void deleteUser(Long id)
     {
            userRepository.deleteById(id);
     }

}
