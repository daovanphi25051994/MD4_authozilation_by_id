package com.example.demo.service.user;

import com.example.demo.model.AppUser;
import com.example.demo.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService , UserDetailsService {

    @Autowired
    private IAppUserRepository userRepository;

    @Override
    public AppUser getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public AppUser getCurrentUser() {
        AppUser user;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        user = this.getUserByUsername(userName);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = this.getUserByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user.getRole());
        UserDetails userDetails = new User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
        return userDetails;
    }

    @Override
    public Iterable<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}