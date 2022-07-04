package com.Hibernate.Factory;

import com.Hibernate.entity.MyPersonEntity;
import org.hibernate.Session;

public class Mapping {
    public static void main(String[] args) {
        Session session = Hibernate_Util.getSessionFactory().openSession();
        var l = session.createQuery("from MyPersonEntity ", MyPersonEntity.class).list();
        for(var s : l){
            System.out.println(s.getPassportName());
        }

    }

}
