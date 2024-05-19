package service;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class Animation {

    ScaleTransition transition = new ScaleTransition();
    public void setBigger(Button button){
        transition.setNode(button);
        button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            transition.setDuration(Duration.millis(1));
            transition.setByX(-0.05);
            transition.setByY(-0.05);
            transition.play();
        }
    });
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
         @Override
            public void handle(MouseEvent mouseEvent) {
                transition.setDuration(Duration.millis(1));
                transition.setByX(0.05);
                transition.setByY(0.05);
                transition.play();
            }
        });
}}
