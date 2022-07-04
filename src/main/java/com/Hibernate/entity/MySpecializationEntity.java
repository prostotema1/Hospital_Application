package com.Hibernate.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "specialization", schema = "hospital", catalog = "")
public class MySpecializationEntity {
    private int specialId;
    private String nameSpec;
    private Collection<MyDiseaseEntity> diseasesBySpecialId;
    private Collection<MyDoctorsEntity> doctorsBySpecialId;

    @Id
    @Column(name = "Special_id", nullable = false)
    public int getSpecialId() {
        return specialId;
    }

    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    @Basic
    @Column(name = "Name_spec", nullable = false, length = 100)
    public String getNameSpec() {
        return nameSpec;
    }

    public void setNameSpec(String nameSpec) {
        this.nameSpec = nameSpec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MySpecializationEntity that = (MySpecializationEntity) o;

        if (specialId != that.specialId) return false;
        if (nameSpec != null ? !nameSpec.equals(that.nameSpec) : that.nameSpec != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = specialId;
        result = 31 * result + (nameSpec != null ? nameSpec.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "specializationBySpecId")
    public Collection<MyDiseaseEntity> getDiseasesBySpecialId() {
        return diseasesBySpecialId;
    }

    public void setDiseasesBySpecialId(Collection<MyDiseaseEntity> diseasesBySpecialId) {
        this.diseasesBySpecialId = diseasesBySpecialId;
    }

    @OneToMany(mappedBy = "specializationBySpecializationId")
    public Collection<MyDoctorsEntity> getDoctorsBySpecialId() {
        return doctorsBySpecialId;
    }

    public void setDoctorsBySpecialId(Collection<MyDoctorsEntity> doctorsBySpecialId) {
        this.doctorsBySpecialId = doctorsBySpecialId;
    }
}
