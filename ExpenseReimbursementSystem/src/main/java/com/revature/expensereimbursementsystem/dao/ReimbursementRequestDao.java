/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.dao;

import com.revature.expensereimbursementsystem.dto.ReimbursementRequest;
import java.util.List;

/**
 *
 * @author James
 */
public interface ReimbursementRequestDao {
    
    public void addReimbursementRequest(ReimbursementRequest reimbursementRequest) throws ERSPersistenceException;
    
    public void deleteReimbursementRequest(int reimbursementRequestId) throws ERSPersistenceException;
    
    public void updateReimbursementRequest(ReimbursementRequest reimbursementRequest) throws ERSPersistenceException;
    
    public ReimbursementRequest getReimbursementRequestByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException;
    
    public List<ReimbursementRequest> getAllReimbursementRequests() throws ERSPersistenceException;
        
}
