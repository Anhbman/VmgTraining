package com.example.vmg.service;

import com.example.vmg.Repository.RoleRepository;
import com.example.vmg.Repository.UserRepository;
import com.example.vmg.model.Role;
import com.example.vmg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User getUser(String userName) {
        return userRepository.findByUsername(userName);
    }

    public User register(User user) {
        String pass = user.getPassword();
        System.out.println(pass);
        user.setPassword(passwordEncoder.encode(pass));
        return userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("User" + username + "not found");
        }

        List<Role> roleList = roleRepository.findAllByUser(userRepository.findByUsername(username));

        Set<GrantedAuthority> authorities = new HashSet<>();

        String pass = user.getPassword();

        if (roleList != null) {
            for (Role role : roleList) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
            }
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
