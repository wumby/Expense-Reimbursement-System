package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.exception.ImageNotFoundException;
import com.revature.exception.InvalidImageException;
import com.revature.model.Reimbursement;
import org.apache.tika.Tika;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {


    private ReimbursementDao reimbursementDao;


    public ReimbursementService() {
        this.reimbursementDao = new ReimbursementDao();
    }


    public List<ResponseReimbursementDTO> getAllPendingReimbursements() throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getAllPendingReimbursements();

        List<ResponseReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (Reimbursement r : reimbursements) {
            reimbursementDTOs.add(new ResponseReimbursementDTO(r.getId(), r.getReimbAmount(),r.getReimbSubmitted(),null,
                    r.getReimbDescription(),r.getReimbAuthor(),r.getReimbStatusId(),r.getReimbTypeId(),
                    r.getEmployee().getUsername(), r.getManager().getUsername()));


        }

        return reimbursementDTOs;
    }


    public List<ResponseReimbursementDTO> getAllDeniedReimbursements() throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getAllDeniedReimbursements();

        List<ResponseReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (Reimbursement r : reimbursements) {
            reimbursementDTOs.add(new ResponseReimbursementDTO(r.getId(), r.getReimbAmount(),r.getReimbSubmitted(),null,
                    r.getReimbDescription(),r.getReimbAuthor(),r.getReimbStatusId(),r.getReimbTypeId(),
                    r.getEmployee().getUsername(), r.getManager().getUsername()));


        }

        return reimbursementDTOs;
    }


    public List<ResponseReimbursementDTO> getAllApprovedReimbursements() throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getAllApprovedReimbursements();

        List<ResponseReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (Reimbursement r : reimbursements) {
            reimbursementDTOs.add(new ResponseReimbursementDTO(r.getId(), r.getReimbAmount(),r.getReimbSubmitted(),null,
                    r.getReimbDescription(),r.getReimbAuthor(),r.getReimbStatusId(),r.getReimbTypeId(),
                    r.getEmployee().getUsername(), r.getManager().getUsername()));


        }

        return reimbursementDTOs;
    }


    public List<ResponseReimbursementDTO> getAllReimbursements() throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getAllReimbursements();

        List<ResponseReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (Reimbursement r : reimbursements) {
            reimbursementDTOs.add(new ResponseReimbursementDTO(r.getId(), r.getReimbAmount(),r.getReimbSubmitted(),null,
                     r.getReimbDescription(),r.getReimbAuthor(),r.getReimbStatusId(),r.getReimbTypeId(),
                    r.getEmployee().getUsername(), r.getManager().getUsername()));


        }

        return reimbursementDTOs;
    }


    public List<ResponseReimbursementDTO> getReimbursementsByEmployee(int id) throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getReimbursementsByEmployee(id);

        List<ResponseReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (Reimbursement r : reimbursements) {
            reimbursementDTOs.add(new ResponseReimbursementDTO(r.getId(), r.getReimbAmount(),r.getReimbSubmitted(),null,
                    r.getReimbDescription(),r.getReimbAuthor(),r.getReimbStatusId(),r.getReimbTypeId(),
                    r.getEmployee().getUsername(), r.getManager().getUsername()));


        }

        return reimbursementDTOs;
    }

    public List<ResponseReimbursementDTO> getPendingReimbursementsByEmployee(int id) throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getPendingReimbursementsByEmployee(id);

        List<ResponseReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (Reimbursement r : reimbursements) {
            reimbursementDTOs.add(new ResponseReimbursementDTO(r.getId(), r.getReimbAmount(),r.getReimbSubmitted(),null,
                    r.getReimbDescription(),r.getReimbAuthor(),r.getReimbStatusId(),r.getReimbTypeId(),
                    r.getEmployee().getUsername(), r.getManager().getUsername()));


        }

        return reimbursementDTOs;
    }


    public List<ResponseReimbursementDTO> getResolvedReimbursementsByEmployee(int id) throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getResolvedReimbursementsByEmployee(id);

        List<ResponseReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (Reimbursement r : reimbursements) {
            reimbursementDTOs.add(new ResponseReimbursementDTO(r.getId(), r.getReimbAmount(),r.getReimbSubmitted(),null,
                    r.getReimbDescription(),r.getReimbAuthor(),r.getReimbStatusId(),r.getReimbTypeId(),
                    r.getEmployee().getUsername(), r.getManager().getUsername()));


        }

        return reimbursementDTOs;
    }

    public ResponseReimbursementDTO addReimbursement(int id, AddReimbursementDTO dto) throws InvalidImageException, IOException, SQLException {
        Tika tika = new Tika();
        String mimeType = tika.detect(dto.getReceipt());

        if(!mimeType.equals("image/jpeg")  && !mimeType.equals("image/gif") && !mimeType.equals("image/png")){
            throw new InvalidImageException("Image must be a .jpg, .gif, or a .png");
        }

        Reimbursement reimbursementAdded = this.reimbursementDao.addReimbursement(id, dto);

        return new ResponseReimbursementDTO(reimbursementAdded.getId(),reimbursementAdded.getReimbAmount(),reimbursementAdded.getReimbSubmitted(),null,reimbursementAdded.getReimbDescription(),
                reimbursementAdded.getReimbAuthor(),reimbursementAdded.getReimbStatusId(),reimbursementAdded.getReimbStatusId(),reimbursementAdded.getEmployee().getUsername(), null);


    }


    public ResponseReimbursementDTO judgeReimbursement(String reimbursementId, String reimbursementStatusId, String reimbursementResolved, int user_id) {
        try{
            int intReimbursementId = Integer.parseInt(reimbursementId);
            int intReimbursementStatusId = Integer.parseInt(reimbursementStatusId);

            Reimbursement reimbursement = this.reimbursementDao.judgeReimbursement(intReimbursementId,intReimbursementStatusId, reimbursementResolved, user_id);

            return new ResponseReimbursementDTO(reimbursement.getId(),reimbursement.getReimbAmount(),reimbursement.getReimbSubmitted(),reimbursement.getReimbResolved(),
                    reimbursement.getReimbDescription(),reimbursement.getReimbAuthor(),reimbursement.getReimbStatusId(),reimbursement.getReimbTypeId(),reimbursement.getEmployee().getUsername(),reimbursement.getManager().getUsername());
        }
        catch (NumberFormatException | SQLException e){
            throw new IllegalArgumentException("You must enter valid int values for the Id's");
        }
    }


    public InputStream getReimbursementImage(String reimbursementId) {
        try {
            int aId = Integer.parseInt(reimbursementId);

            InputStream is = this.reimbursementDao.getReimbursementImage(aId);

            if (is == null) {
                throw new ImageNotFoundException("Reimbursement id " + reimbursementId + " does not have an image");
            }

            return is;
        } catch(NumberFormatException | SQLException | ImageNotFoundException e) {
            throw new IllegalArgumentException("Reimbursement and/or user id must be an int value");
        }
    }


}
