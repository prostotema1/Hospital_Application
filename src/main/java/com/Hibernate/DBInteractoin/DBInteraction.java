package com.Hibernate.DBInteractoin;

import com.Hibernate.Factory.Hibernate_Util;
import com.Hibernate.entity.MyPersonEntity;

import java.sql.*;

public class DBInteraction {
    private static String user_passpor_number;
    private static String user_passport_series;
    private static Connection connection;


    private static Connection getDbConnection()
            throws SQLException,ClassNotFoundException{

        String connectionString = "jdbc:mysql://localhost:3306/hpspital";
        connection = DriverManager.getConnection(connectionString,"root","Nature");
        return connection;
    }

    public void signUpUser_first(String Passport_Number,String Passport_series,String Registration_us,
                                 String SNILS_us ) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO hospital.person(Passport_Number,Passport_series) VALUE(?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1,Passport_Number);
        preparedStatement.setString(2,Passport_series);
        preparedStatement.executeUpdate();
        int query = Hibernate_Util.getSessionFactory().openSession()
                .createQuery("UPDATE MyPersonEntity set registration = " + Registration_us + "," +
                        "snils = " + SNILS_us + " WHERE passportNumber = " +
                        Passport_Number + " AND passportSeries = " + Passport_series).executeUpdate();
        user_passpor_number = Passport_Number;
        user_passport_series = Passport_series;
    }

    public void signUpUser_second(String First_name, String Surname_us, String Middle_name_us,
                                  String Date, String Phone_us,
                                  Integer Age_us){
        int query = Hibernate_Util.getSessionFactory().openSession().createQuery("UPDATE MyPersonEntity set " +
                "passportName = " + First_name + ", surname = " + Surname_us +
                ", middleName = " + Middle_name_us + ", birthDate = " + Date + ", phone = " +
                Phone_us + ", age = " + Age_us + " WHERE passportNumber = "
         + user_passpor_number + " AND passportSeries = " + user_passport_series).executeUpdate();



    }
    public void signUpUser_third(String login, String password, String Gender, Boolean Is_Doctor)
            throws SQLException, ClassNotFoundException {
        Hibernate_Util.getSessionFactory().openSession().createQuery("update MyPersonEntity " +
                "Set gender = " + Gender + " WHERE passportNumber = "
                + user_passpor_number + " AND passportSeries = " + user_passport_series).executeUpdate();
        String insert_user = "INSERT hospital.users(" + "User_Login,User_Password,Person_id,IS_Doctor)" +
                " VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert_user);
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,password);
        preparedStatement.setString(3,get_user_id());
        preparedStatement.setString(4, String.valueOf(Is_Doctor));
        preparedStatement.executeUpdate();
    }

    public void Clean_Table() throws SQLException, ClassNotFoundException {
        Hibernate_Util.getSessionFactory().openSession()
                .createQuery("delete MyPersonEntity WHERE passportNumber = " + user_passpor_number + " AND " +
                        "passportSeries = " + user_passport_series, MyPersonEntity.class).executeUpdate();



    }



    private String get_user_id(){
       return Hibernate_Util.getSessionFactory().openSession().createQuery("SELECT persId from MyPersonEntity " +
               "where  passportSeries = " + user_passport_series + " AND passportNumber = " + user_passpor_number)
               .list().get(1).toString();

    }

    public boolean Check_Login(String text) throws SQLException, ClassNotFoundException {
        int result = Hibernate_Util.getSessionFactory().openSession().createQuery("SELECT userLogin from MyUsersEntity " +
                "WHERE userLogin = " + "'" + text + "'").executeUpdate();
        return result == 0;

    }

    public boolean Check_User(String login, String password) throws SQLException, ClassNotFoundException {
        if(!Check_Login(login)){
            var list = Hibernate_Util.getSessionFactory().openSession().createQuery("select userPassword from MyUsersEntity " +
                    "WHERE userLogin = " + login).list();
            return list.size() != 0;
        }
        return false;
    }
}
