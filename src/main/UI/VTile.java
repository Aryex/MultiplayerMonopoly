package UI;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class VTile implements Tile {
    private VBox rootLayout;
    private Type type;
    private String name;

    public VTile(String name, Type type){
        this.type = type;
        this.name = name;
        double width = SHORT_EDGE;
        double height = LONG_EDGE;

        Label streetTag = new Label();
        streetTag.setMaxSize(width, height*0.3);
        streetTag.getStyleClass().add("street-tag");

        Text streetName = new Text(name);


        if(type == Type.BOTTOM){
            rootLayout = new VBox(streetTag, streetName);
            rootLayout.setStyle(" -fx-alignment: top-center;");
        }else{
            rootLayout = new VBox(streetName,streetTag);
            rootLayout.setStyle(" -fx-alignment: bottom-center;");
        }
        rootLayout.getStyleClass().add("street");
        rootLayout.setMaxSize(width,height);
        rootLayout.setMinSize(width,height);
    }

    public VBox getRootLayout() {
        return rootLayout;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }
}
