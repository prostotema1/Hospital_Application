package com.Hibernate.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "appointment", schema = "hospital", catalog = "")
public class MyAppointmentEntity {
    private int id;
    private int patientId;
    private int doctorId;
    private int diseaseId;
    private Date dateOfAppointment;
    private MyPatientEntity patientByPatientId;
    private MyDoctorsEntity doctorsByDoctorId;
    private MyDiseaseEntity diseaseByDiseaseId;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Patient_ID", nullable = false,insertable = false,updatable = false)
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "Doctor_ID", nullable = false,insertable = false,updatable = false)
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "Disease_ID", nullable = false,insertable = false,updatable = false)
    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Basic
    @Column(name = "Date_of_Appointment", nullable = false)
    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyAppointmentEntity that = (MyAppointmentEntity) o;

        if (id != that.id) return false;
        if (patientId != that.patientId) return false;
        if (doctorId != that.doctorId) return false;
        if (diseaseId != that.diseaseId) return false;
        if (dateOfAppointment != null ? !dateOfAppointment.equals(that.dateOfAppointment) : that.dateOfAppointment != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + patientId;
        result = 31 * result + doctorId;
        result = 31 * result + diseaseId;
        result = 31 * result + (dateOfAppointment != null ? dateOfAppointment.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Patient_ID", referencedColumnName = "Patient_ID", nullable = false)
    public MyPatientEntity getPatientByPatientId() {
        return patientByPatientId;
    }

    public void setPatientByPatientId(MyPatientEntity patientByPatientId) {
        this.patientByPatientId = patientByPatientId;
    }

    @ManyToOne
    @JoinColumn(name = "Doctor_ID", referencedColumnName = "Id", nullable = false)
    public MyDoctorsEntity getDoctorsByDoctorId() {
        return doctorsByDoctorId;
    }

    public void setDoctorsByDoctorId(MyDoctorsEntity doctorsByDoctorId) {
        this.doctorsByDoctorId = doctorsByDoctorId;
    }

    @ManyToOne
    @JoinColumn(name = "Disease_ID", referencedColumnName = "Id", nullable = false)
    public MyDiseaseEntity getDiseaseByDiseaseId() {
        return diseaseByDiseaseId;
    }

    public void setDiseaseByDiseaseId(MyDiseaseEntity diseaseByDiseaseId) {
        this.diseaseByDiseaseId = diseaseByDiseaseId;
    }
}
