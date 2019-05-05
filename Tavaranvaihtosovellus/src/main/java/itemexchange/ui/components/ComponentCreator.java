package itemexchange.ui.components;

import itemexchange.Itemexchange;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


/**
 * Utility methods for creating various UI-components
 */
public class ComponentCreator {
    
    /**
     * Creates a label containing given text
     * 
     * @param text text to be included in the label
     * @return Label object given text-attribute
     */
    public Label createLabel(String text) {
        Label lbl = new Label(text);
        final TextField txtUserName = new TextField();
        
        return lbl;
    }
    
    /**
     * Creates a button with given text
     * 
     * @param text to be written on the button
     * @return Button object with given text-attribute
     * 
     * @see itemexchange.ui.components.ComponentCreator#createButtonWithDropShadow(java.lang.String) 
     */
    public Button createButton(String text) {
        Button btn = new Button(text);
        btn.setPrefSize(100, 20);
        btn.setId("btn" + text);
        
        return btn;
    }
    
    /**
     * Creates a button with given text  and a dropshadow effect
     * 
     * @param text to be written on the button
     * @return  Button object with given text-attribute
     * 
     * @see itemexchange.ui.components.ComponentCreator#createCloseButton()
     * @see itemexchange.ui.components.ComponentCreator#getDropShadow()
     */
    public Button createButtonWithDropShadow(String text) {
        Button btn = new Button(text);
        btn.setPrefSize(100, 20);
        btn.setId("btn" + text);
        
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
            event -> btn.setEffect(this.getDropShadow()));
        
        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
            event -> btn.setEffect(null));
          
        return btn;
        
    }
    
    /**
     * Creates a button equipped with a dropShadow effect,
     * text that reads 'Close'"and onAction eventHandler that
     * closes the application by calling close() on Stage-object
     * 
     * @return Button object with described properties
     */
    public Button createCloseButton() {
        Button btn = createButtonWithDropShadow("Close");

        btn.setOnAction((ActionEvent event) -> {
            Itemexchange.getItemExchangeService().printFarewellMessage();
            Itemexchange.getStage().close();
        });
          
        return btn;
    }
    
    
    /**
     * Creates a TextField object with given promptText-attribute
     * 
     * @param prompText to be included on the textField
     * @return TextField object with included prompText
     */
    public TextField createTextField(String prompText) {
        TextField txt = new TextField();
        txt.setPrefWidth(300);
        
        if (!prompText.isEmpty()) {
            txt.setPromptText(prompText);
        }
        
        return txt;
    }
    
    /**
     * Creates a TextArea object with given prompText-attribute
     * 
     * @param prompText to be included in the TextArea
     * @return TextArea object with included prompText
     */
    public TextArea createTextArea(String prompText) {
        TextArea txt = new TextArea();
        txt.setPrefSize(700, 450);
        
        if (!prompText.isEmpty()) {
            txt.setPromptText(prompText);
        }
        return txt;
        
    }
    
    /**
     * Creates a PasswordField object with  given prompText-attribute
     * 
     * @param prompText to be included in the PasswordField
     * @return PasswordField object with included prompText
     */
    public PasswordField createPasswordfield(String prompText) {
        PasswordField pf = new PasswordField();
        
        if (!prompText.isEmpty()) {
            pf.setPromptText(prompText);
        }
        
        return pf;
    }
    

    
    /**
     * Creates a DropShadow effect with an offset-value 5
     * 
     * @return DropShadow object with described offset
     */
    public DropShadow getDropShadow() {
        DropShadow ds = new DropShadow();
        ds.setOffsetX(5);
        ds.setOffsetY(5);
        
        return ds;
    }
    
}
