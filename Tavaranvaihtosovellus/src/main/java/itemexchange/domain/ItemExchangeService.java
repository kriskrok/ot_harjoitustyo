package itemexchange.domain;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemExchangeServiceMatti")
public class ItemExchangeService {

    static String USER = "Matti";
    static String PASSWD = "Maija";
    
    @Autowired
    private UserController userController;
    
    @Autowired
    private ItemController itemController;
    
    public void setUsername(String username) {
        USER = username;
    }
    
    public void setPassword(String password) {
        PASSWD = password;
    }
    
    public boolean checkCredentials(String user, String passwd) {
        return user.equalsIgnoreCase(USER) && passwd.equalsIgnoreCase(PASSWD);
    }
    
    public void printCredentials() {
        System.out.println("User: " + USER + "\nPassword: " + PASSWD + "\n");
    }
    
    public void countUsers() {
        System.out.println("Users: " + userController.countUsers());
    }
    
    public Optional<User> findUserByName(String username) {
        return userController.findUserByName(username);
    }
    
    public User saveUser(User user) {
        return userController.createUser(user);
    }
    
    public List<Item> getAllItems() {
        return itemController.getAllItems();
    }
    
    public Item createItem(String ownerUsername, Item item) throws Exception  {

        return itemController.createItem(ownerUsername, item);
    }
    
//    public void saveItem(Item item) {
//        try {
//            itemController.createItem(item.getId(), item);
//        } catch (Exception exception) {
//            Logger.getLogger(ItemExchangeService.class.getName()).log(Level.SEVERE, null, exception);
//        }
//        
//    }

}
