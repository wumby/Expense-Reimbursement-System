package com.revature.controller;

import com.revature.dto.ResponseReimbursementDTO;
import com.revature.service.ReimbursementService;
import com.revature.service.JWTService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.List;

public class ReimbursementController implements Controller {

    private JWTService jwtService;
    private ReimbursementService assignmentService;

    public ReimbursementController() {
        this.jwtService = JWTService.getInstance();
        this.assignmentService = new ReimbursementService();
    }


    private Handler getAllReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];

        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("Manager")) {
            throw new UnauthorizedResponse("You must be a manager to access this endpoint");
        }

        List<ResponseReimbursementDTO> reimbursements = this.assignmentService.getAllReimbursements();

        ctx.json(reimbursements);
    };



    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/reimbursements", getAllReimbursements);
        //
    }
}
