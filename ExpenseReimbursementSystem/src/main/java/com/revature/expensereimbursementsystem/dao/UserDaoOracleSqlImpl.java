/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.dao;

import com.revature.expensereimbursementsystem.dto.User;
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
public class UserDaoOracleSqlImpl implements UserDao {

    private static final String SQL_INSERT_USER 
            = "INSERT INTO users (username, password, employee_id) "
            + "VALUES (?, ?, ?)";
    
    private static final String SQL_DELETE_USER 
            = "DELETE FROM users "
            + "WHERE user_id = ?";
    
    private static final String SQL_UPDATE_USER 
            = "UPDATE users "
            + "SET username = ?, password = ?, employee_id = ? "
            + "WHERE user_id = ?";
    
    private static final String SQL_SELECT_USER_BY_USER_ID 
            = "SELECT user_id, username, password "
            + "FROM users "
            + "WHERE user_id = ?";
    
    private static final String SQL_SELECT_ALL_USERS 
            = "SELECT user_id, username, password "
            + "FROM users";
    
    @Override
    public void addUser(User user) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_INSERT_USER);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getEmployee().getEmployeeId());
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
    public void deleteUser(int userId) throws ERSPersistenceException {
        PreparedStatement ps  = null;
        ResultSet rs = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_DELETE_USER);
            ps.setInt(1, userId);
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
    public void updateUser(User user) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_UPDATE_USER);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getEmployee().getEmployeeId());
            ps.setInt(4, user.getUserId());
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
    public User getUserByUserId(int userId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_USER_BY_USER_ID);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
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
        return user;
    }

    @Override
    public List<User> getAllUsers() throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        List<User> users = new ArrayList<>();
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_ALL_USERS);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
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
        return users;
    }
    
}
