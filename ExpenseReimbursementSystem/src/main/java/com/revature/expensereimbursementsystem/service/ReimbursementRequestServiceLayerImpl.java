/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.service;

import com.revature.expensereimbursementsystem.dao.ERSPersistenceException;
import com.revature.expensereimbursementsystem.dao.ReimbursementRequestDao;
import com.revature.expensereimbursementsystem.dao.ReimbursementRequestDaoOracleSqlImpl;
import com.revature.expensereimbursementsystem.dto.ReimbursementRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class ReimbursementRequestServiceLayerImpl implements ReimbursementRequestServiceLayer {

    private ReimbursementRequestDao reimbursementRequestDao;
    private EmployeeServiceLayer employeeServiceLayer;
    private StatusServiceLayer statusServiceLayer;

    public ReimbursementRequestServiceLayerImpl() {
        this.reimbursementRequestDao = new ReimbursementRequestDaoOracleSqlImpl();
        this.employeeServiceLayer = new EmployeeServiceLayerImpl();
        this.statusServiceLayer = new StatusServiceLayerImpl();
    }

    @Override
    public void addReimbursementRequest(ReimbursementRequest reimbursementRequest) throws ERSPersistenceException {
        reimbursementRequestDao.addReimbursementRequest(reimbursementRequest);
    }

    @Override
    public void deleteReimbursementRequest(int reimbursementRequestId) throws ERSPersistenceException {
        reimbursementRequestDao.deleteReimbursementRequest(reimbursementRequestId);
    }

    @Override
    public void updateReimbursementRequest(ReimbursementRequest reimbursementRequest) throws ERSPersistenceException {
        reimbursementRequestDao.updateReimbursementRequest(reimbursementRequest);
    }

    @Override
    public ReimbursementRequest getReimbursementRequestByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException {
        if (reimbursementRequestDao.getReimbursementRequestByReimbursementRequestId(reimbursementRequestId) == null) {
            return null;
        } else {
            return associateEmployeeAndStatusWithReimbursementRequest(reimbursementRequestDao.getReimbursementRequestByReimbursementRequestId(reimbursementRequestId));
        }
    }

    @Override
    public List<ReimbursementRequest> getAllReimbursementRequests() throws ERSPersistenceException {
        List<ReimbursementRequest> reimbursementRequestList = new ArrayList<>();
        for (ReimbursementRequest reimbursementRequest : reimbursementRequestDao.getAllReimbursementRequests()) {
            reimbursementRequestList.add(associateEmployeeAndStatusWithReimbursementRequest(reimbursementRequest));
        }
        return reimbursementRequestList;
    }

    @Override
    public List<ReimbursementRequest> getAllPendingRequestsByEmployeeId(int employeeId) throws ERSPersistenceException {
        List<ReimbursementRequest> reimbursementRequestList = new ArrayList<>();
        for (ReimbursementRequest reimbursementRequest : reimbursementRequestDao.getAllReimbursementRequests()) {
            associateEmployeeAndStatusWithReimbursementRequest(reimbursementRequest);
            if (reimbursementRequest.getStatus().getStatus().equalsIgnoreCase("pending")) {
                if ((reimbursementRequest.getApprover().getEmployeeId() == employeeId) | (reimbursementRequest.getRequester().getEmployeeId() == employeeId)) {
                    reimbursementRequestList.add(reimbursementRequest);
                }
            }
        }
        return reimbursementRequestList;
    }

    @Override
    public List<ReimbursementRequest> getAllResolvedRequestsByEmployeeId(int employeeId) throws ERSPersistenceException {
        List<ReimbursementRequest> reimbursementRequestList = new ArrayList<>();
        for (ReimbursementRequest reimbursementRequest : reimbursementRequestDao.getAllReimbursementRequests()) {
            associateEmployeeAndStatusWithReimbursementRequest(reimbursementRequest);
            if (reimbursementRequest.getStatus().getStatus().equalsIgnoreCase("resolved")) {
                if ((reimbursementRequest.getApprover().getEmployeeId() == employeeId) | (reimbursementRequest.getRequester().getEmployeeId() == employeeId)) {
                    reimbursementRequestList.add(reimbursementRequest);
                }
            }
        }
        return reimbursementRequestList;
    }

    private ReimbursementRequest associateEmployeeAndStatusWithReimbursementRequest(ReimbursementRequest reimbursementRequest) throws ERSPersistenceException {
        reimbursementRequest.setRequester(employeeServiceLayer.getRequesterByReimbursementRequestId(reimbursementRequest.getReimbursementRequestId()));
        reimbursementRequest.setApprover(employeeServiceLayer.getApproverByReimbursementRequestId(reimbursementRequest.getReimbursementRequestId()));
        reimbursementRequest.setStatus(statusServiceLayer.getStatusByReimbursementRequestId(reimbursementRequest.getReimbursementRequestId()));
        return reimbursementRequest;
    }

}
