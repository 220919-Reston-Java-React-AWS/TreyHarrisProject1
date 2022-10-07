package com.revature.model;

import java.util.Objects;

public class Reimbursement {

    private int id;
    private String reimbursementName;
    private int status;
    private int studentId;
    private int trainerId;
    private int amount;
    private String description;

    public Reimbursement(){

    }



    public Reimbursement(int id, String reimbursementName, int status, int studentId, int trainerId, int amount, String description) {
        this.id = id;
        this.reimbursementName = reimbursementName;
        this.status = status;
        this.studentId = studentId;
        this.trainerId = trainerId;
        this.amount = amount;
        this.description = description;
    }

    public Reimbursement(int id, String reimbursementName, int status, int studentId, int trainerId, String description) {
    }

    public Reimbursement(int id, String reimbursementName, int status, int studentId, int trainerId) {
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
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", reimbursementName='" + reimbursementName + '\'' +
                ", status=" + status +
                ", studentId=" + studentId +
                ", trainerId=" + trainerId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && status == that.status && studentId == that.studentId && trainerId == that.trainerId && amount == that.amount && Objects.equals(reimbursementName, that.reimbursementName) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reimbursementName, status, studentId, trainerId, amount, description);
    }
}
