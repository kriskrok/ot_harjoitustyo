package itemexchange.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ItemexchangeUi extends Application {

    static final String USER = "Matti";
    static final String PASSWD = "Maija";

    @Override
    public void init() {

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Itemexchange");

        //BorderPane arranges the nodes in top, left, right, bottom and center positions
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        //Hbox layout arranges all the nodes in a single horizontal row
        HBox hb = new HBox();
        hb.setPadding(new Insets(20, 20, 20, 20));

        //GridPane arranges the nodes as a grid of rows and columns
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //Implementing Nodes for GridPane
        Label lblUserName = new Label("Username");
        final TextField txtUserName = new TextField();
        Label lblPassword = new Label("Password");
        final PasswordField pf = new PasswordField();
        Button btnLogin = new Button("Login");
        final Label lblMessage = new Label();

        //Adding Nodes to GridPane layout
        gridPane.add(lblUserName, 0, 0);
        gridPane.add(txtUserName, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(pf, 1, 1);
        gridPane.add(btnLogin, 2, 1);
        gridPane.add(lblMessage, 1, 2);

        //Reflection for gridPane
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        r.setTopOffset(2.0);
        //gridPane.setEffect(r);
        //hb.setEffect(r);

        //DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        //Adding text and DropShadow effect to it
        Text text = new Text("Itemexchange Login");
        text.setFont(Font.font("Purisa", FontPosture.ITALIC, 28));
        text.setEffect(dropShadow);

        //Adding text to HBox
        hb.getChildren().add(text);

        //Add ID's to Nodes
        bp.setId("bp");
        gridPane.setId("root");
        btnLogin.setId("btnLogin");
        text.setId("text");

        //Action for btnLogin
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String checkUser = txtUserName.getText().toString();
                String checkPw = pf.getText().toString();
                System.out.println("User: " + checkUser + "\nPassword: " + checkPw);

                if (checkUser.equalsIgnoreCase(USER) && checkPw.equalsIgnoreCase(PASSWD)) {
                    lblMessage.setText("");
                    primaryStage.setScene(userView());
                } else {
                    lblMessage.setText("Incorrect user or pw");
                    lblMessage.setTextFill(Color.RED);
                }
                txtUserName.clear();
                pf.clear();

            }
        });

        //Add shadow on btnLogin on mousehover
        btnLogin.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnLogin.setEffect(dropShadow);
            }
        });

        btnLogin.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnLogin.setEffect(null);
            }
        });

        //Add Hbox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setCenter(gridPane);

        //Add BorderPane to the scene
        Scene scene = new Scene(bp);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public Scene userView() {

        String cssLayout = "-fx-border-color: red;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: dashed;\n";

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.setStyle(cssLayout);
        
        bp.setLeft(vbox);

        Scene userView = new Scene(bp);
        return userView;
    }

}
