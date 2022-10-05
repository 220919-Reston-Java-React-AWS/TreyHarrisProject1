package com.revature.repository;

import java.sql.*;
import com.revature.model.Reimbursement;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementRepository {

    public List<Reimbursement> getAllReimbursements() throws SQLException {

        try (Connection connectionObject = ConnectionFactory.createConnection()) {

            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "SELECT * FROM reimbursements";

            Statement stmt = connectionObject.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String reimbursementName = rs.getString("reimbursement_name");
                int grade = rs.getInt("grade");
                int studentId = rs.getInt("student_id");
                int trainerId = rs.getInt("trainer_id");

                Reimbursement reimbursement = new Reimbursement(id, reimbursementName, grade, studentId, trainerId);

                reimbursements.add(reimbursement);
            }return reimbursements;
        }

    }
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
                int grade = rs.getInt("grade");
                int sId = rs.getInt("student_id");
                int trainerId = rs.getInt("grader_id");

                Reimbursement reimbursement = new Reimbursement(id, reimbursementName, grade, sId, trainerId);
                reimbursements.add(reimbursement);
            }return reimbursements;
        }

    }
    public boolean approveReimbursement(int reimbursementId, int grade, int trainerId) throws SQLException {
        try (Connection connectionObj = ConnectionFactory.createConnection()) {
            String sql = "UPDATE reimbursements SET grade = ?, trainer_id = ? WHERE id = ?";

            PreparedStatement pstmt = connectionObj.prepareStatement(sql);
            pstmt.setInt(1,grade);
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
                int grade = rs.getInt("grade");
                int studentId = rs.getInt("student_id");
                int trainerId = rs.getInt("trainer_id");

                return new Reimbursement(reimbursementId, reimbursementName, grade, studentId, trainerId);
            } else {
                return null;
            }
        }
    }


    public boolean gradeReimbursement(int reimbursementId, int grade, int trainerId) {
        return false;
    }
}
