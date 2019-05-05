package itemexchange.ui;

import itemexchange.Itemexchange;
import itemexchange.domain.Item;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class UserView implements Screen {
    private Scene userView;
    
    public UserView() {
        GridPane root = new GridPane();
        root.setHgap(8);
        root.setVgap(8);
        root.setPadding(new Insets(5));
        root.setGridLinesVisible(false);
        
        ColumnConstraints cons1 = new ColumnConstraints();
        cons1.setHgrow(Priority.NEVER);
        ColumnConstraints cons2 = new ColumnConstraints();
        cons2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().addAll(cons1, cons2);
        
        RowConstraints rcons1 = new RowConstraints();
        rcons1.setVgrow(Priority.NEVER);
        RowConstraints rcons2 = new RowConstraints();
        rcons2.setVgrow(Priority.ALWAYS);
        root.getRowConstraints().addAll(rcons1, rcons2);
        
        Label lbl = Itemexchange.getComponentCreator().createLabel("Search: ");
        TextField field = Itemexchange.getComponentCreator().createTextField("TO BE IMPLEMENTED");
        field.setDisable(true);
        ListView view = new ListView();
        Button closeBtn = Itemexchange.getComponentCreator().createCloseButton();
        Button okBtn = Itemexchange.getComponentCreator().createButtonWithDropShadow("Add item");
        okBtn.setOnAction(e -> {
            new AddItemView().start();
        });
        
        GridPane.setHalignment(okBtn, HPos.RIGHT);
        TableView<Item> table = new TableView<>();
        ArrayList<Item> items1 = new ArrayList<>(itemexchange.Itemexchange.getItemDao().findAll());
        
        ObservableList<Item> items = FXCollections.observableArrayList(items1);
        table.setItems(items);
        
        TableColumn<Item, String> itemNameCol = new TableColumn<>("Item");
        itemNameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Item, String> userNameCol = new TableColumn<>("User");
        userNameCol.setCellValueFactory(new PropertyValueFactory("owner_id"));
        TableColumn<Item, String> descriptionCol = new TableColumn<>("Description");
        userNameCol.setCellValueFactory(new PropertyValueFactory("description"));
        table.getColumns().setAll(itemNameCol, userNameCol, descriptionCol);
        
        root.add(lbl, 0, 0);
        root.add(field, 1, 0, 3, 1);
        root.add(table, 0, 1, 4, 2);
        root.add(okBtn, 2, 3);
        root.add(closeBtn, 3, 3);
        
        userView = new Scene(root);        
    }
    
    
    /**
    * Sets the Scene-object initialized by the constructor to be displayed
    */
    @Override
    public void start() {
        Itemexchange.getStage().setScene(userView);
    }
    
}
