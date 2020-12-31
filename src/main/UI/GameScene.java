package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GameScene implements GameBoardUi {

    Text diceDisplayText, timeText;
    Button backBtn, diceBtn, addPlayerBtn;
    Scene scene;
    String msg = "";
    private final DateTimeFormatter dtf;

    List<Tile> tileList;

    public GameScene() {
        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String roll = "0";
        diceDisplayText = new Text(roll);
        timeText = new Text(dtf.format(LocalDateTime.now()));
        diceDisplayText.setFont(Font.font("verdana", FontWeight.BOLD, 60));
        diceBtn = new Button("Dice");
        addPlayerBtn = new Button("Add Player");
        VBox layout = new VBox(10);
//        BackButton Setup
        backBtn = new Button("Back");
//        layout

        ImageView centerImg = new ImageView(new Image("res/me-and-the-boys.jpg"));
        centerImg.setFitHeight(500);
        centerImg.setFitWidth(500);
        StackPane center = new StackPane(centerImg);
        center.setAlignment(Pos.BOTTOM_RIGHT);

        tileList = new ArrayList<Tile>();
        tileList.add(new CornerTile("GO", Tile.Type.CORNER));
        for (int i = 0; i < 9; i++) {
            tileList.add(new VTile("Hawser Ave", Tile.Type.BOTTOM));
        }
        tileList.add(new CornerTile("Jail", Tile.Type.CORNER));

        for (int i = 0; i < 9; i++) {
            tileList.add(new HTile("Hawser Ave", Tile.Type.LEFT));
        }

        tileList.add(new CornerTile("Free Parking", Tile.Type.CORNER));
        for (int i = 0; i < 9; i++) {
            tileList.add(new VTile("Hawser Ave", Tile.Type.TOP));
        }
        tileList.add(new CornerTile("Go to Jail", Tile.Type.CORNER));

        for (int i = 0; i < 9; i++) {
            tileList.add(new HTile("Hawser Ave", Tile.Type.RIGHT));
        }

        HBox bottom = new HBox();
        HBox top = new HBox();
        VBox left = new VBox();
        VBox right = new VBox();

        for(int i = 1; i < 10; i++){
            Tile tile = tileList.get(i);
            bottom.getChildren().add(tile.getRootLayout());
        }

        for(int i = 11; i < 20; i++){
            Tile tile = tileList.get(i);
            left.getChildren().add(tile.getRootLayout());
        }

        for(int i = 21; i < 30; i++){
            Tile tile = tileList.get(i);
            top.getChildren().add(tile.getRootLayout());
        }

        for(int i = 31; i < 40; i++){
            Tile tile = tileList.get(i);
            right.getChildren().add(tile.getRootLayout());
        }

        GridPane gridPane = new GridPane();
        gridPane.add(left, 0,1);
        gridPane.add(tileList.get(10).getRootLayout(), 0,2);
        gridPane.add(tileList.get(0).getRootLayout(), 2,2);
        gridPane.add(bottom,1,2);
        gridPane.add(center,1,1);
        gridPane.add(top,1,0);
        gridPane.add(right,2,1);
        gridPane.add(tileList.get(20).getRootLayout(), 0,0);
        gridPane.add(tileList.get(30).getRootLayout(), 2,0);
        gridPane.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(gridPane);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
    }

    @Override
    public void onDiceButtonClicked(EventHandler<ActionEvent> e) {
        diceBtn.setOnAction(e);
    }

    @Override
    public void onAddPlayerClicked(EventHandler<ActionEvent> e) {
        addPlayerBtn.setOnAction(e);
    }

    @Override
    public void onBackBtnClicked(EventHandler<ActionEvent> e) {
        backBtn.setOnAction(e);
    }

    @Override
    public void setDiceDisplayText(String txt) {
        diceDisplayText.setText(txt);
        timeText.setText(dtf.format(LocalDateTime.now()));
    }

    public Scene getScene() {
        return scene;
    }

    public static class Client {
        private static Socket clientSocket;
        private static PrintWriter out;
        private static BufferedReader in;

        public static void startConnection(String ip, int port) throws IOException {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }

        public static void sendMessage(String msg) throws IOException {
            out.println(msg);
        }

        public static String getMessage() throws IOException {
            return in.readLine();
        }

        public static void stopConnection() throws IOException {
            in.close();
            out.close();
            clientSocket.close();
        }
    }
}
