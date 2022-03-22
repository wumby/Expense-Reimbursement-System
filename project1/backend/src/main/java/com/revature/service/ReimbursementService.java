package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentService {

    private ReimbursementDao reimbursementDao;

    public AssignmentService() {
        this.reimbursementDao = new ReimbursementDao();
    }

    public AssignmentService(ReimbursementDao mockDao) {
        this.reimbursementDao = mockDao;
    }


    public List<ResponseReimbursementDTO> getAllReimbursements() throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getAllReimbursements();

        List<ResponseReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (Reimbursement r : reimbursements) {
            reimbursementDTOs.add(new ResponseReimbursementDTO(r.getId(), r.getReimbAmount(),
                     r.getReimbDescription(),r.getReimbStatusId(),r.getReimbTypeId(),
                    re.getEmployee().getUsername(), r.getManager().getUsername()));


        }

        return reimbursementDTOs;
    }

}
