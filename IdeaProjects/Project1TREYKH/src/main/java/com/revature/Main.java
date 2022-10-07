package com.revature;

import com.revature.model.Reimbursement;
import com.revature.repository.ReimbursementRepository;
import io.javalin.Javalin;
import com.revature.controller.ReimbursementController;
import com.revature.controller.AuthController;
import com.revature.model.User;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create();

        ReimbursementRepository reimbursement = new ReimbursementRepository();

        AuthController ac = new AuthController();
        ac.mapEndpoints(app);

        ReimbursementController reimbursementController = new ReimbursementController();
        reimbursementController.mapEndpoints(app);


        app.start(8080);
    }


}
