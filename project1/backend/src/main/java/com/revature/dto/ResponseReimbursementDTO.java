package com.revature.dto;

import java.util.Objects;

public class ResponseReimbursementDTO {

    private int id;
    private int reimbAmount;
    private String reimbSubmitted;
    private String reimbResolved;
    private String reimbDescription;
    private int reimbAuthor;
    private int reimbStatusId;
    private int reimbTypeId;


    private String employeeUsername;
    private String managerUsername;

    public ResponseReimbursementDTO() {
    }

    public ResponseReimbursementDTO(int id, int reimbAmount, String reimbSubmitted, String reimbResolved,
                                    String reimbDescription, int reimbAuthor, int reimbStatusId, int reimbTypeId,
                                    String employeeUsername, String managerUsername) {
        this.id = id;
        this.reimbAmount = reimbAmount;
        this.reimbSubmitted = reimbSubmitted;
        this.reimbResolved = reimbResolved;
        this.reimbDescription = reimbDescription;
        this.reimbAuthor = reimbAuthor;
        this.reimbStatusId = reimbStatusId;
        this.reimbTypeId = reimbTypeId;
        this.employeeUsername = employeeUsername;
        this.managerUsername = managerUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getReimbResolved() {
        return reimbResolved;
    }

    public void setReimbResolved(String reimbResolved) {
        this.reimbResolved = reimbResolved;
    }

    public String getReimbDescription() {
        return reimbDescription;
    }

    public void setReimbDescription(String reimbDescription) {
        this.reimbDescription = reimbDescription;
    }

    public int getReimbAuthor() {
        return reimbAuthor;
    }

    public void setReimbAuthor(int reimbAuthor) {
        this.reimbAuthor = reimbAuthor;
    }

    public int getReimbStatusId() {
        return reimbStatusId;
    }

    public void setReimbStatusId(int reimbStatusId) {
        this.reimbStatusId = reimbStatusId;
    }

    public int getReimbTypeId() {
        return reimbTypeId;
    }

    public void setReimbTypeId(int reimbTypeId) {
        this.reimbTypeId = reimbTypeId;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseReimbursementDTO that = (ResponseReimbursementDTO) o;
        return id == that.id && reimbAmount == that.reimbAmount && reimbAuthor == that.reimbAuthor && reimbStatusId == that.reimbStatusId && reimbTypeId == that.reimbTypeId && reimbSubmitted.equals(that.reimbSubmitted) && reimbResolved.equals(that.reimbResolved) && reimbDescription.equals(that.reimbDescription) && employeeUsername.equals(that.employeeUsername) && managerUsername.equals(that.managerUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reimbAmount, reimbSubmitted, reimbResolved, reimbDescription, reimbAuthor,
                reimbStatusId, reimbTypeId, employeeUsername, managerUsername);
    }

    @Override
    public String toString() {
        return "ResponseReimbursementDTO{" + "id=" + id + ", reimbAmount=" + reimbAmount + ", reimbSubmitted='" + reimbSubmitted + '\'' + ", reimbResolved='" + reimbResolved + '\'' + ", reimbDescription='" + reimbDescription + '\'' + ", reimbAuthor=" + reimbAuthor + ", reimbStatusId=" + reimbStatusId + ", reimbTypeId=" + reimbTypeId + ", employeeUsername='" + employeeUsername + '\'' + ", managerUsername='" + managerUsername + '\'' + '}';
    }
}
