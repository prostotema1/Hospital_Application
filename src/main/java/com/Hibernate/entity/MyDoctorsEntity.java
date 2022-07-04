package com.Hibernate.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "doctors", schema = "hospital", catalog = "")
public class MyDoctorsEntity {
    private int id;
    private Integer persId;
    private int specializationId;
    private Collection<MyAppointmentEntity> appointmentsById;
    private MyPersonEntity personByPersId;
    private MySpecializationEntity specializationBySpecializationId;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pers_Id", nullable = true,insertable = false,updatable = false)
    public Integer getPersId() {
        return persId;
    }

    public void setPersId(Integer persId) {
        this.persId = persId;
    }

    @Basic
    @Column(name = "Specialization_id", nullable = false,insertable = false,updatable = false)
    public int getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyDoctorsEntity that = (MyDoctorsEntity) o;

        if (id != that.id) return false;
        if (specializationId != that.specializationId) return false;
        if (persId != null ? !persId.equals(that.persId) : that.persId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (persId != null ? persId.hashCode() : 0);
        result = 31 * result + specializationId;
        return result;
    }

    @OneToMany(mappedBy = "doctorsByDoctorId")
    public Collection<MyAppointmentEntity> getAppointmentsById() {
        return appointmentsById;
    }

    public void setAppointmentsById(Collection<MyAppointmentEntity> appointmentsById) {
        this.appointmentsById = appointmentsById;
    }

    @ManyToOne
    @JoinColumn(name = "pers_Id", referencedColumnName = "pers_Id")
    public MyPersonEntity getPersonByPersId() {
        return personByPersId;
    }

    public void setPersonByPersId(MyPersonEntity personByPersId) {
        this.personByPersId = personByPersId;
    }

    @ManyToOne
    @JoinColumn(name = "Specialization_id", referencedColumnName = "Special_id", nullable = false)
    public MySpecializationEntity getSpecializationBySpecializationId() {
        return specializationBySpecializationId;
    }

    public void setSpecializationBySpecializationId(MySpecializationEntity specializationBySpecializationId) {
        this.specializationBySpecializationId = specializationBySpecializationId;
    }
}
