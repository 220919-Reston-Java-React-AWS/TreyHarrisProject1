package com.revature.controller;

import com.revature.exception.MissingRegistrationInfoException;
import com.revature.exception.UserNameAlreadyTakenException;
import com.revature.model.User;
import com.revature.service.AuthService;
import io.javalin.Javalin;
import com.revature.exception.InvalidLoginException;
import javax.servlet.http.HttpSession;

public class AuthController {

    private AuthService authservice = new AuthService();

    public void mapEndpoints(Javalin app){
        app.post("/login", (ctx)->{
            User credentials = ctx.bodyAsClass(User.class);
            HttpSession session1 = ctx.req.getSession();
            try {
                if (session1 != null)
                {
                    User user = authservice.login(credentials.getUsername(), credentials.getPassword());
                    HttpSession session = ctx.req.getSession();
                    session1.setAttribute("student",user);
                    ctx.result("Log in Successful");
                } else {
                    ctx.result("You are already logged in");
                }


            } catch (InvalidLoginException e){
                ctx.status(400);
                ctx.result(e.getMessage());

            }
        });
        app.post("/register", (ctx) -> {
            User user = ctx.bodyAsClass(User.class);
            HttpSession session1 = ctx.req.getSession();
            try {
                    authservice.register(user);
                    ctx.result("You successfully registered");

            } catch (UserNameAlreadyTakenException e) {
                ctx.status(400);
                ctx.result(e.getMessage());
            } catch (MissingRegistrationInfoException e) {
                ctx.status(400);
                ctx.result(e.getMessage());
            }
                });
        app.post("/logout", (ctx)-> {
            ctx.req.getSession().invalidate();
            ctx.result("You Have Successfully Logged out");
        });


    }

}
