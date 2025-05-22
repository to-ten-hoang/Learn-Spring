package com.devteria.identify_service.service;

import com.devteria.identify_service.dto.request.UserCreationRequest;
import com.devteria.identify_service.dto.request.UserUpdateRequest;
import com.devteria.identify_service.entity.User;
import com.devteria.identify_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();

        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("User existed");
        }

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(String id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String userId, UserUpdateRequest request){
        User user = getUser(userId);

        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setDob(request.getDob());
        user.setPassword(request.getPassword());

        return userRepository.save(user);

    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
