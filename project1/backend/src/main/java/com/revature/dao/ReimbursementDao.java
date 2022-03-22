package com.revature.dao;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDao {

    public List<Reimbursement> getAllReimbursements() throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "SELECT r.reimb_id , r.reimb_amount, r.reimb_submitted,r.reimb_description ,r.reimb_author,rs.reimb_status_id,r.reimb_type_id " + ",e.USERS_ID as employee_id," +
                    " e.username as employee_username,e.user_password as employee_password,e.first_name as employee_firstname,e.last_name as employee_lastname,e.user_email as employee_email," +
                    "m.USERS_ID as manager_id, m.username as manager_username,m.user_password as manager_password,m.first_name as manager_firstname,m.last_name as manager_lastname,m.user_email as manager_email " +
                    "FROM reimbursements r " +
                    "left join users e " + "on e.USERS_ID = r.reimb_author " +
                    "left join users m " + "on m.USERS_ID = r.reimb_resolver ";

            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // Reimbursements
                int reimbursementId = rs.getInt("reimb_id");
                int reimbursementAmount = rs.getInt("reimb_amount");
                String reimbursementSubmitted = rs.getString("reimb_submitted");
                //String reimbursementResolved = rs.getString("reimb_resolved");
                String reimbursementDescription = rs.getString("reimb_description");
                //String reimbursementReceipt = rs.getString("reimb_receipt");
                int reimbursementAuthor = rs.getInt("reimb_author");
                //int reimbursementResolver = rs.getInt("reimb_resolver");
                int reimbursementStatus = rs.getInt("reimb_status_id");
                int reimbursementType = rs.getInt("reimb_type_id");


                // Employee User
                int employeeId = rs.getInt("employee_id");
                String employeeUsername = rs.getString("employee_username");
                String employeePassword = rs.getString("employee_password");
                String employeeFirstName = rs.getString("employee_firstname");
                String employeeLastName = rs.getString("employee_lastname");
                String employeeEmail = rs.getString("employee_email");
                String employeeUserRole = "employee";

                User employee = new User(employeeId, employeeUsername, employeePassword, employeeFirstName, employeeLastName, employeeEmail, employeeUserRole);

                // Manager User
                int managerId = rs.getInt("manager_id");
                String managerUsername = rs.getString("manager_username");
                String managerPassword = rs.getString("manager_password");
                String managerFirstName = rs.getString("manager_firstname");
                String managerLastName = rs.getString("manager_lastname");
                String managerEmail = rs.getString("manager_email");
                String managerUserRole = "manager";

                User manager = new User(managerId, managerUsername, managerPassword, managerFirstName, managerLastName, managerEmail, managerUserRole);

                Reimbursement a = new Reimbursement(reimbursementId, reimbursementAmount, reimbursementSubmitted, null, reimbursementDescription, null, reimbursementAuthor, 0, reimbursementStatus, reimbursementType, employee, manager);

                reimbursements.add(a);
            }

            return reimbursements;
        }
    }
}


