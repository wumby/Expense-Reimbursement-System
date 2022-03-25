package com.revature.dto;

import java.io.InputStream;
import java.util.Objects;

public class AddReimbursementDTO {

    private int reimbAmount;
    private String reimbSubmitted;
    private String reimbDescription;
    private InputStream receipt;
    private int reimbTypeId;

    public AddReimbursementDTO() {
    }

    public AddReimbursementDTO(int reimbAmount, String reimbSubmitted, String reimbDescription, InputStream receipt,
                               int reimbTypeId) {
        this.reimbAmount = reimbAmount;
        this.reimbSubmitted = reimbSubmitted;
        this.reimbDescription = reimbDescription;
        this.receipt = receipt;
        this.reimbTypeId = reimbTypeId;
    }

    public int getReimbAmount() {
        return reimbAmount;
    }

    public void setReimbAmount(int reimbAmount) {
        this.reimbAmount = reimbAmount;
    }

    public String getReimbSubmitted() {
        return reimbSubmitted;
    }

    public void setReimbSubmitted(String reimbSubmitted) {
        this.reimbSubmitted = reimbSubmitted;
    }

    public String getReimbDescription() {
        return reimbDescription;
    }

    public void setReimbDescription(String reimbDescription) {
        this.reimbDescription = reimbDescription;
    }

    public InputStream getReceipt() {
        return receipt;
    }

    public void setReceipt(InputStream receipt) {
        this.receipt = receipt;
    }

    public int getReimbTypeId() {
        return reimbTypeId;
    }

    public void setReimbTypeId(int reimbTypeId) {
        this.reimbTypeId = reimbTypeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddReimbursementDTO that = (AddReimbursementDTO) o;
        return reimbAmount == that.reimbAmount && reimbTypeId == that.reimbTypeId && reimbSubmitted.equals(that.reimbSubmitted) && reimbDescription.equals(that.reimbDescription) && receipt.equals(that.receipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbAmount, reimbSubmitted, reimbDescription, receipt, reimbTypeId);
    }

    @Override
    public String toString() {
        return "AddReimbursementDTO{" + "reimbAmount=" + reimbAmount + ", reimbSubmitted='" + reimbSubmitted + '\'' + ", reimbDescription='" + reimbDescription + '\'' + ", receipt=" + receipt + ", reimbTypeId=" + reimbTypeId + '}';
    }
}
