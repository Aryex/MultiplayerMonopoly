package UI;

import javafx.scene.Node;

public interface Tile {
    enum Type {TOP, BOTTOM,LEFT,RIGHT,CORNER}
    int LONG_EDGE = 110, SHORT_EDGE = 90;
    Node getRootLayout();
    String getName();
    Type getType();
}
