package com.example.project6;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;

    public class homebutton {

        @FXML
        private Button Aboutus;

        @FXML
        private Button Authors;

        @FXML
        private Button Exit;

        @FXML
        private Button History;

        @FXML
        private Button News;

        @FXML
        private Button Trendings;

        @FXML
        private Button home;

        @FXML
        private ImageView aboutusicon;



        @FXML
        private ImageView firsticon;

        @FXML
        private ImageView historyicon;

        @FXML
        private ImageView homeicon;

        @FXML
        private ImageView lasticon;

        @FXML
        private ImageView newsicon;

        @FXML
        private ImageView nexticon;

        @FXML
        private ImageView previousicon;

        @FXML
        private ImageView searchicon;

        @FXML
        private ImageView trendingsicon;

        @FXML
        private ScrollBar scrollbar;

        private  Stage stage;
        private Scene scene;
        private Parent root;

        public void switchSceneofHome(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
        public void switchSceneofListView(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("trendingScene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void switchSceneofBack(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        public void ExitTheProgram(ActionEvent event) throws Exception{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("You're about to logout!");
            alert.setContentText("Do you want to save before exiting?");

            if (alert.showAndWait().get() == ButtonType.OK){
                Platform.exit();
            }
        }





}
