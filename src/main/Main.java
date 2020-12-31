import GameModel.Game;
import UI.AlertBox;
import UI.GameBoardUi;
import UI.GameScene;
import UI.MainMenu;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
* TODO:
*   Game should contain a list of street names so UI can draw from
*   street owner representation?
*
* */

public class Main extends Application {
    Stage window;
    boolean connected;
    GameBoardUi gameUi;
    Game game;
    MainMenu mainMenu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setResizable(true);

//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        double screenHeight = screenSize.getHeight()*0.95;
//        double screenWidth = screenHeight;
        window.centerOnScreen();

//        mainMenu = new MainMenu("Monopoly", new MainMenu.EventsHandler() {
//            @Override
//            public void onHostButtonClicked() {
//                gameUi = new GameScene();
//                game = new GameModel.GameHost(gameUi, window, mainMenu.getScene());
//                window.setScene(((GameScene) gameUi).getScene());
//            }
//
//            @Override
//            public void onJoinButtonClicked() {
//                AlertBox.display("Join GameModel.Game", "Connect to: ", new ConnectionInfo());
//                if(connected){
//                    gameUi = new GameScene();
//                    window.setScene(((GameScene) gameUi).getScene());
//                }
//            }
//        });

        GameScene game = new GameScene();

        window.setScene(game.getScene());
        window.setTitle("Monopoly with the boys");
        window.show();
    }

    class ConnectionInfo implements AlertBox.Content {
        TextField portField, addrField;
        VBox layout;

        public ConnectionInfo() {
            this.portField = new TextField();
            this.portField.setPromptText("Enter Port Number");
            this.addrField = new TextField();
            this.addrField.setPromptText("Enter IP Address");
            this.layout = new VBox(10);
            layout.getChildren().addAll(addrField, portField);
        }

        @Override
        public Node getRootLayout(Button positiveBtn, Button negativeBtn, Stage window) {
            positiveBtn.setText("Connect");
            positiveBtn.setOnAction(e -> {
                connected = true;
                window.close();
            });

            negativeBtn.setText("Cancel");
            negativeBtn.setOnAction(e -> {
                connected = false;
                window.close();
            });
            return layout;
        }
    }
}

