package com.Hibernate.DBInteractoin;

public class DBHolder {
    private static DBInteraction dbInteraction = new DBInteraction();

    public static DBInteraction getDBInteraction(){return dbInteraction; }
}
