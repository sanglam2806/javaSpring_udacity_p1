package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvaible(String username) {
        return userMapper.getUser(username) == null;
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    public int createUser(User user) {
        SecureRandom rd = new SecureRandom();
        byte[] salt = new byte[16];

        rd.nextBytes(salt);

        String encodeSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodeSalt);

        return userMapper.addUser(new User(null, user.getUsername(), encodeSalt, hashedPassword, user.getFirstName(),
                user.getLastName()));
    }

}
