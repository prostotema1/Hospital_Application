package com.application;

import com.Hibernate.DBInteractoin.DBHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Pattern;


public class Second_Registration{

    private Pattern pattern = Pattern.compile("^[А-ЯA-Z][а-яa-z]+$");
    @FXML
    private Button NextButton;

    @FXML
    private DatePicker birthsday;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField middleName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button CancelButton;

    @FXML
    void initialize(){


    }

    @FXML
    void handleCloseButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        DBHolder.getDBInteraction().Clean_Table();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.hospital_application/First_Window_Registration.fxml"));
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


    @FXML
    void NextWindow(){
        if(!Check_First_Name() || !Check_Middle_Name() || !Check_Surname() || !Check_Phone_Number()){return;}
        try {
            DBHolder.getDBInteraction().signUpUser_second(firstName.getText(),lastName.getText(),
                    middleName.getText(),birthsday.getValue().toString(), phoneNumber.getText(), get_age());
            NextButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com.hospital_application/ThirdRegistration.fxml"));
            try {
                loader.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int get_age(){
        int year = birthsday.getValue().getYear();
        int month = birthsday.getValue().getMonthValue();
        int day = birthsday.getValue().getDayOfMonth();
        Date dt = new Date();
        int Age = dt.getYear() - year + 1900;
        if(dt.getMonth() + 1 == month){
            if(day > dt.getDate()){
                --Age;
            }
        }
        else if(month > dt.getMonth() + 1){
            --Age;
        }
        return Age;
    }

    private boolean Check_First_Name(){
        return pattern.matcher(firstName.getText()).find();
    }

    private boolean Check_Surname(){
        return pattern.matcher(lastName.getText()).find();
    }

    private boolean Check_Middle_Name(){
        return pattern.matcher(middleName.getText()).find();
    }

    private boolean Check_Phone_Number(){
        Pattern phone_pattern = Pattern.compile("^\\+?\\d{11}$");
        return phone_pattern.matcher(phoneNumber.getText()).find();
    }
}
