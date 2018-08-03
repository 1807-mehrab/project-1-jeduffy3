/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.dao;

import com.revature.expensereimbursementsystem.dto.Role;
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
public class RoleDaoOracleSqlImpl implements RoleDao {

    private static final String SQL_INSERT_ROLE 
            = "INSERT INTO roles (role) "
            + "VALUES (?)";
    
    private static final String SQL_DELETE_ROLE 
            = "DELETE FROM roles "
            + "WHERE role_id = ?";
    
    private static final String SQL_UPDATE_ROLE 
            = "UPDATE roles "
            + "SET role = ? "
            + "WHERE role_id = ?";
    
    private static final String SQL_SELECT_ROLE_BY_ROLE_ID 
            = "SELECT * FROM roles "
            + "WHERE role_id = ?";
    
    private static final String SQL_SELECT_ROLE_BY_EMPLOYEE_ID 
            = "SELECT roles.role_id, roles.role " 
            + "FROM roles " 
            + "INNER JOIN employees " 
            + "ON employees.role_id = roles.role_id " 
            + "WHERE employee_id = ?";
    
    private static final String SQL_SELECT_ROLE_BY_USERNAME 
            = "SELECT roles.role_id, roles.role FROM roles "
            + "INNER JOIN employees "
            + "ON roles.role_id = employees.role_id "
            + "INNER JOIN users "
            + "ON users.employee_id = employees.employee_id "
            + "WHERE users.username = ?";
    
    private static final String SQL_SELECT_ALL_ROLES 
            = "SELECT * FROM roles";
    
    @Override
    public void addRole(Role role) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_INSERT_ROLE);
            ps.setString(1, role.getRole());
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
    public void deleteRole(int roleId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_DELETE_ROLE);
            ps.setInt(1, roleId);
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
    public void updateRole(Role role) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_UPDATE_ROLE);
            ps.setString(1, role.getRole());
            ps.setInt(2, role.getRoleId());
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
    public Role getRoleByRoleId(int roleId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Role role = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_ROLE_BY_ROLE_ID);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                role = new Role();
                role.setRoleId(rs.getInt("role_id"));
                role.setRole(rs.getString("role"));
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
        return role;
    }

    @Override
    public Role getRoleByEmployeeId(int employeeId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Role role = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_ROLE_BY_EMPLOYEE_ID);
            ps.setInt(1, employeeId);
            rs = ps.executeQuery();
            while (rs.next()) {
                role = new Role();
                role.setRoleId(rs.getInt("role_id"));
                role.setRole(rs.getString("role"));
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
        return role;
    }
    
    @Override
    public Role getRoleByUsername(String username) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Role role = null;
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_ROLE_BY_USERNAME);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                role = new Role();
                role.setRoleId(rs.getInt("role_id"));
                role.setRole(rs.getString("role"));
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
        return role;
    }
    
    @Override
    public List<Role> getAllRoles() throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Role role = null;
        List<Role> roles = new ArrayList<>();
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_ALL_ROLES);
            rs = ps.executeQuery();
            while (rs.next()) {
                role = new Role();
                role.setRoleId(rs.getInt("role_id"));
                role.setRole(rs.getString("role"));
                roles.add(role);
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
        return roles;
    }
    
}
