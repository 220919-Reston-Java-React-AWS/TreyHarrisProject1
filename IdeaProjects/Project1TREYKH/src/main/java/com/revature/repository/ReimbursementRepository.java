package com.revature.repository;

import java.sql.*;
import com.revature.model.Reimbursement;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementRepository {


        //get all reimbursements
    public List<Reimbursement> getAllReimbursements() throws SQLException {

        try (Connection connectionObject = ConnectionFactory.createConnection()) {

            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "SELECT * FROM reimbursements";

            PreparedStatement pstmt = connectionObject.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pstmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String reimbursementName = rs.getString("reimbursement_name");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int studentId = rs.getInt("student_id");
                int trainerId = rs.getInt("trainer_id");


                Reimbursement reimbursement = new Reimbursement(id, reimbursementName, status, studentId, trainerId);

                reimbursements.add(reimbursement);

            } System.out.println(reimbursements);
            return reimbursements;

        }

    }
    //get reimbursements for a certain student
    public List<Reimbursement> getAllReimbursementsForStudent(int studentId) throws SQLException {

        try (Connection connectionObject = ConnectionFactory.createConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();
            String sql = "SELECT * FROM reimbursements WHERE student_id = ?";
            PreparedStatement pstmt = connectionObject.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String reimbursementName = rs.getString("reimbursement_name");
                int status = rs.getInt("status");
                int sId = rs.getInt("student_id");
                int trainerId = rs.getInt("trainer_id");
                String description = rs.getString("description");

                Reimbursement reimbursement = new Reimbursement(id, reimbursementName, status, sId, trainerId, description);
                reimbursements.add(reimbursement);
            }return reimbursements;
        }

    }
    //approving reimbursement requests
    public boolean approveReimbursement(int reimbursementId, int status, int trainerId) throws SQLException {
        try (Connection connectionObj = ConnectionFactory.createConnection()) {
            String sql = "UPDATE reimbursements SET status = ?, trainer_id = ? WHERE id = ?";

            PreparedStatement pstmt = connectionObj.prepareStatement(sql);
            pstmt.setInt(1,status);
            pstmt.setInt(2, trainerId);
            pstmt.setInt(3, reimbursementId);

            int numberOfRecordsUpdated = pstmt.executeUpdate();
            return numberOfRecordsUpdated == 1;
        }
    }
    public Reimbursement getReimbursementById(int id) throws SQLException {
        try (Connection connectionObj = ConnectionFactory.createConnection()){
            String sql = "SELECT * FROM reimbursements WHERE id = ?";

            PreparedStatement pstmt = connectionObj.prepareStatement(sql);
            pstmt.setInt(1,id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int reimbursementId = rs.getInt("id");
                String reimbursementName = rs.getString("reimbursement_name");
                int status = rs.getInt("status");
                int studentId = rs.getInt("student_id");
                int trainerId = rs.getInt("trainer_id");
                String descrip = rs.getString("Description of Reimbursement");

                return new Reimbursement(reimbursementId, reimbursementName, status, studentId, trainerId, descrip);
            } else {
                return null;
            }
        }
    }


    public boolean gradeReimbursement(int reimbursementId, int grade, int trainerId) {
        return false;
    }
}
