package itemexchange.domain;

import itemexchange.Itemexchange;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemExchangeService {
    
    static User currentUser;
    
    public ItemExchangeService() {
        initialize();
    }
    
    public boolean checkCredentials(String usrname, String passwd) {
        User user = Itemexchange.getUserDao().findByCredentials(usrname, passwd);
        
        if (user != null) {
            currentUser = user;
        }
        
        return user != null;
    }
    
    public void printImportantInfo() {
        try {
            String importantInfo[] = {
                "\nMares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too\n"
            };

            for (int i = 0; i < importantInfo.length; i++) {
                System.out.println(importantInfo[i]);
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ItemExchangeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    private void initialize() {
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to itemexchange beta!").append(System.lineSeparator());
        sb.append("It is currently: ").append(System.lineSeparator());
        sb.append("\t");
        sb.append(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
        System.out.println(sb.toString());
    }
    
    public void printFarewellMessage() {
        printImportantInfo();
        System.out.println("\nSo long, and thanks for all the fish!");
    }

}
