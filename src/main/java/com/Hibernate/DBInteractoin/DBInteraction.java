package com.Hibernate.DBInteractoin;

import com.Hibernate.Factory.Hibernate_Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBInteraction {
    private static String user_passport_number;
    private static String user_passport_series;
    private static Connection connection;


    private static Connection getDbConnection()
            throws SQLException,ClassNotFoundException{

        String connectionString = "jdbc:mysql://localhost:3306/hospital";
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
        Session session = Hibernate_Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("UPDATE MyPersonEntity set registration = :reg" +
                ", snils = :sn  WHERE passportNumber = :x AND passportSeries = :y");
        q.setParameter("reg",Registration_us);
        q.setParameter("x",Passport_Number);
        q.setParameter("y",Passport_series);
        q.setParameter("sn",SNILS_us);
        int result = q.executeUpdate();
        tx.commit();
        session.close();
        user_passport_number = Passport_Number;
        user_passport_series = Passport_series;
    }

    public void signUpUser_second(String First_name, String Surname_us, String Middle_name_us,
                                  String Date, String Phone_us,
                                  Integer Age_us){
        Session session = Hibernate_Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("UPDATE MyPersonEntity set " +
                "passportName = :first, surname = :second, middleName = :third," +
                " birthDate = :fourth, phone = :fith, age = :six WHERE passportNumber = :seven " +
                        " AND passportSeries = :eight");
        q.setParameter("first",First_name);
        q.setParameter("second",Surname_us);
        q.setParameter("third",Middle_name_us);
        q.setParameter("fourth",java.sql.Date.valueOf(Date));
        q.setParameter("fith",Phone_us);
        q.setParameter("six",Age_us);
        q.setParameter("seven", user_passport_number);
        q.setParameter("eight", user_passport_series);
        q.executeUpdate();
        tx.commit();
        session.close();
    }
    public void signUpUser_third(String login, String password, String Gender, Boolean Is_Doctor)
            throws SQLException, ClassNotFoundException {
        Session session = Hibernate_Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("update MyPersonEntity " +
                "Set gender = :gen" +
                " WHERE passportNumber = :user_passport_Number AND passportSeries = :user_passport_series");
        q.setParameter("user_passport_Number", user_passport_number);
        q.setParameter("gen",Gender);
        q.setParameter("user_passport_series", user_passport_series);
        q.executeUpdate();
        tx.commit();
        session.close();
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
        Session session = Hibernate_Util.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.createQuery("delete MyPersonEntity WHERE passportNumber = " + "'" + user_passport_number + "'"+ " AND " +
                        "passportSeries = " + "'" + user_passport_series+"'").executeUpdate();
        tr.commit();
        session.close();
    }



    private String get_user_id(){
       return Hibernate_Util.getSessionFactory().openSession().createQuery("SELECT persId from MyPersonEntity " +
               "where  passportSeries = " + user_passport_series + " AND passportNumber = " + user_passport_number)
               .list().get(0).toString();

    }

    public boolean Check_Login(String text) throws SQLException, ClassNotFoundException {
        Query q = Hibernate_Util.getSessionFactory().openSession().createQuery("SELECT userLogin from MyUsersEntity " +
                "WHERE userLogin = : log");
        q.setParameter("log",text);
        var result = q.list();
        return result.size() == 0;

    }

    public boolean Check_User(String login, String password) throws SQLException, ClassNotFoundException {
        if(!Check_Login(login)){
            var q = Hibernate_Util.getSessionFactory().openSession().createQuery("select userLogin,userPassword from MyUsersEntity " +
                    "WHERE userLogin = : log AND userPassword = :pas");
            q.setParameter("log", login);
            q.setParameter("pas",password);
            var l = q.list();
            return l.size() != 0;
        }
        return false;
    }
}
