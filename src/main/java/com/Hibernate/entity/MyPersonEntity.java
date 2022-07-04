package com.Hibernate.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "person", schema = "hospital", catalog = "")
public class MyPersonEntity {
    private int persId;
    private String passportNumber;
    private String passportSeries;
    private String passportName;
    private String surname;
    private String middleName;
    private Date birthDate;
    private String phone;
    private String registration;
    private String snils;
    private Integer age;
    private String gender;
    private Collection<MyDoctorsEntity> doctorsByPersId;
    private Collection<MyPatientEntity> patientsByPersId;
    private Collection<MyUsersEntity> usersByPersId;

    @Id
    @Column(name = "pers_Id", nullable = false)
    public int getPersId() {
        return persId;
    }

    public void setPersId(int persId) {
        this.persId = persId;
    }

    @Basic
    @Column(name = "Passport_Number", nullable = true, length = 50)
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Basic
    @Column(name = "Passport_series", nullable = true, length = 50)
    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    @Basic
    @Column(name = "Passport_name", nullable = true, length = 50)
    public String getPassportName() {
        return passportName;
    }

    public void setPassportName(String passportName) {
        this.passportName = passportName;
    }

    @Basic
    @Column(name = "Surname", nullable = true, length = 50)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "Middle_name", nullable = true, length = 50)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "Birth_date", nullable = true)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "Phone", nullable = true, length = 50)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "Registration", nullable = true, length = 500)
    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Basic
    @Column(name = "SNILS", nullable = true, length = 15)
    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    @Basic
    @Column(name = "Age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "Gender", nullable = true, length = 50)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPersonEntity that = (MyPersonEntity) o;

        if (persId != that.persId) return false;
        if (passportNumber != null ? !passportNumber.equals(that.passportNumber) : that.passportNumber != null)
            return false;
        if (passportSeries != null ? !passportSeries.equals(that.passportSeries) : that.passportSeries != null)
            return false;
        if (passportName != null ? !passportName.equals(that.passportName) : that.passportName != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (registration != null ? !registration.equals(that.registration) : that.registration != null) return false;
        if (snils != null ? !snils.equals(that.snils) : that.snils != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = persId;
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        result = 31 * result + (passportSeries != null ? passportSeries.hashCode() : 0);
        result = 31 * result + (passportName != null ? passportName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (registration != null ? registration.hashCode() : 0);
        result = 31 * result + (snils != null ? snils.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "personByPersId")
    public Collection<MyDoctorsEntity> getDoctorsByPersId() {
        return doctorsByPersId;
    }

    public void setDoctorsByPersId(Collection<MyDoctorsEntity> doctorsByPersId) {
        this.doctorsByPersId = doctorsByPersId;
    }

    @OneToMany(mappedBy = "personByPersId")
    public Collection<MyPatientEntity> getPatientsByPersId() {
        return patientsByPersId;
    }

    public void setPatientsByPersId(Collection<MyPatientEntity> patientsByPersId) {
        this.patientsByPersId = patientsByPersId;
    }

    @OneToMany(mappedBy = "personByPersonId")
    public Collection<MyUsersEntity> getUsersByPersId() {
        return usersByPersId;
    }

    public void setUsersByPersId(Collection<MyUsersEntity> usersByPersId) {
        this.usersByPersId = usersByPersId;
    }
}
