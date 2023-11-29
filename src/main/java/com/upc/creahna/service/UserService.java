package com.upc.creahna.service;


import com.upc.creahna.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    public abstract User createUser(User user);
    public abstract User updateUser(User user);
    public abstract User getUserById(int id);
    public abstract void deleteUser(int id);
    public abstract boolean isUserExist(int id);
    public abstract List<User> getAllUsers();

}
