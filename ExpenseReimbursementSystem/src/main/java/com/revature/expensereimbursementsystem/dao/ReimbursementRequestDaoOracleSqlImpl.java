/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.dao;

import com.revature.expensereimbursementsystem.dto.ReimbursementRequest;
import com.revature.expensereimbursementsystem.util.ConnectionUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class ReimbursementRequestDaoOracleSqlImpl implements ReimbursementRequestDao {

    private static final String SQL_INSERT_REIMBURSEMENT_REQUEST 
            = "INSERT INTO reimbursement_requests (description, receipt, amount, requester_id, approver_id, status_id) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_DELETE_REIMBURSEMENT_REQUEST 
            = "DELETE FROM reimbursement_requests "
            + "WHERE reimbursement_request_id = ?";
    
    private static final String SQL_UPDATE_REIMBURSEMENT_REQUEST 
            = "UPDATE reimbursement_requests "
            + "SET description = ?, receipt = ?, amount = ?, requester_id = ?, approver_id = ?, status_id = ? "
            + "WHERE reimbursement_request_id = ?";
    
    private static final String SQL_SELECT_REIMBURSEMENT_REQUEST_BY_REIMBURSEMENT_REQUEST_ID 
            = "SELECT reimbursement_request_id, description, receipt, amount "
            + "FROM reimbursement_requests "
            + "WHERE reimbursement_request_id = ?";
    
    private static final String SQL_SELECT_ALL_REIMBURSEMENT_REQUESTS 
            = "SELECT reimbursement_request_id, description, receipt, amount "
            + "FROM reimbursement_requests";
    
    @Override
    public void addReimbursementRequest(ReimbursementRequest reimbursementRequest) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_INSERT_REIMBURSEMENT_REQUEST);
            ps.setString(1, reimbursementRequest.getDescription());
            ps.setString(2, reimbursementRequest.getReceipt());
            ps.setString(3, reimbursementRequest.getAmount());
            ps.setInt(4, reimbursementRequest.getRequester().getEmployeeId());
            ps.setInt(5, reimbursementRequest.getApprover().getEmployeeId());
            ps.setInt(6, reimbursementRequest.getStatus().getStatusId());
            rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new ERSPersistenceException("Could not connect to db.", e);
        } catch (IOException e) {
            throw new ERSPersistenceException("Could not write to db.", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new ERSPersistenceException("Could not close db.", e);
            }
        }
    }

    @Override
    public void deleteReimbursementRequest(int reimbursementRequestId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_DELETE_REIMBURSEMENT_REQUEST);
            ps.setInt(1, reimbursementRequestId);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new ERSPersistenceException("Could not connect to db.", e);
        } catch (IOException e) {
            throw new ERSPersistenceException("Could not write to db.", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new ERSPersistenceException("Could not close db.", e);
            }
        }
    }

    @Override
    public void updateReimbursementRequest(ReimbursementRequest reimbursementRequest) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_UPDATE_REIMBURSEMENT_REQUEST);
            ps.setString(1, reimbursementRequest.getDescription());
            ps.setString(2, reimbursementRequest.getReceipt());
            ps.setString(3, reimbursementRequest.getAmount());
            ps.setInt(4, reimbursementRequest.getRequester().getEmployeeId());
            ps.setInt(5, reimbursementRequest.getApprover().getEmployeeId());
            ps.setInt(6, reimbursementRequest.getStatus().getStatusId());
            ps.setInt(7, reimbursementRequest.getReimbursementRequestId());
            rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new ERSPersistenceException("Could not connect to db.", e);
        } catch (IOException e) {
            throw new ERSPersistenceException("Could not write to db.", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new ERSPersistenceException("Could not close db.", e);
            }
        }
    }

    @Override
    public ReimbursementRequest getReimbursementRequestByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ReimbursementRequest reimbursementRequest = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_REIMBURSEMENT_REQUEST_BY_REIMBURSEMENT_REQUEST_ID);
            ps.setInt(1, reimbursementRequestId);
            rs = ps.executeQuery();
            while (rs.next()) {
                reimbursementRequest = new ReimbursementRequest();
                reimbursementRequest.setReimbursementRequestId(rs.getInt("reimbursement_request_id"));
                reimbursementRequest.setDescription(rs.getString("description"));
                reimbursementRequest.setReceipt(rs.getString("receipt"));
                reimbursementRequest.setAmount(rs.getString("amount"));
            }
        } catch (SQLException e) {
            throw new ERSPersistenceException("Could not connect to db.", e);
        } catch (IOException e) {
            throw new ERSPersistenceException("Could not read from db.", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new ERSPersistenceException("Could not close db.", e);
            }
        }
        return reimbursementRequest;
    }
    
    @Override
    public List<ReimbursementRequest> getAllReimbursementRequests() throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ReimbursementRequest reimbursementRequest = null;
        List<ReimbursementRequest> reimbursementRequests = new ArrayList<>();
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_ALL_REIMBURSEMENT_REQUESTS);
            rs = ps.executeQuery();
            while (rs.next()) {
                reimbursementRequest = new ReimbursementRequest();
                reimbursementRequest.setReimbursementRequestId(rs.getInt("reimbursement_request_id"));
                reimbursementRequest.setDescription(rs.getString("description"));
                reimbursementRequest.setReceipt(rs.getString("receipt"));
                reimbursementRequest.setAmount(rs.getString("amount"));
                reimbursementRequests.add(reimbursementRequest);
            }
        } catch (SQLException e) {
            throw new ERSPersistenceException("Could not connect to db.", e);
        } catch (IOException e) {
            throw new ERSPersistenceException("Could not read from db.", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new ERSPersistenceException("Could not close db.", e);
            }
        }
        return reimbursementRequests;
    }
    
}
