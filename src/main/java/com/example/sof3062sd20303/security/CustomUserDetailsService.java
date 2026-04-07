package com.example.sof3062sd20303.security;

import com.example.sof3062sd20303.entity.User;
import com.example.sof3062sd20303.exception.CustomResourceNotFoundException;
import com.example.sof3062sd20303.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

       User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
               .orElseThrow(() -> new CustomResourceNotFoundException("User not found with username or email: " + usernameOrEmail));

       Set<SimpleGrantedAuthority> authorities =user.getRoles().stream()
               .map(role -> new SimpleGrantedAuthority(role.getName()))
               .collect(Collectors.toSet());

       return new org.springframework.security.core.userdetails.User(usernameOrEmail, user.getPassword(), authorities);
    }


}
