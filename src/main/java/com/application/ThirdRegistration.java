package com.application;


import com.Hibernate.DBInteractoin.DBHolder;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ThirdRegistration {


    @FXML
    private Button BackButton;

    @FXML
    private ChoiceBox Gender;

    @FXML
    private CheckBox Is_Doctor;

    @FXML
    private TextField Login1;

    @FXML
    private TextField Login2;

    @FXML
    private Button NextButton;

    @FXML
    private PasswordField Password1;

    @FXML
    private PasswordField Password2;

    @FXML
    public void initialize(){
        Gender.setItems(FXCollections.observableArrayList("Male", "Female", "Other"));
        Gender.setValue("Male");
    }

    @FXML
    public void Next_Window()
            throws SQLException, ClassNotFoundException {
        if(!Logins_and_Password_equality()){
            return;
        }
        try {
            DBHolder.getDBInteraction().signUpUser_third(Login1.getText(),Password1.getText(),Gender.getValue().toString(),Is_Doctor.isSelected());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ((Stage)NextButton.getScene().getWindow()).close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.hospital_application/AppSignedFile.fxml"));
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

    @FXML
    public void Previous_Window(){
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.hospital_application/Second_Window.fxml"));
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

    public boolean Logins_and_Password_equality() throws SQLException, ClassNotFoundException {
        return Login1.getText().equals(Login2.getText()) && Password1.getText().equals(Password2.getText()) &&
                DBHolder.getDBInteraction().Check_Login(Login1.getText());

    }


}
