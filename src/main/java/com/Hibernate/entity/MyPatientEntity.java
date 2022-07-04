package com.Hibernate.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "patient", schema = "hospital", catalog = "")
public class MyPatientEntity {
    private int patientId;
    private Integer persId;
    private Collection<MyAppointmentEntity> appointmentsByPatientId;
    private MyPersonEntity personByPersId;

    @Id
    @Column(name = "Patient_ID", nullable = false)
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "pers_id", nullable = true,insertable = false,updatable = false)
    public Integer getPersId() {
        return persId;
    }

    public void setPersId(Integer persId) {
        this.persId = persId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPatientEntity that = (MyPatientEntity) o;

        if (patientId != that.patientId) return false;
        if (persId != null ? !persId.equals(that.persId) : that.persId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = patientId;
        result = 31 * result + (persId != null ? persId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "patientByPatientId")
    public Collection<MyAppointmentEntity> getAppointmentsByPatientId() {
        return appointmentsByPatientId;
    }

    public void setAppointmentsByPatientId(Collection<MyAppointmentEntity> appointmentsByPatientId) {
        this.appointmentsByPatientId = appointmentsByPatientId;
    }

    @ManyToOne
    @JoinColumn(name = "pers_id", referencedColumnName = "pers_Id")
    public MyPersonEntity getPersonByPersId() {
        return personByPersId;
    }

    public void setPersonByPersId(MyPersonEntity personByPersId) {
        this.personByPersId = personByPersId;
    }
}
