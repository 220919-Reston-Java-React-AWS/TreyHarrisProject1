package com.revature.controller;

import com.revature.model.Reimbursement;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.exception.ReimbursementAlreadyGradedException;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import javax.servlet.http.HttpSession;
import java.util.List;
import io.javalin.Javalin;

public class ReimbursementController {
    private ReimbursementService reimbursementService = new ReimbursementService();
    public void mapEndpoints(Javalin app){

        app.get("/reimbursements", (ctx) -> {
            HttpSession httpSession = ctx.req.getSession();
            User user = (User) httpSession.getAttribute("user");

            if (user != null) {
                if (user.getRoleId() == 2) {
                    List<Reimbursement> reimbursements = reimbursementService.getAllReimbursements();
                    ctx.json(reimbursements);

                } else if (user.getRoleId() == 1) {
                    int studentId = user.getId();
                    List<Reimbursement> reimbursements = reimbursementService.getAllReimbursementsForStudent(studentId);
                    ctx.json(reimbursements);

                } else {
                    ctx.result("You aren't logged in Champ!");
                    ctx.status(401);
                }
            }
        });
        app.patch("/reimbursement/{reimbursementId}", (ctx) -> {
            HttpSession httpSession = ctx.req.getSession();
            User user = (User) httpSession.getAttribute("user");
            if (user != null){
                if (user.getRoleId() == 2) {
                    int trainerId = user.getId();
                    int reimbursementId = Integer.parseInt(ctx.pathParam("reimbursementId"));
                    Reimbursement a = ctx.bodyAsClass(Reimbursement.class);
                    int grade = a.getGrade();

                    try{
                        reimbursementService.gradeReimbursement(reimbursementId, grade, trainerId);
                        ctx.result("Reimbursement successfully approved");
                    }       catch (ReimbursementAlreadyGradedException | IllegalArgumentException e){
                        ctx.result(e.getMessage());
                        ctx.status(400);
                    }   catch (ReimbursementNotFoundException e){
                        ctx.result(e.getMessage());
                        ctx.status(404);
                    }
                } else {
                    ctx.result("You are logged in, but you aren't a trainer");
                    ctx.status(404);
                }
                } else {
                ctx.result("You are not logged in Champ!");
            }

        });
    }
    }

