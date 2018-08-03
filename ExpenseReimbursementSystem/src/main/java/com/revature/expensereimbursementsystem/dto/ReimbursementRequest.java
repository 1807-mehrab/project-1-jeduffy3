/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.dto;

/**
 *
 * @author James
 */
public class ReimbursementRequest {
    
    private int reimbursementRequestId;
    private String description;
    private String receipt; // image of receipt
    private String amount;
    private Employee requester;
    private Employee approver;
    private Status status;

    public int getReimbursementRequestId() {
        return reimbursementRequestId;
    }

    public void setReimbursementRequestId(int reimbursementRequestId) {
        this.reimbursementRequestId = reimbursementRequestId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Employee getRequester() {
        return requester;
    }

    public void setRequester(Employee requester) {
        this.requester = requester;
    }

    public Employee getApprover() {
        return approver;
    }

    public void setApprover(Employee approver) {
        this.approver = approver;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
