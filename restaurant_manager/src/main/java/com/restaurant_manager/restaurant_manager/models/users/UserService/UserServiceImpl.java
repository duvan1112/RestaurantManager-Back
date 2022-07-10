package com.restaurant_manager.restaurant_manager.models.users.UserService;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurant_manager.restaurant_manager.models.users.Role;
import com.restaurant_manager.restaurant_manager.models.users.User;
import com.restaurant_manager.restaurant_manager.models.users.dto.UserRegistryDTO;
import com.restaurant_manager.restaurant_manager.models.users.repository.RoleRepository;
import com.restaurant_manager.restaurant_manager.models.users.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository rolRepository;
    @Override
    public User save(UserRegistryDTO userRegistryDTO) {
        User user = new User(userRegistryDTO.getName(), userRegistryDTO.getLastName(), userRegistryDTO.getEmail(),
        passwordEncoder.encode(userRegistryDTO.getPassword()), Arrays.asList(rolRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mappAuthoritiesRoles(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mappAuthoritiesRoles(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}