package com.application;


import com.Hibernate.DBInteractoin.DBHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Greeting_Window_Controller {

    @FXML
    private TextField LoginField;

    @FXML
    private Button LoginSignButton;

    @FXML
    private TextField PasswordField;

    @FXML
    private Button SignButton;

    @FXML
    void initialize(){
        LoginSignButton.setOnAction(actionEvent -> {
            ((Stage)LoginSignButton.getScene().getWindow()).close();
            Previous_or_next_page("/com.hospital_application/First_Window_Registration.fxml");
        });

        SignButton.setOnAction(actionEvent -> {
            String login = LoginField.getText().trim();
            String password = PasswordField.getText().trim();
            if(!login.equals("") && !password.equals("")){
            try {
                if(DBHolder.getDBInteraction().Check_User(login,password)){
                    ((Stage)SignButton.getScene().getWindow()).close();
                    Previous_or_next_page("/com.hospital_application/AppSignedFile.fxml");
                }
                else {
                    System.out.println("Error");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } }
        }
        );


    }

    private void Previous_or_next_page(String address){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(address));
        try {
            loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }
}
