package com.Hibernate.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "disease", schema = "hospital", catalog = "")
public class MyDiseaseEntity {
    private int id;
    private int specId;
    private String diseaseNm;
    private Collection<MyAppointmentEntity> appointmentsById;
    private MySpecializationEntity specializationBySpecId;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Spec_id", nullable = false,insertable = false,updatable = false)
    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    @Basic
    @Column(name = "Disease_nm", nullable = false, length = 500)
    public String getDiseaseNm() {
        return diseaseNm;
    }

    public void setDiseaseNm(String diseaseNm) {
        this.diseaseNm = diseaseNm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyDiseaseEntity that = (MyDiseaseEntity) o;

        if (id != that.id) return false;
        if (specId != that.specId) return false;
        if (diseaseNm != null ? !diseaseNm.equals(that.diseaseNm) : that.diseaseNm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + specId;
        result = 31 * result + (diseaseNm != null ? diseaseNm.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "diseaseByDiseaseId")
    public Collection<MyAppointmentEntity> getAppointmentsById() {
        return appointmentsById;
    }

    public void setAppointmentsById(Collection<MyAppointmentEntity> appointmentsById) {
        this.appointmentsById = appointmentsById;
    }

    @ManyToOne
    @JoinColumn(name = "Spec_id", referencedColumnName = "Special_id", nullable = false)
    public MySpecializationEntity getSpecializationBySpecId() {
        return specializationBySpecId;
    }

    public void setSpecializationBySpecId(MySpecializationEntity specializationBySpecId) {
        this.specializationBySpecId = specializationBySpecId;
    }
}
