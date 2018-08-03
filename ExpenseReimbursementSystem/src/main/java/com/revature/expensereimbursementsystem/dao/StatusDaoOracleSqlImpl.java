/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.dao;

import com.revature.expensereimbursementsystem.dto.Status;
import com.revature.expensereimbursementsystem.util.ConnectionUtil;
import java.io.IOException;
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
public class StatusDaoOracleSqlImpl implements StatusDao {
    
    private static final String SQL_INSERT_STATUS 
            = "INSERT INTO statuses (status) "
            + "VALUES (?)";
    
    private static final String SQL_DELETE_STATUS 
            = "DELETE FROM statuses "
            + "WHERE status_id = ?";
    
    private static final String SQL_UPDATE_STATUS 
            = "UPDATE statuses "
            + "SET status = ? "
            + "WHERE status_id = ?";
    
    private static final String SQL_SELECT_STATUS_BY_STATUS_ID 
            = "SELECT * FROM statuses "
            + "WHERE status_id = ?";
    
    private static final String SQL_SELECT_STATUS_BY_STATUS 
            = "SELECT * FROM statuses "
            + "WHERE status = ?";
    
    private static final String SQL_SELECT_STATUS_BY_REIMBURSEMENT_REQUEST_ID 
            = "SELECT statuses.status_id, statuses.status " 
            + "FROM statuses " 
            + "INNER JOIN reimbursement_requests " 
            + "ON reimbursement_requests.status_id = statuses.status_id " 
            + "WHERE reimbursement_request_id = ?";
    
    private static final String SQL_SELECT_ALL_STATUSES 
            = "SELECT * FROM statuses";

    @Override
    public void addStatus(Status status) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_INSERT_STATUS);
            ps.setString(1, status.getStatus());
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
    public void deleteStatus(int statusId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_DELETE_STATUS);
            ps.setInt(1, statusId);
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
    public void updateStatus(Status status) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_UPDATE_STATUS);
            ps.setString(1, status.getStatus());
            ps.setInt(2, status.getStatusId());
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
    public Status getStatusByStatusId(int statusId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Status status = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_STATUS_BY_STATUS_ID);
            ps.setInt(1, statusId);
            rs = ps.executeQuery();
            while (rs.next()) {
                status = new Status();
                status.setStatusId(rs.getInt("status_id"));
                status.setStatus(rs.getString("status"));
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
        return status;
    }
    
    public Status getStatusByStatus(String input) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Status status = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_STATUS_BY_STATUS);
            ps.setString(1, input);
            rs = ps.executeQuery();
            while (rs.next()) {
                status = new Status();
                status.setStatusId(rs.getInt("status_id"));
                status.setStatus(rs.getString("status"));
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
        return status;
    }
    
    @Override
    public Status getStatusByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Status status = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_STATUS_BY_REIMBURSEMENT_REQUEST_ID);
            ps.setInt(1, reimbursementRequestId);
            rs = ps.executeQuery();
            while (rs.next()) {
                status = new Status();
                status.setStatusId(rs.getInt("status_id"));
                status.setStatus(rs.getString("status"));
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
        return status;
    }

    @Override
    public List<Status> getAllStatuses() throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Status status = null;
        List<Status> statuses = new ArrayList<>();
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_ALL_STATUSES);
            rs = ps.executeQuery();
            while (rs.next()) {
                status = new Status();
                status.setStatusId(rs.getInt("status_id"));
                status.setStatus(rs.getString("status"));
                statuses.add(status);
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
        return statuses;
    }
    
}
