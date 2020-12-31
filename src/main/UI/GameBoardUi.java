package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface GameBoardUi {
    public void setDiceDisplayText(String txt);
    public void onDiceButtonClicked(EventHandler<ActionEvent> e);
    public void onAddPlayerClicked(EventHandler<ActionEvent> e);
    public void onBackBtnClicked(EventHandler<ActionEvent> e);
}
