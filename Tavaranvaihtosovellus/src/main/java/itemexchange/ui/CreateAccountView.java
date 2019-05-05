package itemexchange.ui;

import itemexchange.Itemexchange;
import itemexchange.domain.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;


public class CreateAccountView implements Screen {
    
    private Scene createAccountView;
    
    public CreateAccountView() {
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(20, 20, 20, 20));
        
        HBox hbTop = new HBox();
        hbTop.setAlignment(Pos.BASELINE_RIGHT);
        hbTop.setPadding(new Insets(15, 12, 15, 12));
        hbTop.setSpacing(10);
        hbTop.setStyle("-fx-background-color: #B1C8DB;");

        Button itemlistBtn = Itemexchange.getComponentCreator().createButtonWithDropShadow("Login");
        itemlistBtn.setOnAction((ActionEvent event) -> {
            new LoginScreen().start();
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

        Label lblName = Itemexchange.getComponentCreator().createLabel("Name");
        TextField txtName = Itemexchange.getComponentCreator().createTextField("Name");
        Label lblUsername = Itemexchange.getComponentCreator().createLabel("Username");
        TextField txtUsername = Itemexchange.getComponentCreator().createTextField("Username");
        Label lblPassword = Itemexchange.getComponentCreator().createLabel("Password");
        PasswordField pf = Itemexchange.getComponentCreator().createPasswordfield("Password");
        Button btnCreate = Itemexchange.getComponentCreator().createButtonWithDropShadow("Create");
        Label lblMessage = Itemexchange.getComponentCreator().createLabel("Create a new account");
        lblMessage.setFont(new Font("Arial", 20));
        
        btnCreate.setOnAction(e -> {
            String usersName = txtName.getText();
            String username = txtUsername.getText();
            String password = pf.getText();
            
            lblMessage.setFont(new Font("Arial", 30));
            
            if (usersName.isEmpty() || username.isEmpty() | password.isEmpty()) {
                
                lblMessage.setText("Kindly provide sufficient credentials!");
                lblMessage.setTextFill(Color.RED);
                
                return;
            }
            User user = new User();
            user.setName(usersName);
            user.setUsername(username);
            user.setPassword(password);
            Itemexchange.getUserDao().saveOrUpdate(user);
            
            lblMessage.setText("Most obliged.");
            lblMessage.setTextFill(Color.LIGHTSEAGREEN);
            
            txtName.setText("");
            txtUsername.setText("");
            pf.setText("");
        });
        
        gridPane.add(lblName, 0, 0);
        gridPane.add(txtName, 1, 0);
        gridPane.add(lblUsername, 0, 1);
        gridPane.add(txtUsername, 1, 1);
        gridPane.add(lblPassword, 0, 2);
        gridPane.add(pf, 1, 2);
        gridPane.add(btnCreate, 2, 3);
        gridPane.add(lblMessage, 2, 4);

        bp.setTop(hbTop);
        bp.setCenter(gridPane);
        bp.setBottom(hbBottom);
        
        createAccountView = new Scene(bp);
    }
    
    @Override
    public void start() {
        Itemexchange.getStage().setScene(createAccountView);
    }
    
}
