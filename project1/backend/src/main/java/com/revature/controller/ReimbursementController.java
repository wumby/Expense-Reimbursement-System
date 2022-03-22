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
    private ReimbursementService reimbursementService;

    public ReimbursementController() {
        this.jwtService = JWTService.getInstance();
        this.reimbursementService = new ReimbursementService();
    }


    private Handler getAllReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        System.out.println("Get request made");
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("Manager")) {
            throw new UnauthorizedResponse("You must be a manager to access this endpoint");
        }

        List<ResponseReimbursementDTO> reimbursements = this.reimbursementService.getAllReimbursements();

        ctx.json(reimbursements);
    };

    private Handler getReimbursementsByEmployee = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("Employee")) {
            throw new UnauthorizedResponse("You must be an employee to access this endpoint");
        }

        String userId = ctx.pathParam("employeeId");
        int id = Integer.parseInt(userId);
        if (!token.getBody().get("user_id").equals(id)) {
            throw new UnauthorizedResponse("You cannot obtain reimbursements that don't belong to yourself");
        }

        List<ResponseReimbursementDTO> dtos = this.reimbursementService.getReimbursementsByEmployee(id);
        ctx.json(dtos);
    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/reimbursements", getAllReimbursements);
        app.get("/users/{employeeId}/reimbursements", getReimbursementsByEmployee);
        //
    }
}
