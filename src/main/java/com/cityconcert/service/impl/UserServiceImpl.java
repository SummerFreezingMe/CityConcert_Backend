package com.cityconcert.service.impl;

import com.cityconcert.domain.User;
import com.cityconcert.domain.dto.UserDTO;
import com.cityconcert.mapper.UserMapper;
import com.cityconcert.repository.UserRepository;
import com.cityconcert.security.SecurityUtils;
import com.cityconcert.service.UserService;
import com.cityconcert.service.exceptions.EmailAlreadyUsedException;
import com.cityconcert.service.exceptions.UsernameAlreadyUsedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public void deleteUser(String login) {
        userRepository
                .findByUsername(login)
                .ifPresent(user -> {
                    userRepository.delete(user);
                    log.debug("Deleted User: {}", user);
                });
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param userDTO   data to update the current user.
     */
    public UserDTO updateUser(UserDTO userDTO) {
        AtomicReference<UserDTO> updated =new AtomicReference<>();
        SecurityUtils
                .getCurrentUserLogin()
                .flatMap(userRepository::findByUsername)
                .ifPresent(user -> {

                    if (userDTO.getEmail() != null) {
                        user.setEmail(userDTO.getEmail());
                    }
                    user.setImageUrl(userDTO.getImageUrl());
                    log.debug("Changed Information for User: {}", user);

                    updated.set(userMapper.toDto(user));
                });
        return updated.get();
    }
    /*
        @Transactional
        public void changePassword(String currentClearTextPassword, String newPassword) {
            SecurityUtils
                    .getCurrentUserLogin()
                    .flatMap(userRepository::findOneByUsername)
                    .ifPresent(user -> {
                        String currentEncryptedPassword = user.getPassword();
                        if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                            throw new InvalidPasswordException();
                        }
                        String encryptedPassword = passwordEncoder.encode(newPassword);
                        user.setPassword(encryptedPassword);
                        log.debug("Changed password for User: {}", user);
                    });
        }
*/


    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        log.debug("Request to get all Users");
        return userRepository.findAll().stream()
                .map(userMapper::toDto).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Optional<UserDTO> findOne(Long id) {
        log.debug("Request to get User : {}", id);
        return Optional.ofNullable(userMapper.toDto(userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id: "+ id))));
    }

    public UserDTO save(UserDTO user) {
        List<User> existingUsers = userRepository.findAll();
        User newUser = new User();

        String encryptedPassword =
                passwordEncoder.encode(
                        user.getPassword());
        newUser.setUsername(user.getUsername());
        newUser.setRole(user.getRole());
        newUser.setPassword(encryptedPassword);
        if (user.getEmail() != null) {
            newUser.setEmail(user.getEmail());
        }
        for (User u:
             existingUsers) {
            if(Objects.equals(u.getEmail(), user.getEmail())){
                throw new EmailAlreadyUsedException();
            }
                if(Objects.equals(u.getUsername(), user.getUsername())){
                throw new UsernameAlreadyUsedException();
            }
        }
        newUser.setImageUrl(user.getImageUrl());
        userRepository.save(newUser);
        return userMapper.toDto(newUser);
    }

    @Override
    public UserDTO getCurrentUser() {
        System.out.println(SecurityContextHolder.getContext().
                getAuthentication().getName());
        User currentUser = userRepository.findByUsername(
                SecurityContextHolder.getContext().
                        getAuthentication().getName()).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
        return userMapper.toDto(currentUser);
    }


}
