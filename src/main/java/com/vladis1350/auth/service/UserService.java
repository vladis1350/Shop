package com.vladis1350.auth.service;

import com.vladis1350.auth.bean.Role;
import com.vladis1350.auth.bean.User;
import com.vladis1350.auth.bean.UserRoles;
import com.vladis1350.auth.repositories.RoleRepository;
import com.vladis1350.auth.repositories.UserRepository;
import com.vladis1350.shop.repositories.interfaces.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByUserName(String userName) {
//        return erasePasswordDataBeforeResponse(userRepository.findByUserName(userName)); todo: delete user password data if necessary
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user, Optional<UserRoles> userRoleOp) {
        UserRoles newUserRole = userRoleOp.orElseGet(() -> UserRoles.ROLE_GUEST);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);

        Role userRole = roleRepository.findByRole(newUserRole);
        if (userRole == null) {

            userRole = roleRepository.save(new Role(null, newUserRole));
        }
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public User getCurrentAuthenticationUser() {
        return userRepository.findByUserName(getCurrentUsername());
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public static User erasePasswordDataBeforeResponse(User user) {
        user.setPassword("");
        return user;
    }
}
