package com.stevensgv.auth_service.service;

import com.stevensgv.auth_service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO userDTO = userFeign.getUserByEmail(email);

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userDTO.roles()
                .forEach(role ->
                        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role))));

        userDTO.permissions()
                .forEach(permission ->
                        authorityList.add(new SimpleGrantedAuthority(permission)));

        return new User(
                userDTO.email(),
                userDTO.password(),
                userDTO.enabled(),
                userDTO.accountNonExpired(),
                userDTO.accountNonLocked(),
                userDTO.credentialsNonExpired(),
                authorityList);
    }
}
