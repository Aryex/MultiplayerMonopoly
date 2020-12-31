package UI;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CornerTile implements Tile {
    private VBox rootLayout;
    private Type ty;
    private String name;

    public CornerTile(String name, Type ty) {
        this.name = name;
        this.ty = ty;
        double width = LONG_EDGE;
        Text streetName = new Text(name);
        rootLayout = new VBox(streetName);
        rootLayout.setMaxSize(width,width);
        rootLayout.setMinSize(width,width);
        rootLayout.getStyleClass().add("corner");

    }

    @Override
    public VBox getRootLayout() {
        return rootLayout;
    }

    @Override
    public Type getType() {
        return ty;
    }

    @Override
    public String getName() {
        return name;
    }
}
