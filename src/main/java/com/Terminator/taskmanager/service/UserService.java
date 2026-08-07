package com.Terminator.taskmanager.service;

import com.Terminator.taskmanager.dto.*;
import com.Terminator.taskmanager.entity.User;
import com.Terminator.taskmanager.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponseDTO register(UserRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
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
