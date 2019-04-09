package itemexchange.ui;

import java.util.*;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        TextInterface iface = new TextInterface(reader);
        iface.start();
    }
    
    public static void launch() {
        Application.launch(ItemexchangeUi.class);
    }

}
