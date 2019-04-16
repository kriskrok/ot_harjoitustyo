package itemexchange;

import itemexchange.ui.ItemexchangeUi;
import itemexchange.ui.TextInterface;
import java.util.*;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        System.exit(SpringApplication.exit(context, () -> 0));
    }

    public static void launch() {
        Application.launch(ItemexchangeUi.class);
    }


}