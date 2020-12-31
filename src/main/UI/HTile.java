package UI;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HTile implements Tile {
    private HBox rootLayout;
    private String name;
    private Type type;

    public HTile(String name, Type type) {
        this.type = type;
        this.name = name;

        Label label = new Label();
        label.setMinSize(LONG_EDGE*0.2, SHORT_EDGE);
        label.setStyle("-fx-background-color:#0091ea;");

        Text streetName = new Text(name);

        rootLayout = new HBox();
        if(type == Type.LEFT){
            rootLayout.getChildren().addAll(streetName, label);
            rootLayout.setAlignment(Pos.TOP_RIGHT);
        }else{
            rootLayout.getChildren().addAll(label, streetName);
            rootLayout.setAlignment(Pos.TOP_LEFT);
        }
        rootLayout.getStyleClass().add("street");
    }

    @Override
    public Node getRootLayout() {
        return rootLayout;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return type;
    }
}
