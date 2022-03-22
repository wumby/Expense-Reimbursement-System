package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.model.Reimbursement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {

    private ReimbursementDao reimbursementDao;

    public ReimbursementService() {
        this.reimbursementDao = new ReimbursementDao();
    }

    public ReimbursementService(ReimbursementDao mockDao) {
        this.reimbursementDao = mockDao;
    }


    public List<ResponseReimbursementDTO> getAllReimbursements() throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getAllReimbursements();

        List<ResponseReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (Reimbursement r : reimbursements) {
            reimbursementDTOs.add(new ResponseReimbursementDTO(r.getId(), r.getReimbAmount(),r.getReimbSubmitted(),
                     r.getReimbDescription(),r.getReimbAuthor(),r.getReimbStatusId(),r.getReimbTypeId(),
                    r.getEmployee().getUsername(), r.getManager().getUsername()));


        }

        return reimbursementDTOs;
    }

    public List<ResponseReimbursementDTO> getReimbursementsByEmployee(int id) throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getReimbursementsByEmployee(id);

        List<ResponseReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (Reimbursement r : reimbursements) {
            reimbursementDTOs.add(new ResponseReimbursementDTO(r.getId(), r.getReimbAmount(),r.getReimbSubmitted(),
                    r.getReimbDescription(),r.getReimbAuthor(),r.getReimbStatusId(),r.getReimbTypeId(),
                    r.getEmployee().getUsername(), r.getManager().getUsername()));


        }

        return reimbursementDTOs;
    }
}
