package service;

import helper.Table;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Animation {
    public void buttonHighlight(Button button){
        DropShadow shadow =new DropShadow();
        shadow.setColor(Color.LIGHTBLUE);
        shadow.setRadius(10);
        if(button!=null){
            button.setOnMouseEntered(event -> button.setEffect(shadow));
            button.setOnMouseExited(event -> button.setEffect(null));
        }
    }

//    ScaleTransition transition = new ScaleTransition();
//    public void setBigger(Button button){
//        transition.setNode(button);
//        button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
//        @Override
//        public void handle(MouseEvent mouseEvent) {
//            transition.setDuration(Duration.millis(1));
//            transition.setByX(-0.05);
//            transition.setByY(-0.05);
//            transition.play();
//        }
//    });
//        button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
//         @Override
//            public void handle(MouseEvent mouseEvent) {
//                transition.setDuration(Duration.millis(1));
//                transition.setByX(0.05);
//                transition.setByY(0.05);
//                transition.play();
//            }
//        });
//}
}
