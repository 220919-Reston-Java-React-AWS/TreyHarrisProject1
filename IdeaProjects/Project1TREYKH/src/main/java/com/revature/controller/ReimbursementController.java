package com.revature.controller;

import com.revature.model.Reimbursement;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.exception.ReimbursementAlreadyCheckedException;
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
                    List<Reimbursement> reimbursement = reimbursementService.getAllReimbursements();
                    ctx.json(reimbursement);

                } else if (user.getRoleId() == 1) {
                    int studentId = user.getId();
                    List<Reimbursement> reimbursement = reimbursementService.getAllReimbursementsForStudent(studentId);
                    ctx.json(reimbursement);

                } else {
                    ctx.result("You aren't logged in Champ!");
                    ctx.status(401);
                }
            }
        });
        //add reimbursements
        app.post("/addReimbursements", (ctx) -> {
                    HttpSession httpSession = ctx.req.getSession();
                    User user = (User) httpSession.getAttribute("user");
                    Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
                    if (user != null) {
                        int UserId = user.getId();
                        reimbursement.setStudentId(UserId);
                        try {
                            ReimbursementService.addReimbursement(reimbursement);
                            ctx.result("Reimbursement submitted, a trainer will approve/deny shortly");
                        } catch (IllegalArgumentException e) {
                            ctx.status(400);
                            ctx.result(e.getMessage());
                        }

                    } else {
                        ctx.result("you are not logged in");
                        ctx.status(400);
                    }
                });

        //update the status
        app.patch("/updateReimbursement", (ctx) -> {
            HttpSession httpSession = ctx.req.getSession();
            User user = (User) httpSession.getAttribute("user");
            if (user != null){
                if (user.getRoleId() == 2) {
                    int trainerId = user.getId();
                    int reimbursementId = Integer.parseInt(ctx.pathParam("reimbursementId"));
                    Reimbursement a = ctx.bodyAsClass(Reimbursement.class);
                    int grade = a.getStatus();

                    try{
                        reimbursementService.gradeReimbursement(reimbursementId, grade, trainerId);
                        ctx.result("Reimbursement successfully approved");
                    }       catch (ReimbursementAlreadyCheckedException | IllegalArgumentException e){
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

