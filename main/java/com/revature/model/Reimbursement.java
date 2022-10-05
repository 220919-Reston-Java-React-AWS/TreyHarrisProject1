package com.revature.model;

import java.util.Objects;

public class Reimbursement {

    private int id;
    private String reimbursementName;
    private int grade;
    private int studentId;
    private int trainerId;

    public Reimbursement(){

    }


    public Reimbursement(int id, String reimbursementName, int grade, int studentId, int trainerId) {
        this.id = id;
        this.reimbursementName = reimbursementName;
        this.grade = grade;
        this.studentId = studentId;
        this.trainerId = trainerId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getReimbursementName() {
        return reimbursementName;
    }

    public void setReimbursementName(String reimbursementName) {
        this.reimbursementName = reimbursementName;
    }
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }



}
