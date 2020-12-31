package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenu {
    public interface EventsHandler {
        public void onHostButtonClicked();
        public void onJoinButtonClicked();
    }
    Button hostBtn, joinBtn;
    Scene scene;

    public MainMenu(String title, EventsHandler eventsHandler) {
        Label mainTitle = new Label(title);

        hostBtn = new Button("Host game");
        hostBtn.setOnAction(actionEvent -> eventsHandler.onHostButtonClicked());
        joinBtn = new Button("Join game");
        joinBtn.setOnAction(e-> eventsHandler.onJoinButtonClicked());

        VBox rootLayout = new VBox(10);
        rootLayout.getChildren().addAll(mainTitle, hostBtn, joinBtn);
        rootLayout.setAlignment(Pos.BOTTOM_CENTER);
        rootLayout.setPadding(new Insets(0, 0, 20, 0));

        this.scene = new Scene(rootLayout);
    }

    public Scene getScene() {
        return scene;
    }
}
