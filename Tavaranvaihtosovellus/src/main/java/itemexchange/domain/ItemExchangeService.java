package itemexchange.domain;

import java.util.*;


public class ItemExchangeService {

    static String USER = "Matti";
    static String PASSWD = "Maija";
    
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
    
    public void  /*Item*/ createItem(String ownerUsername, Item item) throws Exception  {

//        return itemController.createItem(ownerUsername, item);
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
