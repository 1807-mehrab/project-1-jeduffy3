/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.service;

import com.revature.expensereimbursementsystem.dao.ERSPersistenceException;
import com.revature.expensereimbursementsystem.dao.StatusDao;
import com.revature.expensereimbursementsystem.dao.StatusDaoOracleSqlImpl;
import com.revature.expensereimbursementsystem.dto.Status;
import java.util.List;

/**
 *
 * @author James
 */
public class StatusServiceLayerImpl implements StatusServiceLayer {

    private StatusDao statusDao;

    public StatusServiceLayerImpl() {
        this.statusDao = new StatusDaoOracleSqlImpl();
    }
    
    @Override
    public void addStatus(Status status) throws ERSPersistenceException {
        statusDao.addStatus(status);
    }

    @Override
    public void deleteStatus(int statusId) throws ERSPersistenceException {
        statusDao.deleteStatus(statusId);
    }

    @Override
    public void updateStatus(Status status) throws ERSPersistenceException {
        statusDao.updateStatus(status);
    }

    @Override
    public Status getStatusByStatusId(int statusId) throws ERSPersistenceException {
        return statusDao.getStatusByStatusId(statusId);
    }
    
    @Override
    public Status getStatusByStatus(String status) throws ERSPersistenceException {
        return statusDao.getStatusByStatus(status);
    }
    
    @Override
    public Status getStatusByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException {
        return statusDao.getStatusByReimbursementRequestId(reimbursementRequestId);
    }

    @Override
    public List<Status> getAllStatuses() throws ERSPersistenceException {
        return statusDao.getAllStatuses();
    }
    
}
