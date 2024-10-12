package com.thinkontest.demo.service;

import com.thinkontest.demo.model.User;
import com.thinkontest.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public int createUser(User user) {
        return userRepository.save(user);
    }

    public int updateUser(Long id, User user) {
        return userRepository.update(id, user);
    }

    public int deleteUser(Long id) {
        return userRepository.delete(id);
    }
}
