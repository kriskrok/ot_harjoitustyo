package itemexchange.ui;

import itemexchange.domain.ItemExchangeService;
import itemexchange.domain.User;
import itemexchange.domain.Item;
import itemexchange.Main;
import itemexchange.domain.Inventory;
import itemexchange.persistence.ItemRepository;
import itemexchange.persistence.UserRepository;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class TextInterface {

    private Scanner reader = new Scanner(System.in);
    private Inventory inventory = new Inventory();

    @Autowired
    private ItemExchangeService itemExchangeService;
    
    @Autowired(required = true)
    private UserRepository userRepository;

    public void start() {
        /*
        User matti = new User();
        matti.setName("Matti");
        matti.setUsername("Masa");
        
        

        Item item1 = new Item();
        item1.setName("Mug");
        item1.setOwner(matti);
        
        Item item2 = new Item("Holy Grail", matti);
        Item item3 = new Item("Shiny Bauble", matti);
        
        itemExchangeService.saveItem(item1);
        itemExchangeService.saveUser(matti);
        itemExchangeService.saveItem(item2);
        itemExchangeService.saveItem(item3);
        */

        printWelcomeMessage();
        while (true) {
            printInstructions();
            String input = reader.nextLine();
            if (input.equals("5")) {
                break;
            }
            executeCommand(input);
        }
        
        System.out.println(printFarewellMessage());
    }

    public void executeCommand(String input) {
        switch (input) {
            case "1":
                listAllItems();
                break;
            case "2":
                Main.launch();
                System.out.println("input was 2");
                break;
            case "3":
                itemExchangeService.countUsers();
                break;
            case "4":
                try {
                    String importantInfo[] = {
                        "Mares eat oats",
                        "Does eat oats",
                        "Little lambs eat ivy",
                        "A kid will eat ivy too"
                    };

                    for (int i = 0; i < importantInfo.length; i++) {
                        Thread.sleep(2000);
                        System.out.println(importantInfo[i]);
                    }

                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TextInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                System.out.println("input was: " + input);
        }
    }

    public void listAllItems() {
        System.out.println("Current listed items: ");
        
        for (String user : this.inventory.getInventory().keySet()) {
            for (Item item : this.inventory.getUserItems(user)) {
                System.out.println("\t" + item);
            }
        }

    }

    public void printInstructions() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("What would you like to do?").append(System.lineSeparator());
        sb.append("[1] View all available items").append(System.lineSeparator());
        sb.append("[2] Get a glimpse of the AWESOME graphical interface (beta)").append(System.lineSeparator());
        sb.append("[3] To be implemented").append(System.lineSeparator());
        sb.append("[4] Ponder the meaning of life for a full 10000 milliseconds").append(System.lineSeparator());
        sb.append("[5] Exit program").append(System.lineSeparator());
        sb.append("> ");

        System.out.print(sb.toString());
    }

    public void printWelcomeMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to itemexchange beta!").append(System.lineSeparator());
        sb.append("It is currently: ").append(System.lineSeparator());
        sb.append("\t");
        sb.append(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
        System.out.println(sb.toString());
    }

    public String printFarewellMessage() {
        return "So long, and thanks for all the fish!";
    }

}
