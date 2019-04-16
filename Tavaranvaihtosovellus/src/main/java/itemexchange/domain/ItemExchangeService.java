package itemexchange.domain;

import itemexchange.persistence.ItemRepository;
import itemexchange.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemExchangeServiceMatti")
public class ItemExchangeService {

    static final String USER = "Matti";
    static final String PASSWD = "Maija";
    
    @Autowired
    private UserController userController;
    
    @Autowired
    private ItemController itemController;
    
    public boolean checkCredentials(String user, String passwd) {
        return user.equalsIgnoreCase(USER) && passwd.equalsIgnoreCase(PASSWD);
    }
    
    public void countUsers() {
        System.out.println("Users: " + userController.countUsers());
    }
    
    public void saveUser(User user) {
        userController.saveUser(user);
    }
    
    public void saveItem(Item item) {
        itemController.saveItem(item);
    }

}
