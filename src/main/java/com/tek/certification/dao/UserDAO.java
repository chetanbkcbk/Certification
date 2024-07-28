package com.tek.certification.dao;

import com.tek.certification.model.Subject;
import com.tek.certification.model.User;

import java.util.List;

public interface UserDAO {
    public User createUser(User user);

    User findDisabledSubByUser(String userId);

    User findUserById(String userId);
}
