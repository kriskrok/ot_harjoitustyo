package itemexchange.ui;

import javafx.scene.Scene;
import itemexchange.Itemexchange;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class LoginScreen implements Screen {

    private Scene loginScreen;

    public LoginScreen() {

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(20, 20, 20, 20));

        HBox hb = new HBox();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label lblUserName = new Label("Username");
        final TextField txtUserName = new TextField();
        Label lblPassword = new Label("Password");
        final PasswordField pf = new PasswordField();
        Button btnLogin = new Button("Login");
        btnLogin.setDefaultButton(true);
        final Label lblMessage = new Label();

        gridPane.add(lblUserName, 0, 0);
        gridPane.add(txtUserName, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(pf, 1, 1);
        gridPane.add(btnLogin, 2, 1);
        gridPane.add(lblMessage, 1, 2);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        Text text = new Text("Itemexchange Login");
        text.setFont(Font.font("Purisa", FontPosture.ITALIC, 78));
        text.setEffect(dropShadow);

        hb.getChildren().add(text);
        hb.setAlignment(Pos.CENTER);

        bp.setId("bp");
        gridPane.setId("root");
        btnLogin.setId("btnLogin");
        text.setId("text");

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String checkUser = txtUserName.getText().toString();
                String checkPw = pf.getText().toString();

                if (Itemexchange.getItemExchangeService().checkCredentials(checkUser, checkPw)) {
                    lblMessage.setText("");
                    //Fix me!
                    new UserView().start();
                } else {
                    lblMessage.setText("Incorrect user or pw");
                    lblMessage.setTextFill(Color.RED);
                }
                txtUserName.clear();
                pf.clear();

            }
        });

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
        bp.setTop(hb);
        bp.setCenter(gridPane);

        loginScreen = new Scene(bp);
    }

    /**
     * Sets the Scene-object initialized by the constructor to be displayed
     */
    @Override
    public void start() {
        Itemexchange.getStage().setScene(loginScreen);
    }
}
