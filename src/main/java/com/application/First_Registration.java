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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class First_Registration{


    @FXML
    private Button BackButton;

    @FXML
    private TextField City;

    @FXML
    private TextField Country;

    @FXML
    private Button NextButton;

    @FXML
    private TextField Passport_num;

    @FXML
    private TextField Passport_series;

    @FXML
    private TextField SNILS;

    @FXML
    private TextField street;

    @FXML
    public void initialize(){
        NextButton.setOnAction(actionEvent -> {
            if(!City.getText().equals("") && !Country.getText().equals("") &&
                    Check_Passport_Number() && Check_Passport_Series() &&
                    Check_SNILS() && !street.getText().equals("")){
                try {
                    DBHolder.getDBInteraction().signUpUser_first(Passport_num.getText(),Passport_series.getText(),"City: " + City.getText()
                                    .toString() + "\nCountry: " + Country.getText() + "\nStreet: " + street.getText(),
                            SNILS.getText());
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Close_and_load_next_window();
            }
        });
    }

    private void Close_and_load_next_window(){
        ((Stage)NextButton.getScene().getWindow()).close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.hospital_application/Second_Window.fxml"));
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
    private void Close_and_load_previous_window(){
        ((Stage)BackButton.getScene().getWindow()).close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.hospital_application/hello-view.fxml"));
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

    private boolean Check_Passport_Number(){
        String passport_number = Passport_num.getText();
        if(passport_number.length() == 6){
            try{Integer.parseInt(passport_number);
                return true;

            }
            catch (NumberFormatException e){
                return false;
            }

        }
        return false;


    }
    private boolean Check_Passport_Series(){
        String passport_series = Passport_series.getText();
        if(passport_series.length() == 4){
            try{
                Integer.parseInt(passport_series);
                return true;
            }
            catch (NumberFormatException e){
                return false;
            }
        }
        return false;
    }
    private boolean Check_Country(){
        return false;
    }
    private boolean Check_SNILS(){
        String sn = SNILS.getText().trim();
        if(sn.length() == 14){
            Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{3} \\d{2}");
            Matcher matcher = pattern.matcher(sn);
            return matcher.find();
        }
        return false;
    }
}
