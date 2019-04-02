package itemexchange.ui;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        TextInterface iface = new TextInterface(reader);
        iface.start();
    }

}
