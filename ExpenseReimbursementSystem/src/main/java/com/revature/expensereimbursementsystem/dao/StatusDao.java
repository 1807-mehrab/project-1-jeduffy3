/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.dao;

import com.revature.expensereimbursementsystem.dto.Status;
import java.util.List;

/**
 *
 * @author James
 */
public interface StatusDao {
    
    public void addStatus(Status status) throws ERSPersistenceException;
    
    public void deleteStatus(int statusId) throws ERSPersistenceException;
    
    public void updateStatus(Status status) throws ERSPersistenceException;
    
    public Status getStatusByStatusId(int statusId) throws ERSPersistenceException;
    
    public Status getStatusByStatus(String status) throws ERSPersistenceException;
    
    public Status getStatusByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException;
    
    public List<Status> getAllStatuses() throws ERSPersistenceException;
    
}
