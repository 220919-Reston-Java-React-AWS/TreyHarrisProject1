package com.revature;

import io.javalin.Javalin;
import com.revature.controller.ReimbursementController;
import com.revature.controller.AuthController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create();

        AuthController ac = new AuthController();
        ac.mapEndpoints(app);

        ReimbursementController assignmentController = new ReimbursementController();
        assignmentController.mapEndpoints(app);

        app.start(8080);
    }


}
