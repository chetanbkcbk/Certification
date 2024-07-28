package com.tek.certification.dao.daoimpl;

import com.tek.certification.dao.UserDAO;
import com.tek.certification.model.Subject;
import com.tek.certification.model.User;
import com.tek.certification.repository.SubjectRepository;
import com.tek.certification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    private final SubjectRepository subjectRepository;

    @Override
    public User createUser(User user) {
        return userRepository.insert(user);
    }

    @Override
    public User findDisabledSubByUser(String userId) {
      User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No such User id"));
        return user;
    }

    @Override
    public User findUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new NoSuchElementException("No user found"));
    }
}
