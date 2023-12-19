package com.pingwit.parsel.service;

import com.pingwit.parsel.entity.User;
import com.pingwit.parsel.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

import static java.lang.String.format;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BeanUtilService beanUtilService;

    public UserService(UserRepository userRepository, BeanUtilService beanUtilService){

        this.userRepository = userRepository;
        this.beanUtilService = beanUtilService;
    }

    @Transactional
    public User save(@Validated User user){
        return userRepository.save(user);};

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User update(User user) {
        User existing = findById(user.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException(format("User with such id=%d wasn't found", user.getId())));
        beanUtilService.copyProperties(user, existing);

        return userRepository.save(existing);
    }
}
