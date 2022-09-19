package com.vmg.buoi9_thuchanh1.service;

import com.vmg.buoi9_thuchanh1.dao.AppRoleDAO;
import com.vmg.buoi9_thuchanh1.dao.AppUserDAO;
import com.vmg.buoi9_thuchanh1.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private AppRoleDAO appRoleDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = this.appUserDAO.findUserAccount(username);

        if (appUser == null) {
            System.out.println("User not found!" + username);
            throw new UsernameNotFoundException("User" + username + "was not found in the database");
        }

        System.out.println("Found User: " + appUser);

        List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantedAuthorityList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(),
                appUser.getEncrytedPassword(),
                grantedAuthorityList);

        return userDetails;
    }
}
