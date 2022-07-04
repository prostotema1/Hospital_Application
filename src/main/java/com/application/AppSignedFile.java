package com.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class AppSignedFile {

    @FXML
    private ImageView ImageButtonHome;

    @FXML
    private Button Return_Button;

    @FXML
    void initialize(){

    }
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) Return_Button.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.hospital_application/hello-view.fxml"));
        try {
            loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
