/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.service;

import com.revature.expensereimbursementsystem.dao.ERSPersistenceException;
import com.revature.expensereimbursementsystem.dto.ReimbursementRequest;
import java.util.List;

/**
 *
 * @author James
 */
public interface ReimbursementRequestServiceLayer {
    
    public void addReimbursementRequest(ReimbursementRequest reimbursementRequest) throws ERSPersistenceException;
    
    public void deleteReimbursementRequest(int reimbursementRequestId) throws ERSPersistenceException;
    
    public void updateReimbursementRequest(ReimbursementRequest reimbursementRequest) throws ERSPersistenceException;
    
    public ReimbursementRequest getReimbursementRequestByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException;
    
    public List<ReimbursementRequest> getAllReimbursementRequests() throws ERSPersistenceException;
    
    public List<ReimbursementRequest> getAllPendingRequestsByEmployeeId(int employeeId) throws ERSPersistenceException;
    
    public List<ReimbursementRequest> getAllResolvedRequestsByEmployeeId(int employeeId) throws ERSPersistenceException;
    
}
