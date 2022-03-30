package com.revature.dao;

import com.revature.dto.AddReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDao {

    public List<Reimbursement> getAllPendingReimbursements() throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "SELECT r.reimb_id , r.reimb_amount, r.reimb_submitted,r.reimb_description ,r.reimb_author,r.reimb_status_id,r.reimb_type_id " + ",e.USERS_ID as employee_id," +
                    " e.username as employee_username,e.user_password as employee_password,e.first_name as employee_firstname,e.last_name as employee_lastname,e.user_email as employee_email," +
                    "m.USERS_ID as manager_id, m.username as manager_username,m.user_password as manager_password,m.first_name as manager_firstname,m.last_name as manager_lastname,m.user_email as manager_email " +
                    "FROM reimbursements r " +
                    "left join users e " + "on e.USERS_ID = r.reimb_author " +
                    "left join users m " + "on m.USERS_ID = r.reimb_resolver " +
                    "where r.reimb_status_id = 2";

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

                Reimbursement a = new Reimbursement(reimbursementId, reimbursementAmount, reimbursementSubmitted, null, reimbursementDescription,  reimbursementAuthor, 0, reimbursementStatus, reimbursementType, employee, manager);

                reimbursements.add(a);
            }

            return reimbursements;
        }
    }


    public List<Reimbursement> getAllApprovedReimbursements() throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "SELECT r.reimb_id , r.reimb_amount, r.reimb_submitted,r.reimb_description ,r.reimb_author,r.reimb_status_id,r.reimb_type_id " + ",e.USERS_ID as employee_id," +
                    " e.username as employee_username,e.user_password as employee_password,e.first_name as employee_firstname,e.last_name as employee_lastname,e.user_email as employee_email," +
                    "m.USERS_ID as manager_id, m.username as manager_username,m.user_password as manager_password,m.first_name as manager_firstname,m.last_name as manager_lastname,m.user_email as manager_email " +
                    "FROM reimbursements r " +
                    "left join users e " + "on e.USERS_ID = r.reimb_author " +
                    "left join users m " + "on m.USERS_ID = r.reimb_resolver " +
                    "where r.reimb_status_id = 1";

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

                Reimbursement a = new Reimbursement(reimbursementId, reimbursementAmount, reimbursementSubmitted, null, reimbursementDescription,  reimbursementAuthor, 0, reimbursementStatus, reimbursementType, employee, manager);

                reimbursements.add(a);
            }

            return reimbursements;
        }
    }


    public List<Reimbursement> getAllDeniedReimbursements() throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "SELECT r.reimb_id , r.reimb_amount, r.reimb_submitted,r.reimb_description ,r.reimb_author,r.reimb_status_id,r.reimb_type_id " + ",e.USERS_ID as employee_id," +
                    " e.username as employee_username,e.user_password as employee_password,e.first_name as employee_firstname,e.last_name as employee_lastname,e.user_email as employee_email," +
                    "m.USERS_ID as manager_id, m.username as manager_username,m.user_password as manager_password,m.first_name as manager_firstname,m.last_name as manager_lastname,m.user_email as manager_email " +
                    "FROM reimbursements r " +
                    "left join users e " + "on e.USERS_ID = r.reimb_author " +
                    "left join users m " + "on m.USERS_ID = r.reimb_resolver " +
                    "where r.reimb_status_id = 3";

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

                Reimbursement a = new Reimbursement(reimbursementId, reimbursementAmount, reimbursementSubmitted, null, reimbursementDescription,  reimbursementAuthor, 0, reimbursementStatus, reimbursementType, employee, manager);

                reimbursements.add(a);
            }

            return reimbursements;
        }
    }


    public List<Reimbursement> getAllReimbursements() throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "SELECT r.reimb_id , r.reimb_amount, r.reimb_submitted,r.reimb_description ,r.reimb_author,r.reimb_status_id,r.reimb_type_id " + ",e.USERS_ID as employee_id," +
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

                Reimbursement a = new Reimbursement(reimbursementId, reimbursementAmount, reimbursementSubmitted, null, reimbursementDescription,  reimbursementAuthor, 0, reimbursementStatus, reimbursementType, employee, manager);

                reimbursements.add(a);
            }

            return reimbursements;
        }
    }


    public List<Reimbursement> getReimbursementsByEmployee(int id) throws SQLException{
        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "SELECT r.reimb_id , r.reimb_amount, r.reimb_submitted,r.reimb_description ,r.reimb_author,r.reimb_status_id,r.reimb_type_id " + ",e.USERS_ID as employee_id," +
                    " e.username as employee_username,e.user_password as employee_password,e.first_name as employee_firstname,e.last_name as employee_lastname,e.user_email as employee_email," +
                    "m.USERS_ID as manager_id, m.username as manager_username,m.user_password as manager_password,m.first_name as manager_firstname,m.last_name as manager_lastname,m.user_email as manager_email " +
                    "FROM reimbursements r " +
                    "left join users e " + "on e.USERS_ID = r.reimb_author " +
                    "left join users m " + "on m.USERS_ID = r.reimb_resolver " +
                    "where e.USERS_ID = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);

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

                Reimbursement a = new Reimbursement(reimbursementId, reimbursementAmount, reimbursementSubmitted, null, reimbursementDescription,
                        reimbursementAuthor, 0, reimbursementStatus, reimbursementType, employee, manager);

                reimbursements.add(a);
            }

            return reimbursements;
        }
    }



    public List<Reimbursement> getPendingReimbursementsByEmployee(int id) throws SQLException{
        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "SELECT r.reimb_id , r.reimb_amount, r.reimb_submitted,r.reimb_description ,r.reimb_author,r.reimb_status_id,r.reimb_type_id " + ",e.USERS_ID as employee_id," +
                    " e.username as employee_username,e.user_password as employee_password,e.first_name as employee_firstname,e.last_name as employee_lastname,e.user_email as employee_email," +
                    "m.USERS_ID as manager_id, m.username as manager_username,m.user_password as manager_password,m.first_name as manager_firstname,m.last_name as manager_lastname,m.user_email as manager_email " +
                    "FROM reimbursements r " +
                    "left join users e " + "on e.USERS_ID = r.reimb_author " +
                    "left join users m " + "on m.USERS_ID = r.reimb_resolver " +
                    "where e.USERS_ID = ? AND r.reimb_status_id = 2";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);

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

                Reimbursement a = new Reimbursement(reimbursementId, reimbursementAmount, reimbursementSubmitted, null, reimbursementDescription,
                        reimbursementAuthor, 0, reimbursementStatus, reimbursementType, employee, manager);

                reimbursements.add(a);
            }

            return reimbursements;
        }
    }


    public List<Reimbursement> getResolvedReimbursementsByEmployee(int id) throws SQLException{
        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "SELECT r.reimb_id , r.reimb_amount, r.reimb_submitted,r.reimb_description ,r.reimb_author,r.reimb_status_id,r.reimb_type_id " + ",e.USERS_ID as employee_id," +
                    " e.username as employee_username,e.user_password as employee_password,e.first_name as employee_firstname,e.last_name as employee_lastname,e.user_email as employee_email," +
                    "m.USERS_ID as manager_id, m.username as manager_username,m.user_password as manager_password,m.first_name as manager_firstname,m.last_name as manager_lastname,m.user_email as manager_email " +
                    "FROM reimbursements r " +
                    "left join users e " + "on e.USERS_ID = r.reimb_author " +
                    "left join users m " + "on m.USERS_ID = r.reimb_resolver " +
                    "where e.USERS_ID = ? AND r.reimb_status_id = 2 OR 3 ";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);

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

                Reimbursement a = new Reimbursement(reimbursementId, reimbursementAmount, reimbursementSubmitted, null, reimbursementDescription,
                        reimbursementAuthor, 0, reimbursementStatus, reimbursementType, employee, manager);

                reimbursements.add(a);
            }

            return reimbursements;
        }
    }


    public Reimbursement addReimbursement(int id, AddReimbursementDTO dto) throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()){
            con.setAutoCommit(false);
            String sql = "insert into reimbursements (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION, REIMB_RECEIPT, REIMB_AUTHOR, REIMB_TYPE_ID) " +
                    "values (?,?,?,?,?,?) ";

            PreparedStatement pstmt1 = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt1.setInt(1, dto.getReimbAmount());
            pstmt1.setString(2,dto.getReimbSubmitted());
            pstmt1.setString(3, dto.getReimbDescription());
            pstmt1.setBinaryStream(4, dto.getReceipt());
            pstmt1.setInt(5,id);
            pstmt1.setInt(6,dto.getReimbTypeId());
            pstmt1.executeUpdate();

            ResultSet rs = pstmt1.getGeneratedKeys();
            rs.next();
            int reimbursementId = rs.getInt(1);

            String sql2 = "Select * from users Where users_id = ?";
            PreparedStatement pstmt2 = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);

            pstmt2.setInt(1, id);

            ResultSet rs2 = pstmt2.executeQuery();
            rs2.next();

            int employeeId = rs2.getInt("users_id");
            String employeeUsername = rs2.getString("username");
            String employeePassword = rs2.getString("user_password");
            String employeeFirstName = rs2.getString("first_name");
            String employeeLastName = rs2.getString("last_name");
            String employeeEmail = rs2.getString("user_email");
            String employeeUserRole = "employee";

            User employee = new User(employeeId,employeeUsername,employeePassword,employeeFirstName,employeeLastName,employeeEmail,employeeUserRole);

            Reimbursement reimbursement = new Reimbursement(reimbursementId,dto.getReimbAmount(), dto.getReimbSubmitted(), null, dto.getReimbDescription(),
                    employeeId,0,2,dto.getReimbTypeId(),employee,null);

            con.commit();
            return reimbursement;



        }
    }


    public Reimbursement judgeReimbursement(int intReimbursementId, int intReimbursementStatusId, String reimbursementResolved, int user_id) throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()){
            con.setAutoCommit(false);

            String sql = "update reimbursements " +
                    "set reimb_resolved = ?, " +
                    "reimb_resolver = ?, " +
                    "reimb_status_id = ? " +
                    "where reimb_id  = ? ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,reimbursementResolved);
            pstmt.setInt(2,user_id);
            pstmt.setInt(3,intReimbursementStatusId);
            pstmt.setInt(4,intReimbursementId);
            pstmt.executeUpdate();


            String sql2 = "SELECT r.reimb_id , r.reimb_amount, r.reimb_submitted,r.reimb_description ,r.reimb_author,r.reimb_status_id,r.reimb_type_id " + ",e.USERS_ID as employee_id," +
                    " e.username as employee_username,e.user_password as employee_password,e.first_name as employee_firstname,e.last_name as employee_lastname,e.user_email as employee_email," +
                    "m.USERS_ID as manager_id, m.username as manager_username,m.user_password as manager_password,m.first_name as manager_firstname,m.last_name as manager_lastname,m.user_email as manager_email " +
                    "FROM reimbursements r " +
                    "left join users e " + "on e.USERS_ID = r.reimb_author " +
                    "left join users m " + "on m.USERS_ID = r.reimb_resolver " +
                    "where r.reimb_id = ?";

            PreparedStatement pstmt2 = con.prepareStatement(sql2);
            pstmt2.setInt(1,intReimbursementId);

            ResultSet rs = pstmt2.executeQuery();

            rs.next();
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

            Reimbursement r = new Reimbursement(reimbursementId, reimbursementAmount, reimbursementSubmitted, reimbursementResolved, reimbursementDescription,
                    reimbursementAuthor, managerId, reimbursementStatus, reimbursementType, employee, manager);

            con.commit();
            return r;
            }
        }


    public InputStream getReimbursementImage(int aId) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "SELECT r.reimb_receipt " +
                    "FROM reimbursements r " +
                    "WHERE r.reimb_id = ? ";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, aId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                InputStream is = rs.getBinaryStream("reimb_receipt");

                return is;
            } else {
                return null;
            }
        }
    }

}


