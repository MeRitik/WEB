package com.ritik.scm.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ritik.scm.entities.User;
import com.ritik.scm.helpers.AppConstants;
import com.ritik.scm.helpers.ResourceNotFoundException;
import com.ritik.scm.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        // Generate dynamic userId
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        logger.info(user.getProvider().toString());
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User tempUser = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // update tempUser
        tempUser.setName(user.getName());
        tempUser.setEmail(user.getEmail());
        tempUser.setPassword(user.getPassword());
        tempUser.setAbout(user.getAbout());
        tempUser.setPhoneNumber(user.getPhoneNumber());
        tempUser.setProfilePic(user.getProfilePic());
        tempUser.setEnabled(user.isEnabled());
        tempUser.setEmailVerified(user.isEmailVerified());
        tempUser.setPhoneVerified(user.isPhoneVerified());
        tempUser.setProvider(user.getProvider());
        tempUser.setProviderUserId(user.getProviderUserId());

        // Save temp user in database
        User savedUser = userRepo.save(tempUser);

        return Optional.ofNullable(savedUser);
    }

    @Override
    public void deleteUser(String id) {
        User tempUser = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepo.delete(tempUser);
    }

    @Override
    public boolean isUserExist(String userId) {
        User tempUser = userRepo.findById(userId).orElse(null);
        return tempUser == null ? false : true;

    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user == null ? false : true;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
