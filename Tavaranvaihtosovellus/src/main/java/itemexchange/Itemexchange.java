package itemexchange;

import itemexchange.domain.ItemExchangeService;
import javafx.application.Application;
import javafx.stage.Stage;
import itemexchange.ui.Screen;
import itemexchange.persistence.Database;
import itemexchange.persistence.ItemDao;
import itemexchange.persistence.UserDao;
import itemexchange.ui.CreateAccountView;
import itemexchange.ui.components.ComponentCreator;

/**
 * Main class for the program
 */

public class Itemexchange extends Application {
    private static Stage stage;
    private static Screen screen;
    private static ItemDao itemDao;
    private static UserDao userDao;
    private static Database database;
    private static ComponentCreator componentCreator;
    private static ItemExchangeService itemExchangeService;
    

    public static void main(String[] args) throws Exception {
        itemExchangeService = new ItemExchangeService();
        database = new Database("jdbc:h2:./database");
        itemDao = new ItemDao(database);
        userDao = new UserDao(database);
        componentCreator = new ComponentCreator();
        
        itemDao.setUserDao(userDao);
        userDao.setItemDao(itemDao);
        
        launch(Itemexchange.class);
    }
    
    /**
     * Starts the graphical interface
     * 
     * @param stage Stage-object for the interface
     * @throws Exception exception
     */
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        setScreen(new CreateAccountView());
        stage.setTitle("Itemexchange");
        stage.setWidth(960);
        stage.setHeight(540);
        stage.setResizable(false);
        
        screen.start();
        stage.show();
    }
    
    public static Stage getStage() {
        return stage;
    }
    
    public static void setScreen(Screen newScreen) {
        screen = newScreen;
    }
    
    public static ItemExchangeService getItemExchangeService() {
        return itemExchangeService;
    }
    
    public static ComponentCreator getComponentCreator() {
        return componentCreator;
    }
    
    public static ItemDao getItemDao() {
        return itemDao;
    }
    
    public static UserDao getUserDao() {
        return userDao;
    }

}