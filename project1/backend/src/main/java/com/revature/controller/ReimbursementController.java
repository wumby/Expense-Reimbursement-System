package com.revature.controller;

import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.service.ReimbursementService;
import com.revature.service.JWTService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.UploadedFile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.tika.Tika;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class ReimbursementController implements Controller {

    private JWTService jwtService;
    private ReimbursementService reimbursementService;

    public ReimbursementController() {
        this.jwtService = JWTService.getInstance();
        this.reimbursementService = new ReimbursementService();
    }


    private Handler getAllReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("Manager")) {
            throw new UnauthorizedResponse("You must be a manager to access this endpoint");
        }
            List<ResponseReimbursementDTO> reimbursements = this.reimbursementService.getAllReimbursements();
            ctx.json(reimbursements);
    };

    private Handler getAllDeniedReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("Manager")) {
            throw new UnauthorizedResponse("You must be a manager to access this endpoint");
        }
        List<ResponseReimbursementDTO> reimbursements = this.reimbursementService.getAllDeniedReimbursements();
        ctx.json(reimbursements);
    };
    private Handler getAllApprovedReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("Manager")) {
            throw new UnauthorizedResponse("You must be a manager to access this endpoint");
        }
        List<ResponseReimbursementDTO> reimbursements = this.reimbursementService.getAllApprovedReimbursements();
        ctx.json(reimbursements);
    };
    private Handler getAllPendingReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("Manager")) {
            throw new UnauthorizedResponse("You must be a manager to access this endpoint");
        }
        List<ResponseReimbursementDTO> reimbursements = this.reimbursementService.getAllPendingReimbursements();
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

    private Handler getPendingReimbursementsByEmployee = (ctx) -> {
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

        List<ResponseReimbursementDTO> dtos = this.reimbursementService.getPendingReimbursementsByEmployee(id);
        ctx.json(dtos);
    };

    private Handler getResolvedReimbursementsByEmployee = (ctx) -> {
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

        List<ResponseReimbursementDTO> dtos = this.reimbursementService.getResolvedReimbursementsByEmployee(id);
        ctx.json(dtos);
    };


    private Handler addReimbursement = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];

        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("Employee")) {
            throw new UnauthorizedResponse("You must be an employee to access this endpoint");
        }

        String userId = ctx.pathParam("employeeId");
        int id = Integer.parseInt(userId);
        if (!token.getBody().get("user_id").equals(id)) {
            throw new UnauthorizedResponse("You cannot add a reimbursement for an employee other than yourself");
        }

        String ra = ctx.formParam("amount");
        int reimbursementAmount = Integer.parseInt(ra);
        String reimbursementSubmitted = ctx.formParam("submitted");
        String reimbursementDescription = ctx.formParam("description");
        UploadedFile file = ctx.uploadedFile("receipt");
        InputStream is = file.getContent();
        String ti = ctx.formParam("type");
        int reimbursementTypeId = Integer.parseInt(ti);
        AddReimbursementDTO dto = new AddReimbursementDTO();
        dto.setReimbAmount(reimbursementAmount);
        dto.setReimbSubmitted(reimbursementSubmitted);
        dto.setReimbDescription(reimbursementDescription);
        dto.setReceipt(is);
        dto.setReimbTypeId(reimbursementTypeId);

        ResponseReimbursementDTO getDto = this.reimbursementService.addReimbursement(id, dto);
        ctx.status(201); // 201 Created
        ctx.json(getDto);

    };


    private Handler judgeReimbursement =(ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if(!token.getBody().get("user_role").equals("Manager")){
            throw new UnauthorizedResponse("You must be a manager");
        }
        String reimbursementId =ctx.pathParam("reimbursementId");
        String reimbursementResolved= ctx.queryParam("reimb_resolved");
        String reimbursementStatusId = ctx.queryParam("reimb_status_id");
        int user_id = token.getBody().get("user_id", Integer.class);
        System.out.println("hi");
        if(reimbursementStatusId == null) {
            throw new UnauthorizedResponse("You must choose to approve or deny this reimbursement");
        }
        ResponseReimbursementDTO reimbursement = this.reimbursementService.judgeReimbursement(reimbursementId,reimbursementStatusId, reimbursementResolved,user_id);
        ctx.json(reimbursement);


    };

    private Handler getReimbursementImage = (ctx) -> {

        String reimbursementId = ctx.pathParam("reimbursementId");
        InputStream image = this.reimbursementService.getReimbursementImage(reimbursementId);

        Tika tika = new Tika();
        String mimeType = tika.detect(image);

        ctx.header("Content-Type", mimeType); // tell the client what type of image is being sent in the response
        ctx.result(image);


    };


    private Handler deleteReimbursement =(ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if(!token.getBody().get("user_role").equals("Manager")){
            throw new UnauthorizedResponse("You must be a manager");
        }
        String reimbursementId =ctx.pathParam("reimbursementId");
        boolean reimbursement = this.reimbursementService.deleteReimbursement(reimbursementId);
        ctx.json(reimbursement);


    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/reimbursements", getAllReimbursements);
        app.get("/reimbursements/approved", getAllApprovedReimbursements);
        app.get("/reimbursements/denied", getAllDeniedReimbursements);
        app.get("/reimbursements/pending", getAllPendingReimbursements);
        app.get("/users/{employeeId}/reimbursements", getReimbursementsByEmployee);
        app.get("/users/{employeeId}/reimbursements/pending", getPendingReimbursementsByEmployee);
        app.get("/users/{employeeId}/reimbursements/resolved", getResolvedReimbursementsByEmployee);
        app.post("/users/{employeeId}/reimbursements", addReimbursement);
        app.patch("/reimbursements/{reimbursementId}", judgeReimbursement);
        app.delete("/reimbursements/{reimbursementId}", deleteReimbursement);
        app.get("/reimbursements/{reimbursementId}/image", getReimbursementImage);
        //
    }
}
