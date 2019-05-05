package itemexchange.ui;

import itemexchange.Itemexchange;
import itemexchange.domain.Item;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;


public class AddItemView implements Screen {
    
    private Scene addItemView;
    
    public AddItemView() {
        
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(20, 20, 20, 20));
        
        HBox hbTop = new HBox();
        hbTop.setAlignment(Pos.BASELINE_RIGHT);
        hbTop.setPadding(new Insets(15, 12, 15, 12));
        hbTop.setSpacing(10);
        hbTop.setStyle("-fx-background-color: #B1C8DB;");

        Button itemlistBtn = Itemexchange.getComponentCreator().createButtonWithDropShadow("Itemlisting");
        itemlistBtn.setOnAction((ActionEvent event) -> {
            new UserView().start();
        });

        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);
        Button closeBtn = Itemexchange.getComponentCreator().createCloseButton();
        hbTop.getChildren().addAll(itemlistBtn, closeBtn);
        
        HBox hbBottom = new HBox();
        
        Text text = new Text("Itemexchange");
        text.setFont(Font.font("Purisa", FontPosture.ITALIC, 78));
        text.setEffect(Itemexchange.getComponentCreator().getDropShadow());

        hbBottom.getChildren().add(text);
        hbBottom.setAlignment(Pos.CENTER);
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label lblItemName = Itemexchange.getComponentCreator().createLabel("Name");
        TextField txtItemName = Itemexchange.getComponentCreator().createTextField("Item");
        Label lblDescription = Itemexchange.getComponentCreator().createLabel("Description");
        TextArea txtDescription = Itemexchange.getComponentCreator().createTextArea("description");
        Button addItemBtn = Itemexchange.getComponentCreator().createButton("Add item");
        Label lblMessage = Itemexchange.getComponentCreator().createLabel("");
        addItemBtn.setOnAction(e -> {
            String itemName = txtItemName.getText();
            String itemDescription = txtDescription.getText();
            
            if (itemName.isEmpty() || itemDescription.isEmpty()) {
                lblMessage.setText("Item must have both name and description!");
                lblMessage.setTextFill(Color.RED);
                
                return;
            }
            
            Item item = new Item();
            item.setName(txtItemName.getText());
            item.setDescription(txtDescription.getText());
            item.setOwnerId(Itemexchange.getItemExchangeService().getCurrentUser().getId());
            
            Itemexchange.getItemDao().saveOrUpdate(item);
            
            lblMessage.setText("");
            
            new AddItemView().start();
        });
        
        gridPane.add(lblItemName, 0, 0);
        gridPane.add(txtItemName, 1, 0);
        gridPane.add(lblDescription, 0, 1);
        gridPane.add(txtDescription, 1, 1);
        gridPane.add(addItemBtn, 1, 2);
        gridPane.add(lblMessage, 1, 3);

        bp.setTop(hbTop);
        bp.setCenter(gridPane);
        bp.setBottom(hbBottom);
        
        addItemView = new Scene(bp);
    }
    
    @Override
    public void start() {
        Itemexchange.getStage().setScene(addItemView);
    }
    
}
