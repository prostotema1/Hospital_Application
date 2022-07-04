package com.Hibernate.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "hospital", catalog = "")
public class MyUsersEntity {
    private int userId;
    private String userLogin;
    private String userPassword;
    private int personId;
    private String isDoctor;
    private MyPersonEntity personByPersonId;

    @Id
    @Column(name = "User_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "User_Login", nullable = false, length = 50)
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Basic
    @Column(name = "User_Password", nullable = false, length = 50)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "Person_id", nullable = false,insertable = false,updatable = false)
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "IS_Doctor", nullable = false, length = 5)
    public String getIsDoctor() {
        return isDoctor;
    }

    public void setIsDoctor(String isDoctor) {
        this.isDoctor = isDoctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyUsersEntity that = (MyUsersEntity) o;

        if (userId != that.userId) return false;
        if (personId != that.personId) return false;
        if (userLogin != null ? !userLogin.equals(that.userLogin) : that.userLogin != null) return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;
        if (isDoctor != null ? !isDoctor.equals(that.isDoctor) : that.isDoctor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + personId;
        result = 31 * result + (isDoctor != null ? isDoctor.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Person_id", referencedColumnName = "pers_Id", nullable = false)
    public MyPersonEntity getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(MyPersonEntity personByPersonId) {
        this.personByPersonId = personByPersonId;
    }
}
