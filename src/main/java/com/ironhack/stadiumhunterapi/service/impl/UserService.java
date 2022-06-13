package com.ironhack.stadiumhunterapi.service.impl;

import com.ironhack.stadiumhunterapi.model.Stadium;
import com.ironhack.stadiumhunterapi.model.User;
import com.ironhack.stadiumhunterapi.repository.RoleRepository;
import com.ironhack.stadiumhunterapi.repository.UserRepository;
import com.ironhack.stadiumhunterapi.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StadiumService stadiumService;

    public User saveUser(User userSignupDTO) {
        log.info("Saving a new user {} inside of the database", userSignupDTO.getName());
        User user = new User(userSignupDTO.getName(), userSignupDTO.getEmail(), userSignupDTO.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public void addStadiumToUser( Long stadiumId, Authentication authentication){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            email = authentication.getName();
        }
        User currentUser = userRepository.findByEmail(email);
        Stadium stadiumFromDb = stadiumService.findById(stadiumId);
        if(!currentUser.getHuntedStadiums().contains(stadiumFromDb)){
            currentUser.getHuntedStadiums().add(stadiumFromDb);
        }
        userRepository.save(currentUser);
    }

    public void removeStadium(Long stadiumId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            email = authentication.getName();
        }
        User currentUser = userRepository.findByEmail(email);
        Stadium stadiumFromDb = stadiumService.findById(stadiumId);
        currentUser.getHuntedStadiums().remove(stadiumFromDb);
        userRepository.save(currentUser);
    }

    public List<Stadium> getUserStadiums(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            email = authentication.getName();
        }
        User currentUser = userRepository.findByEmail(email);
        return currentUser.getHuntedStadiums();
    }

    public List<User> getTopUsers(){
        List<Long> users = userRepository.findTopUsers();
        List<User> topUsers = new ArrayList<>();
        for(Long id : users){
            topUsers.add(userRepository.findById(id).orElse(null));
        }
        return topUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User is found in the database: {}", email);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }
    }

}
