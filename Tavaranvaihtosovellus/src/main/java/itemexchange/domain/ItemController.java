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
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    
    public Optional<Item> getItemById(Long itemId) throws Exception {
        if (!itemRepository.existsById(itemId)) {
            throw new RuntimeException("Item with id " + itemId + " is not yet a part of the Creation.");
        }
        
        return itemRepository.findById(itemId);
    }
    
    public Item createItem(String ownerUsername, Item item) throws Exception {
        userRepository.findByName(ownerUsername);
        
        User owner = new User();
        
        Optional<User> byOwner = userRepository.findByName(ownerUsername);
        if (!byOwner.isPresent()) {
            throw new RuntimeException("User with username " + ownerUsername + " is not yet a part of the Creation.");
        }
        owner = byOwner.get();
        

        
        //tie Owner to item
        item.setOwner(owner);
        

        
        Item savedItem = itemRepository.save(item);

        System.out.println("NANANAN\nNANANANN\nNANANANAN\n" + owner.getName() + "\nNANANANAN\nNANANANAN");
        
        //tie Item to Owner
        owner.getItems()/*.add(savedItem)*/;
        
        System.out.println("BATMAN\nBATMAN\nBATMAN\nBATMAN\nBATMAN\nBATMAN\nBATMAN\nBATMAN\n");
        
        return savedItem;
    }
    
    public Item updateItemById(Long itemId, Item itemRequest) {
        if (!itemRepository.existsById(itemId)) {
            throw new RuntimeException("Item with id " + itemId + " is not yet a part of the Creation.");
        }
        Optional<Item> item = itemRepository.findById(itemId);
        
        if (!item.isPresent()) {
            throw new RuntimeException("Item with id " + itemId + " is not yet a part of the Creation.");
        }
        
        Item item1 = item.get();
        item1.setName(itemRequest.getName());
        
        return itemRepository.save(item1);
        
    }
    

}
