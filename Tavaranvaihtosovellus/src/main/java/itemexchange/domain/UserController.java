package itemexchange.domain;

import itemexchange.persistence.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Controller
public class UserController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User with id " + userId + " is not yet a part of the Creation.");
        }
        return userRepository.findById(userId);
    }
    
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public User updateUserById(Long userId, User userRequest) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User with id " + userId + " is not yet a part of the Creation.");
        }
        Optional<User> user = userRepository.findById(userId);
        
        if (!user.isPresent()) {
            throw new RuntimeException("User with id " + userId + " is not yet a part of the Creation.");
        }
        
        User user1 = user.get();
        user1.setName(userRequest.getName());
        user1.setUsername(userRequest.getUsername());
        user1.setPassword(user1.getPassword());
        
        return userRepository.save(user1);
    }
    
    public Optional<User> findUserByName(String username) {
        return userRepository.findByName(username);
    }
    
    public long countUsers() {
        return userRepository.count();
    }


}
