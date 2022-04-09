package com.example.MyTwitter.services;

import com.example.MyTwitter.entities.Role;
import com.example.MyTwitter.entities.User;
import com.example.MyTwitter.repositories.RoleRepository;
import com.example.MyTwitter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("There is no user with this username");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user.getRoles()));
    }


    private static Collection<? extends GrantedAuthority> getAuthority(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }


    public boolean save(User user) {
        if (userRepository.existsUserByUsername(user.getUsername()))
            return false;


        user.addRole(roleRepository.getById(1L));
        String newPassword = encoder.encode(user.getPassword());
        user.setPassword(newPassword);

        userRepository.save(user);
        return true;
    }

    public void addAdminRole(User user) {
        if (!user.getRoles().contains((Role) roleRepository.getById(2L)))
        user.getRoles().add(roleRepository.getById(2L));
        userRepository.save(user);
    }

    public void saveChanges(User oldUser, User newUser) {
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setBirthday(newUser.getBirthday());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setSex(newUser.getSex());

        userRepository.save(oldUser);
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }
}
