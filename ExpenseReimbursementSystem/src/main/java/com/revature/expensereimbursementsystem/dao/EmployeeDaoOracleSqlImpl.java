/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.dao;

import com.revature.expensereimbursementsystem.dto.Employee;
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
public class EmployeeDaoOracleSqlImpl implements EmployeeDao {

    private static final String SQL_INSERT_EMPLOYEE
            = "INSERT INTO employees (first_name, last_name, email, role_id) "
            + "VALUES (?, ?, ?, ?)";

    private static final String SQL_DELETE_EMPLOYEE
            = "DELETE FROM employees "
            + "WHERE employee_id = ?";

    private static final String SQL_UPDATE_EMPLOYEE
            = "UPDATE employees "
            + "SET first_name = ?, last_name = ?, email = ?, role_id = ? "
            + "WHERE employee_id = ?";

    private static final String SQL_SELECT_EMPLOYEE_BY_EMPLOYEE_ID
            = "SELECT employee_id, first_name, last_name, email "
            + "FROM employees "
            + "WHERE employee_id = ?";

    private static final String SQL_SELECT_EMPLOYEE_BY_USER_ID
            = "SELECT employees.employee_id, employees.first_name, "
            + "employees.last_name, employees.email "
            + "FROM employees "
            + "INNER JOIN users "
            + "ON users.employee_id = employees.employee_id "
            + "WHERE user_id = ?";

    private static final String SQL_SELECT_EMPLOYEE_BY_USERNAME 
            = "SELECT employees.employee_id, employees.first_name, "
            + "employees.last_name, employees.email " 
            + "FROM employees " 
            + "INNER JOIN users " 
            + "ON users.employee_id = employees.employee_id " 
            + "WHERE username = ?";
    
    private static final String SQL_SELECT_REQUESTER_BY_REIMBURSEMENT_REQUEST_ID
            = "SELECT employees.employee_id, employees.first_name, "
            + "employees.last_name, employees.email "
            + "FROM employees "
            + "INNER JOIN reimbursement_requests "
            + "ON reimbursement_requests.requester_id = employees.employee_id "
            + "WHERE reimbursement_request_id = ?";
    
    private static final String SQL_SELECT_APPROVER_BY_REIMBURSEMENT_REQUEST_ID
            = "SELECT employees.employee_id, employees.first_name, "
            + "employees.last_name, employees.email "
            + "FROM employees "
            + "INNER JOIN reimbursement_requests "
            + "ON reimbursement_requests.approver_id = employees.employee_id "
            + "WHERE reimbursement_request_id = ?";

    private static final String SQL_SELECT_ALL_EMPLOYEES
            = "SELECT employee_id, first_name, last_name, email "
            + "FROM employees";

    @Override
    public void addEmployee(Employee employee) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_INSERT_EMPLOYEE);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setInt(4, employee.getRole().getRoleId());
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
    public void deleteEmployee(int employeeId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_DELETE_EMPLOYEE);
            ps.setInt(1, employeeId);
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
    public void updateEmployee(Employee employee) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_UPDATE_EMPLOYEE);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setInt(4, employee.getRole().getRoleId());
            ps.setInt(5, employee.getEmployeeId());
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
    public Employee getEmployeeByEmployeeId(int employeeId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_EMPLOYEE_BY_EMPLOYEE_ID);
            ps.setInt(1, employeeId);
            rs = ps.executeQuery();
            while (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
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
        return employee;
    }

    @Override
    public Employee getEmployeeByUserId(int userId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_EMPLOYEE_BY_USER_ID);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
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
        return employee;
    }

    @Override
    public Employee getEmployeeByUsername(String username) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_EMPLOYEE_BY_USERNAME);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
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
        return employee;
    }
    
    @Override
    public Employee getRequesterByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_REQUESTER_BY_REIMBURSEMENT_REQUEST_ID);
            ps.setInt(1, reimbursementRequestId);
            rs = ps.executeQuery();
            while (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
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
        return employee;
    }

    @Override
    public Employee getApproverByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_APPROVER_BY_REIMBURSEMENT_REQUEST_ID);
            ps.setInt(1, reimbursementRequestId);
            rs = ps.executeQuery();
            while (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
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
        return employee;
    }
    
    @Override
    public List<Employee> getAllEmployees() throws ERSPersistenceException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(SQL_SELECT_ALL_EMPLOYEES);
            rs = ps.executeQuery();
            while (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employees.add(employee);
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
        return employees;
    }

}
