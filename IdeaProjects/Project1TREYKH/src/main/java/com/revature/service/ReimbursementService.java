package com.revature.service;

import com.revature.exception.ReimbursementAlreadyCheckedException;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.model.Reimbursement;
import com.revature.repository.ReimbursementRepository;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class ReimbursementService {
    private ReimbursementRepository reimbursementRepository = new ReimbursementRepository();


    public static void addReimbursement(Reimbursement reimbursement) {
    }

    public List<Reimbursement> getAllReimbursements() throws SQLException {
        ReimbursementRepository reimbursementRepository = new ReimbursementRepository();
        System.out.println("elbows");
        return reimbursementRepository.getAllReimbursements();
    }
    public List<Reimbursement> getAllReimbursementsForStudent(int studentId) throws SQLException {
        return reimbursementRepository.getAllReimbursementsForStudent(studentId);
    }
    public boolean gradeReimbursement(int reimbursementId, int status, int trainerId) throws SQLException, ReimbursementNotFoundException, ReimbursementAlreadyCheckedException {
       if (status < 0){
           throw new IllegalArgumentException("Reimbursement request must be 0 or higher");
       }
       Reimbursement reimbursement = reimbursementRepository.getReimbursementById(reimbursementId);
       if (reimbursement == null){
           throw new ReimbursementNotFoundException("Reimbursement with id" + reimbursementId + "was not found");
       }
       if (reimbursement.getStatus() != 0 && reimbursement.getTrainerId() != 0){
           throw new ReimbursementAlreadyCheckedException("Reimbursement with id" + reimbursementId + "has already been approved/denied");
       }
       return reimbursementRepository.gradeReimbursement(reimbursementId, status, trainerId);

    }


}
