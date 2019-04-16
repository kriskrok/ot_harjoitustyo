package itemexchange.domain;

import itemexchange.persistence.*;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    
    public void saveUser(User user) {
        userRepository.save(user);
    }
    
    public long countUsers() {
        return userRepository.count();
    }


}
