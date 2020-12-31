package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public interface Content{
        public Node getRootLayout(Button positiveBtn, Button negativeBtn, Stage window);
    }

    static boolean connected;
    static Button positiveBtn, negativeBtn;
    static Stage window;

    public static boolean display(String title, String message, Content content) {
        window = new Stage();
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.centerOnScreen();

        /*
         * Alert Box Layout:
         * Root layout:
         *   Label
         *   Content
         *   BtnLayout
         *
         * */

//      Pos/Neg Buttons layouts
        Label label = new Label(message);
        negativeBtn = new Button("No");
        positiveBtn = new Button("Yes");
        HBox btnLayout = new HBox(10);
        btnLayout.getChildren().addAll(negativeBtn, positiveBtn);
        btnLayout.setAlignment(Pos.CENTER);

//      Root Layout
        VBox rootLayout = new VBox(10);
        if (content == null) {
            rootLayout.getChildren().addAll(label, btnLayout);
        } else {
            rootLayout.getChildren().addAll(label, content.getRootLayout(positiveBtn, negativeBtn, window), btnLayout);
        }
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.setPadding(new Insets(10));

        Scene scene = new Scene(rootLayout);
        window.setScene(scene);
        window.showAndWait();

        return connected;
    }
}
