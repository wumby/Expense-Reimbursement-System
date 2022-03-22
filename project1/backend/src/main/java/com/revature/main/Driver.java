package com.revature.main;

import com.revature.controller.ReimbursementController;
import com.revature.controller.AuthenticationController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import com.revature.utility.ConnectionUtility;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {

    public static void main(String[] args) {
        Javalin app = Javalin.create((config) -> {
            config.enableCorsForAllOrigins();
            //config.enableCorsForOrigin("http://localhost:8081");
        });

        map(app, new AuthenticationController(), new ExceptionController(), new ReimbursementController());

        app.start(8080);
    }

    public static void map(Javalin app, Controller... controllers) {
        for (Controller c : controllers) {
            c.mapEndpoints(app);
        }
    }
}
