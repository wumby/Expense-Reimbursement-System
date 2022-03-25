package com.revature.model;

import java.util.Objects;

public class Reimbursement {

    private int id;
    private int reimbAmount;
    private String reimbSubmitted;
    private String reimbResolved;
    private String reimbDescription;
    private int reimbAuthor;
    private int reimbResolver;
    private int reimbStatusId;
    private int reimbTypeId;


    private User employee;
    private User manager;

    public Reimbursement() {
    }

    public Reimbursement(int id, int reimbAmount, String reimbSubmitted, String reimbResolved, String reimbDescription, int reimbAuthor, int reimbResolver, int reimbStatusId, int reimbTypeId, User employee, User manager) {
        this.id = id;
        this.reimbAmount = reimbAmount;
        this.reimbSubmitted = reimbSubmitted;
        this.reimbResolved = reimbResolved;
        this.reimbDescription = reimbDescription;
        this.reimbAuthor = reimbAuthor;
        this.reimbResolver = reimbResolver;
        this.reimbStatusId = reimbStatusId;
        this.reimbTypeId = reimbTypeId;
        this.employee = employee;
        this.manager = manager;
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

    public int getReimbResolver() {
        return reimbResolver;
    }

    public void setReimbResolver(int reimbResolver) {
        this.reimbResolver = reimbResolver;
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

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public User getManager() {
        return manager;
    }

    public void setFinanceManager(User financeManager) {
        this.manager = financeManager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && reimbAmount == that.reimbAmount && reimbAuthor == that.reimbAuthor && reimbResolver == that.reimbResolver && reimbStatusId == that.reimbStatusId && reimbTypeId == that.reimbTypeId && reimbSubmitted.equals(that.reimbSubmitted) && reimbResolved.equals(that.reimbResolved) && reimbDescription.equals(that.reimbDescription) && employee.equals(that.employee) && manager.equals(that.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reimbAmount, reimbSubmitted, reimbResolved, reimbDescription,
                reimbAuthor, reimbResolver, reimbStatusId, reimbTypeId, employee, manager);
    }


    @Override
    public String toString() {
        return "Reimbursement{" + "id=" + id + ", reimbAmount=" + reimbAmount + ", reimbSubmitted='" + reimbSubmitted + '\'' + ", reimbResolved='" + reimbResolved + '\'' + ", reimbDescription='" + reimbDescription + '\'' + ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver + ", reimbStatusId=" + reimbStatusId + ", reimbTypeId=" + reimbTypeId + ", employee=" + employee + ", financeManager=" + manager + '}';
    }
}
