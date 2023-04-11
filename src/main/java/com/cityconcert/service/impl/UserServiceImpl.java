package com.cityconcert.service.impl;

import com.cityconcert.domain.Role;
import com.cityconcert.domain.User;
import com.cityconcert.domain.dto.UserDTO;
import com.cityconcert.mapper.UserMapper;
import com.cityconcert.repository.AuthorityRepository;
import com.cityconcert.repository.UserRepository;
import com.cityconcert.security.SecurityUtils;
import com.cityconcert.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);
private String currentPassword;
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*
        public Optional<User> activateRegistration(String key) {
            log.debug("Activating user for activation key {}", key);
            return userRepository
                    .findOneByActivationKey(key)
                    .map(user -> {
                        // activate given user for the registration key.
                        user.setActivated(true);
                        log.debug("Activated user: {}", user);
                        return er;
                    });
        }
        /*
            public Optional<User> completePasswordReset(String newPassword, String key) {
                log.debug("Reset user password for reset key {}", key);
                return userRepository
                        .findOneByResetKey(key)
                        .filter(user -> user.getResetDate().isAfter(Instant.now().minus(1, ChronoUnit.DAYS)))
                        .map(user -> {
                            user.setPassword(passwordEncoder.encode(newPassword));
                            user.setResetKey(null);
                            user.setResetDate(null);
                            return user;
                        });
            }

            /*  public Optional<User> requestPasswordReset(String mail) {
                   return userRepository
                       .findOneByEmailIgnoreCase(mail)
                       .filter(User::isActivated)
                       .map(user -> {
                           user.setResetKey(RandomUtil.generateResetKey());
                           user.setResetDate(Instant.now());
                           return user;
                       });
               }

               public User registerUser(AdminUserDTO userDTO, String password) {
                   userRepository
                       .findOneByLogin(userDTO.getLogin().toLowerCase())
                       .ifPresent(existingUser -> {
                           boolean removed = removeNonActivatedUser(existingUser);
                           if (!removed) {
                               throw new UsernameAlreadyUsedException();
                           }
                       });
                   userRepository
                       .findOneByEmailIgnoreCase(userDTO.getEmail())
                       .ifPresent(existingUser -> {
                           boolean removed = removeNonActivatedUser(existingUser);
                           if (!removed) {
                               throw new EmailAlreadyUsedException();
                           }
                       });
                   User newUser = new User();
                   String encryptedPassword = passwordEncoder.encode(password);
                   newUser.setLogin(userDTO.getLogin().toLowerCase());
                   // new user gets initially a generated password
                   newUser.setPassword(encryptedPassword);
                   newUser.setFirstName(userDTO.getFirstName());
                   newUser.setLastName(userDTO.getLastName());
                   if (userDTO.getEmail() != null) {
                       newUser.setEmail(userDTO.getEmail().toLowerCase());
                   }
                   newUser.setImageUrl(userDTO.getImageUrl());
                   // new user is not active
                   newUser.setActivated(false);
                   // new user gets registration key
                   newUser.setActivationKey(RandomUtil.generateActivationKey());
                   Set<Authority> authorities = new HashSet<>();
                   authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
                   newUser.setAuthorities(authorities);
                   userRepository.save(newUser);
                   log.debug("Created Information for User: {}", newUser);
                   return newUser;
               }
           */
    private boolean removeNonActivatedUser(User existingUser) {
        if (existingUser.isActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        return true;
    }

//    public User createUser(AdminUserDTO userDTO) {
//        User user = new User();
//        user.setLogin(userDTO.getLogin().toLowerCase());
//        user.setFirstName(userDTO.getFirstName());
//        user.setLastName(userDTO.getLastName());
//        if (userDTO.getEmail() != null) {
//            user.setEmail(userDTO.getEmail().toLowerCase());
//        }
//        user.setImageUrl(userDTO.getImageUrl());
//
//        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
//        user.setPassword(encryptedPassword);
//        user.setResetKey(RandomUtil.generateResetKey());
//        user.setResetDate(Instant.now());
//        user.setActivated(true);
//        if (userDTO.getAuthorities() != null) {
//            Set<Authority> authorities = userDTO
//                .getAuthorities()
//                .stream()
//                .map(authorityRepository::findById)
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toSet());
//            user.setAuthorities(authorities);
//        }
//        userRepository.save(user);
//        log.debug("Created Information for User: {}", user);
//        return user;
//    }

    /**
     * Update all information for a specific user, and return the modified user.
     * <p>
     * //@param userDTO user to update.
     *
     * @return updated user.
     */
   /* public Optional<AdminUserDTO> updateUser(AdminUserDTO userDTO) {
        return Optional
                .of(userRepository.findById(userDTO.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> {
                    user.setUsername(userDTO.getLogin().toLowerCase());

                    if (userDTO.getEmail() != null) {
                        user.setEmail(userDTO.getEmail().toLowerCase());
                    }
                    user.setImageUrl(userDTO.getImageUrl());
                    user.setActivated(userDTO.isActivated());
                    Set<Authority> managedAuthorities = user.getAuthorities();
                    managedAuthorities.clear();
                    userDTO
                            .getAuthorities()
                            .stream()
                            .map(authorityRepository::findById)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .forEach(managedAuthorities::add);
                    log.debug("Changed Information for User: {}", user);
                    return user;
                })
                .map(AdminUserDTO::new);
    }
*/
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

        @Transactional(readOnly = true)
        public Page<AdminUserDTO> getAllManagedUsers(Pageable pageable) {
            return userRepository.findAll(pageable).map(AdminUserDTO::new);
        }

        @Transactional(readOnly = true)
        public Page<UserDTO> getAllPublicUsers(Pageable pageable) {
            return userRepository.findAllByIdNotNullAndActivatedIsTrue(pageable).map(UserDTO::new);
        }

        @Transactional(readOnly = true)
        public Optional<User> getUserWithAuthoritiesByLogin(String login) {
            return userRepository.findOneWithAuthoritiesByUsername(login);
        }

        @Transactional(readOnly = true)
        public Optional<User> getUserWithAuthorities() {
            return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByUsername);
        }

        /**
         * Not activated users should be automatically deleted after 3 days.
         * <p>
         * This is scheduled to get fired everyday, at 01:00 (am).

    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        userRepository
                .findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS))
                .forEach(user -> {
                    log.debug("Deleting not activated user {}", user.getUsername());
                    userRepository.delete(user);
                });
    }
*/

    /**
     * Gets a list of all the authorities.
     *
     * @return a list of all the authorities.
     */
    @Transactional(readOnly = true)
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Role::getName).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        log.debug("Request to get all Users");
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Optional<UserDTO> findOne(Long id) {
        log.debug("Request to get User : {}", id);
        return Optional.ofNullable(userMapper.toDto(userRepository.findById(id).get()));
    }

    public UserDTO save(UserDTO user) {
        User newUser = new User();

        String encryptedPassword =
                passwordEncoder.encode(
                        user.getPassword());
        newUser.setUsername(user.getUsername());
        // new user gets initially a generated password
        newUser.setRole("USER");
        newUser.setPassword(encryptedPassword);
        if (user.getEmail() != null) {
            newUser.setEmail(user.getEmail());
        }
        newUser.setImageUrl(user.getImageUrl());
        //newUser = userMapper.toEntity(user);
        userRepository.save(newUser);
        return userMapper.toDto(newUser);
    }

    @Override
    public UserDTO getCurrentUser() {
        System.out.println(SecurityContextHolder.getContext().
                getAuthentication().getName());
        User currentUser = userRepository.findByUsername(
                SecurityContextHolder.getContext().
                        getAuthentication().getName()).get();
        return userMapper.toDto(currentUser);
    }


}
