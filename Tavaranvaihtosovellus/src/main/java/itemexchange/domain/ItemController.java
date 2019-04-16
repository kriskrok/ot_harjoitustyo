package itemexchange.domain;

import itemexchange.persistence.*;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

}
