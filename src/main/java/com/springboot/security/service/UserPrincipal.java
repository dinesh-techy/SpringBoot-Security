package com.springboot.security.service;

import com.springboot.security.models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private Users users;
    public UserPrincipal(Users user) {
        this.users=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((new SimpleGrantedAuthority("USER")));
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    //Assuming Account non expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //Assuming Account is not locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //Assuming Account is nonExpired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Assuming Acocount is enabled
    @Override
    public boolean isEnabled() {
        return true;
    }
}
